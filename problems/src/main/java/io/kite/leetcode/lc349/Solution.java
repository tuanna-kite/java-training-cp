package io.kite.leetcode.lc349;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] unique = new boolean[1001];
        for (var elm : nums1) unique[elm] = true;

        Set<Integer> set = new HashSet<>(1001);

        for (var elm : nums2) {
            if (unique[elm]) set.add(elm);
        }

        int[] results = new int[set.size()];
        int i = 0;
        for (var elm : set) results[i++] = elm;
        return results;
    }

}
