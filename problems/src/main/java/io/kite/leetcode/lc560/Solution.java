package io.kite.leetcode.lc560;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length * 2);
        freq.put(0, 1);

        int sum = 0;
        int ans = 0;
        for (var x : nums) {
            sum += x;
            int f = freq.getOrDefault(sum - k, 0);
            ans += f;
            freq.put(sum, freq.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum(
                new int[]{1, 2, 3},
                3
        ));
    }
}
