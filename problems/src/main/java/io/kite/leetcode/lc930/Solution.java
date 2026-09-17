package io.kite.leetcode.lc930;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> lookup = new HashMap<>(nums.length * 2);
        lookup.put(0, 1);

        int s = 0;
        int ans = 0;
        for (int num : nums) {
            s += num;
            int freq = lookup.getOrDefault(s - goal, 0);
            ans += freq;
            lookup.put(s, lookup.getOrDefault(s, 0) + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        new Solution().numSubarraysWithSum(
                new int[]{1, 0, 1, 0, 1},
                2
        );
    }
}
