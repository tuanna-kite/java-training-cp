package io.kite.leetcode.lc217;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seens = new HashSet<>(2 * nums.length);

        for (var val : nums) {
            if (seens.contains(val)) return true;
            seens.add(val);
        }
        return false;
    }
}
