package io.kite.leetcode.lc378;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinHeapSolution {
    record Candidate(int srcId, int pos, int val) { }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Candidate> pq = new PriorityQueue<>(
                Comparator.comparingInt(Candidate::val)
        );

        for (int i = 0; i < Math.min(n,k); i++) {
            pq.add(new Candidate(i, 0, matrix[i][0]));
        }
        int ans = matrix[0][0];

        for (int i = 1; i <= k; i++) {
            var best = pq.remove();
            ans = best.val;
            int nextPos = best.pos + 1;
            if (nextPos < n) {
                pq.add(new Candidate(best.srcId, nextPos, matrix[best.srcId][nextPos]));
            }
        }

        return ans;
    }
}
