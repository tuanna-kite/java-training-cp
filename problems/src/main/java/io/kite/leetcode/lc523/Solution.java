package io.kite.leetcode.lc523;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remains = new HashMap<>(nums.length * 2);
        int invalid = -2;
        remains.put(0, -1);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int r = ((sum % k) + k) % k;
            int first = remains.getOrDefault(r, invalid);
            if (first > invalid && i - first > 1) return true;
            if (first == invalid) remains.put(r, i);
        }

        return false;
    }
}
