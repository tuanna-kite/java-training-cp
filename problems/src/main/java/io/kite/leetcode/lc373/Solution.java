package io.kite.leetcode.lc373;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    record Candidate(int srcId, int pos, int val) { }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> results = new ArrayList<>(k);
        PriorityQueue<Candidate> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.val)
        );

        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            pq.add(new Candidate(i, 0, nums1[i] + nums2[0]));
        }

        for (int i = 0; i < k; i++) {
            var best = pq.remove();
            results.add(List.of(nums1[best.srcId], nums2[best.pos]));
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
}
