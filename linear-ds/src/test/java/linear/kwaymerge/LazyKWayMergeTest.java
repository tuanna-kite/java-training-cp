package linear.kwaymerge;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LazyKWayMergeTest {

    record Event(long timestamp, String id) { }

    private static class CountingIterable<T> implements Iterable<T> {
        private final List<T> values;
        private int nextCalls;

        public CountingIterable(List<T> values) {
            this.values = values;
            this.nextCalls = 0;
        }

        int nextCalls() { return nextCalls; }

        @SuppressWarnings("NullableProblems")
        @Override
        public Iterator<T> iterator() {
            var delegate = values.iterator();
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return delegate.hasNext();
                }

                @Override
                public T next() {
                    nextCalls++;
                    return delegate.next();
                }
            };
        }
    }

    @Test
    void constructor_prefetchesOnlyOneElementPerNonEmptySource() {
        var s1 = new CountingIterable<>(List.of(1, 4));
        var s2 = new CountingIterable<>(List.of(2, 5));
        var s3 = new CountingIterable<>(List.of(3, 6));

        var merge = new LazyKWayMerge<>(List.of(s1, s2, s3), Comparator.naturalOrder());

        assertEquals(1, s1.nextCalls());
        assertEquals(1, s2.nextCalls());
        assertEquals(1, s3.nextCalls());
    }


    // tests for constructor
    @Test
    void constructor_nullSources_NPE() {
        assertThrows(
                NullPointerException.class,
                () -> new LazyKWayMerge<>(null, Comparator.naturalOrder())
        );
    }

    @Test
    void constructor_nullComparator_NPE() {
        assertThrows(
                NullPointerException.class,
                () -> new LazyKWayMerge<>(new ArrayList<>(), null)
        );
    }

    @Test
    void constructor_nullIndividualSource_NPE() {
        List<List<Integer>> sources = new ArrayList<>();
        sources.add(List.of(1, 2, 3));
        sources.add(List.of(2, 3, 4));
        sources.add(null);
        sources.add(List.of(3, 4, 5));

        assertThrows(
                NullPointerException.class,
                () -> new LazyKWayMerge<>(sources, Comparator.naturalOrder())
        );
    }

    // Test hasNext
    @Test
    void hasNext_emptySources_returnFalse() {
        List<List<Integer>> sources = new ArrayList<>();
        var merge = new LazyKWayMerge<>(sources, Comparator.naturalOrder());
        assertFalse(merge.hasNext());
    }

    @Test
    void hasNext_nonEmptySources_returnTrue() {
        List<List<Integer>> sources = new ArrayList<>();

        sources.add(List.of(1, 2, 3));
        sources.add(List.of(2, 3, 4));
        sources.add(List.of(3, 4, 5));

        var merge = new LazyKWayMerge<>(sources, Comparator.naturalOrder());
        assertTrue(merge.hasNext());
    }

    @Test
    void hasNext_withEmptyIndividual_returnTrue() {
        List<List<Integer>> sources = new ArrayList<>();
        sources.add(List.of(1, 2, 3));
        sources.add(List.of());

        var merge = new LazyKWayMerge<>(sources, Comparator.naturalOrder());
        assertTrue(merge.hasNext());
    }

    @Test
    void hasNext_traverseAllElement_returnFalse() {
        List<List<Integer>> sources = new ArrayList<>();
        sources.add(List.of(1, 2, 3));
        var merge = new LazyKWayMerge<>(sources, Comparator.naturalOrder());
        merge.next();
        merge.next();
        merge.next();
        assertFalse(merge.hasNext());
    }


    @Test
    void next_advancesOnlyWinningSource() {
        var s1 = new CountingIterable<>(List.of(1, 4));
        var s2 = new CountingIterable<>(List.of(2, 5));
        var s3 = new CountingIterable<>(List.of(3, 6));

        var merge = new LazyKWayMerge<>(List.of(s1, s2, s3), Comparator.naturalOrder());

        assertEquals(1, s1.nextCalls());
        assertEquals(1, s2.nextCalls());
        assertEquals(1, s3.nextCalls());

        assertEquals(1, merge.next());
        assertEquals(2, s1.nextCalls());
        assertEquals(1, s2.nextCalls());
        assertEquals(1, s3.nextCalls());

        assertEquals(2, merge.next());
        assertEquals(2, s1.nextCalls());
        assertEquals(2, s2.nextCalls());
        assertEquals(1, s3.nextCalls());

        assertEquals(3, merge.next());
        assertEquals(2, s1.nextCalls());
        assertEquals(2, s2.nextCalls());
        assertEquals(2, s3.nextCalls());
    }

    // Next
    @Test
    void next_withValidSource_correctOrder() {
        List<List<Integer>> sources = new ArrayList<>();
        sources.add(List.of(1, 2, 8));
        sources.add(List.of(3, 4, 6));
        sources.add(List.of(5, 7, 9));
        var merge = new LazyKWayMerge<>(sources, Comparator.naturalOrder());
        List<Integer> results = new ArrayList<>();
        while (merge.hasNext()) {
            results.add(merge.next());
        }
        assertEquals(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                results
        );
    }

    @Test
    void next_withDuplicate_correctOrder() {
        List<List<Integer>> sources = new ArrayList<>();
        sources.add(List.of(1, 2, 8));
        sources.add(List.of(3, 4, 6));
        sources.add(List.of(3, 7, 9));
        sources.add(List.of(4));
        var merge = new LazyKWayMerge<>(sources, Comparator.naturalOrder());
        List<Integer> results = new ArrayList<>();
        while (merge.hasNext()) {
            results.add(merge.next());
        }
        assertEquals(
                List.of(1, 2, 3, 3, 4, 4, 6, 7, 8, 9),
                results
        );
    }

    @Test
    void next_withEmptyIndividual_correctOrder() {
        List<List<Integer>> sources = new ArrayList<>();
        sources.add(List.of(1, 3, 5));
        sources.add(List.of());
        sources.add(List.of(2, 4, 6, 8));
        var merge = new LazyKWayMerge<>(sources, Comparator.naturalOrder());
        List<Integer> results = new ArrayList<>();
        while (merge.hasNext()) {
            results.add(merge.next());
        }
        assertEquals(
                List.of(1, 2, 3, 4, 5, 6, 8),
                results
        );
    }

    @Test
    void next_withReversedOrdering_correctOrder() {
        List<List<Integer>> sources = new ArrayList<>();
        sources.add(List.of(8, 2, 1));
        sources.add(List.of(6, 4, 3));
        sources.add(List.of(9, 7, 5));
        var merge = new LazyKWayMerge<>(sources, Comparator.reverseOrder());
        List<Integer> results = new ArrayList<>();
        while (merge.hasNext()) {
            results.add(merge.next());
        }
        assertEquals(
                List.of(9, 8, 7, 6, 5, 4, 3, 2, 1),
                results
        );
    }

    @Test
    void next_traverseAllElement_throwNoSuchElement() {
        List<List<Integer>> sources = new ArrayList<>();
        var merge = new LazyKWayMerge<>(sources, Comparator.reverseOrder());

        assertThrows(
                NoSuchElementException.class,
                merge::next
        );
    }

    @Test
    void next_withGenericType_correctOrder() {
        List<List<Event>> sources = new ArrayList<>();
        sources.add(List.of(
                new Event(1, "E001"), new Event(4, "E004"), new Event(9, "E009")
        ));
        sources.add(List.of(
                new Event(3, "E003"), new Event(7, "E007"), new Event(8, "E008")
        ));
        sources.add(List.of(
                new Event(2, "E002"), new Event(5, "E005"), new Event(6, "E006")
        ));

        var merge = new LazyKWayMerge<>(sources, Comparator.comparingLong(Event::timestamp));

        List<Event> results = new ArrayList<>();
        while (merge.hasNext()) {
            results.add(merge.next());
        }
        assertEquals(
                List.of(
                        new Event(1, "E001"), new Event(2, "E002"), new Event(3, "E003"),
                        new Event(4, "E004"), new Event(5, "E005"), new Event(6, "E006"),
                        new Event(7, "E007"), new Event(8, "E008"), new Event(9, "E009")
                ),
                results
        );
    }
}