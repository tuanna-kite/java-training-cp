package io.kite.leetcode.lc525;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }

        Map<Integer, Integer> freqMap = new HashMap<>(nums.length * 2);
        freqMap.put(0, -1);

        int s = 0;
        int maxLen = 0;
        for (int r = 0; r < nums.length; r++) {
            s += nums[r];
            int l = freqMap.getOrDefault(s, nums.length);
            maxLen = Math.max(maxLen, r-l);
            if (l == nums.length) freqMap.put(s, r);
        }

        return maxLen;
    }
}
