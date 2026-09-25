package io.kite.leetcode.lc974;


public class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int[] remains = new int[k];
        remains[0] = 1;

        int count = 0;
        int s = 0;
        for (int num : nums) {
            s += num;
            int remain = s % k;
            if (remain < 0) remain = remain + k;
            count += remains[remain];
            remains[remain]++;
        }

        return count;
    }

}
