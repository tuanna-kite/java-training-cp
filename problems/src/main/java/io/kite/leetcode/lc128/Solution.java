package io.kite.leetcode.lc128;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int longestConsecutive(int[] nums) {
        Set<Integer> exists = new HashSet<>((int) (nums.length / 0.75f) + 1);

        for (var elm : nums) exists.add(elm);

        int maxLen = 0;
        for (var elm : exists) {
            if (exists.contains(elm - 1)) {
                continue;
            }

            int current = elm;
            int len = 1;
            while (exists.contains(current + 1)) {
                len++;
                current++;
            }
            maxLen = Math.max(len, maxLen);
        }

        return maxLen;
    }

}
