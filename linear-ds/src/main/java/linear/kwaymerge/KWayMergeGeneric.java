package linear.kwaymerge;

import java.util.*;

public class KWayMergeGeneric {

    private record Candidate<T>(Iterator<? extends T> it, T value) { }

    static <T> List<T> merge(
            List<? extends Iterable<? extends T>> sources,
            Comparator<? super T> comparator
    ) {
        Objects.requireNonNull(sources, "sources");
        Objects.requireNonNull(comparator, "comparator");

        PriorityQueue<Candidate<T>> candidates = new PriorityQueue<>(
                (a, b) -> comparator.compare(a.value(), b.value())
        );

        for (var src : sources) {
            Objects.requireNonNull(src, "source");
            var it = src.iterator();
            if (it.hasNext()) {
                candidates.add(new Candidate<>(it, it.next()));
            }
        }

        List<T> results = new ArrayList<>();

        while (!candidates.isEmpty()) {
            var best = candidates.remove();
            results.add(best.value());
            if (best.it().hasNext()) {
                candidates.add(new Candidate<>(best.it(), best.it().next()));
            }
        }

        return results;
    }
}
