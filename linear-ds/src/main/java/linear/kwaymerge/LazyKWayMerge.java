package linear.kwaymerge;

import java.util.*;

public class LazyKWayMerge<T> implements Iterator<T> {

    private record Candidate<T>(Iterator<? extends T> it, T value) { }

    private final PriorityQueue<Candidate<T>> pq;

    public LazyKWayMerge(
            final Iterable<? extends Iterable<? extends T>> sources,
            final Comparator<? super T> comparator
    ) {
        Objects.requireNonNull(sources, "sources");
        Objects.requireNonNull(comparator, "comparator");

        pq = new PriorityQueue<>(
                (a, b) -> comparator.compare(a.value(), b.value())
        );

        for (var src : sources) {
            Objects.requireNonNull(src, "individual source");
            var it = src.iterator();
            if (it.hasNext()) {
                pq.add(new Candidate<>(it, it.next()));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !pq.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        var best = pq.remove();
        var bestVal = best.value();
        if (best.it().hasNext()) {
            pq.add(new Candidate<>(best.it(), best.it().next()));
        }

        return bestVal;
    }
}
