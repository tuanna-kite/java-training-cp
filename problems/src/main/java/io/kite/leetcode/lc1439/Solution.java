package io.kite.leetcode.lc1439;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    record Candidate(int srcId, int pos, int val) { }

    public int kthSmallest(int[][] mat, int k) {
        if (mat.length == 1) {
            return mat[0][k-1];
        }
        int[] results = mergePairs(mat[0], mat[1], k);
        for (int i = 2; i < mat.length; i++) {
            results = mergePairs(results, mat[i], k);
        }

        return results[results.length - 1];
    }

    public int[] mergePairs(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int limit = Math.min(n1 * n2, k);
        int[] results = new int[limit];

        PriorityQueue<Candidate> pq = new PriorityQueue<>(
                Comparator.comparingInt(Candidate::val)
        );

        for (int i = 0; i < Math.min(nums1.length, limit); i++) {
            pq.add(new Candidate(i, 0, nums1[i] + nums2[0]));
        }

        for (int i = 0; i < limit; i++) {
            var best = pq.remove();
            results[i] = best.val;
            int nextPos = best.pos + 1;
            if (nextPos < nums2.length) {
                pq.add(new Candidate(
                        best.srcId,
                        nextPos,
                        nums1[best.srcId] + nums2[nextPos]
                ));
            }
        }

        return results;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 10, 10},
                {1, 4, 5},
                {2, 3, 6}
        };

        System.out.println(new Solution().kthSmallest(mat, 7));
    }
}
