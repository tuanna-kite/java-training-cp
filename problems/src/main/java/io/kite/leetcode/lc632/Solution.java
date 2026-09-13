package io.kite.leetcode.lc632;

import java.util.*;

public class Solution {
    record Candidate(int src, int pos, int val) { }

    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        PriorityQueue<Candidate> pq = new PriorityQueue<>(
                n,
                Comparator.comparingInt(Candidate::val)
        );

        int maxVal = nums.getFirst().getFirst();
        for (int i = 0; i < n; i++) {
            var list = nums.get(i);
            int val = list.getFirst();
            maxVal = Math.max(val, maxVal);
            pq.add(new Candidate(i, 0, val));
        }
        int[] ans = new int[]{pq.peek().val, maxVal};

        while (pq.size() == n) {
            var min = pq.remove();
            if (maxVal - min.val < ans[1] - ans[0] ||
                    maxVal - min.val == ans[1] - ans[0] && min.val < ans[0]
            ) {
                ans[0] = min.val;
                ans[1] = maxVal;
            }
            if (min.pos + 1 < nums.get(min.src).size()) {
                pq.add(new Candidate(
                        min.src,
                        min.pos + 1,
                        nums.get(min.src).get(min.pos + 1)
                ));
                maxVal = Math.max(maxVal, nums.get(min.src).get(min.pos + 1));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(List.of(4, 10, 15, 24, 26));
        nums.add(List.of(0, 9, 12, 20));
        nums.add(List.of(5, 18, 22, 30));

        System.out.println(Arrays.toString(
                new Solution().smallestRange(nums)
        ));
    }


}
