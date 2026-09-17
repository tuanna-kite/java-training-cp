package io.kite.leetcode.lc1010;

import java.util.Arrays;

public class Solution {

    public int numPairsDivisibleBy60(int[] time) {
        int[] remains = new int[60];

        for (var x : time) {
            remains[x % 60]++;
        }

        int ans = 0;
        int c0 = remains[0];
        ans += c0 * (c0 - 1) / 2;

        int c30 = remains[30];
        ans += c30 * (c30 - 1) / 2;


        for (int i = 0; i < 60; i++) {
            if (remains[i] != 0) { System.out.println(i + " - " + remains[i]); }
        }

        for (int i = 1; i <= 29; i++) {
            ans += remains[i] * remains[60 - i];
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40}));
    }
}
