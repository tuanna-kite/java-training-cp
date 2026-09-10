package linear.kwaymerge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KWayMergeGenericTest {

    record Event(long timestamp, String id) { }

    @Test
    void merge_nullSources_NPE() {
        assertThrows(
                NullPointerException.class,
                () -> KWayMergeGeneric.merge(null, Comparator.naturalOrder())
        );
    }

    @Test
    void merge_individualSourceNull_NPE() {
        List<List<Integer>> sources = new ArrayList<>(4);

        sources.add(new ArrayList<>(List.of(1, 6, 11)));
        sources.add(new ArrayList<>(List.of(2, 3, 20)));
        sources.add(null);
        sources.add(new ArrayList<>(List.of(4, 7, 9)));
        assertThrows(
                NullPointerException.class,
                () -> KWayMergeGeneric.merge(sources, Comparator.naturalOrder())
        );
    }

    @Test
    void merge_nullComparator_NPE() {
        assertThrows(
                NullPointerException.class,
                () -> KWayMergeGeneric.merge(new ArrayList<>(), null)
        );
    }

    @Test
    void merge_genericDomainObject() {
        List<List<Event>> events = List.of(
                List.of(new Event(1, "E001"), new Event(3, "E003"), new Event(9, "E009")),
                List.of(new Event(2, "E002"), new Event(5, "E005"), new Event(7, "E007")),
                List.of(new Event(0, "E000"), new Event(4, "E004"), new Event(6, "E006")),
                List.of(new Event(8, "E008"))
        );

        var results = KWayMergeGeneric.merge(events, Comparator.comparingLong(Event::timestamp));

        assertEquals(
                List.of(new Event(0, "E000"), new Event(1, "E001"), new Event(2, "E002"),
                        new Event(3, "E003"), new Event(4, "E004"), new Event(5, "E005"),
                        new Event(6, "E006"), new Event(7, "E007"), new Event(8, "E008"),
                        new Event(9, "E009")
                ),
                results
        );


    }


    @Test
    void merge_normalMerge() {
        List<List<Integer>> sources = new ArrayList<>(4);

        sources.add(new ArrayList<>(List.of(1, 6, 11)));
        sources.add(new ArrayList<>(List.of(2, 3, 20)));
        sources.add(new ArrayList<>(List.of(0, 8)));
        sources.add(new ArrayList<>(List.of(4, 7, 9)));

        var results = KWayMergeGeneric.merge(sources, Comparator.naturalOrder());

        assertEquals(
                List.of(0, 1, 2, 3, 4, 6, 7, 8, 9, 11, 20),
                results
        );
    }

    @Test
    void merge_withDuplicateValues() {
        List<List<Integer>> sources = new ArrayList<>(4);

        sources.add(new ArrayList<>(List.of(1, 6, 11)));
        sources.add(new ArrayList<>(List.of(1, 3, 20)));
        sources.add(new ArrayList<>(List.of(1, 8)));
        sources.add(new ArrayList<>(List.of(1, 7, 9)));

        var results = KWayMergeGeneric.merge(sources, Comparator.naturalOrder());

        assertEquals(
                List.of(1, 1, 1, 1, 3, 6, 7, 8, 9, 11, 20),
                results
        );
    }

    @Test
    void merge_emptyIndividualSources() {
        List<List<Integer>> sources = new ArrayList<>(4);

        sources.add(new ArrayList<>(List.of()));
        sources.add(new ArrayList<>(List.of(1, 3, 5)));
        sources.add(new ArrayList<>(List.of()));
        sources.add(new ArrayList<>(List.of(2, 4, 6)));

        var results = KWayMergeGeneric.merge(sources, Comparator.naturalOrder());

        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 6},
                results.stream().mapToInt(value -> value).toArray()
        );
    }

    @Test
    void merge_zeroSources() {
        List<List<Integer>> sources = new ArrayList<>();
        var results = KWayMergeGeneric.merge(sources, Comparator.naturalOrder());

        assertArrayEquals(
                new int[]{},
                results.stream().mapToInt(value -> value).toArray()
        );
    }

    @Test
    void merge_singleSources() {
        List<List<Integer>> sources = new ArrayList<>();
        sources.add(new ArrayList<>(List.of(1, 3, 5)));

        var results = KWayMergeGeneric.merge(sources, Comparator.naturalOrder());

        assertArrayEquals(
                new int[]{1, 3, 5},
                results.stream().mapToInt(value -> value).toArray()
        );
    }

    @Test
    void merge_reverseOrderingSources() {
        List<List<Integer>> sources = List.of(
                List.of(9, 5, 1),
                List.of(8, 4, 0),
                List.of(7, 6, 2)
        );

        var results = KWayMergeGeneric.merge(sources, Comparator.reverseOrder());

        assertArrayEquals(
                new int[]{9, 8, 7, 6, 5, 4, 2, 1, 0},
                results.stream().mapToInt(value -> value).toArray()
        );
    }


}