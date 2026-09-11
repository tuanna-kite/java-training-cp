package linear.kwaymerge;

import java.util.*;

public class ArrayBasedKWayMerge {

    record Candidate(int sourceId, int position, int value) { }

    public int[] merge(int[][] sources) {
        PriorityQueue<Candidate> pq = new PriorityQueue<>(
                Comparator.comparingInt(Candidate::value)
        );

        int length = 0;
        for (int sourceId = 0; sourceId < sources.length; sourceId++) {
            var src = sources[sourceId];
            length += src.length;
            if (src.length > 0) {
                pq.add(new Candidate(sourceId, 0, src[0]));
            }
        }

        int[] results = new int[length];
        int i = 0;

        while (!pq.isEmpty()) {
            var best = pq.remove();
            results[i++] = best.value();
            int srcId = best.sourceId();
            int nextPos = best.position() + 1;
            if (nextPos < sources[srcId].length) {
                pq.add(new Candidate(
                        srcId,
                        nextPos,
                        sources[srcId][nextPos]
                ));
            }
        }

        return results;
    }

    public static void main(String[] args) {
        int[][] sources = new int[][]{
                {1, 8, 9, 11},
                {3, 5, 13},
                {2, 6, 12},
                {4, 7, 10, 14, 15},
        };
        System.out.println(Arrays.toString(new ArrayBasedKWayMerge().merge(sources)));
    }

}
