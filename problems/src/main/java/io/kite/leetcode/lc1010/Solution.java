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
        int c01 = c0 - 1;
        if(c0 % 2 == 0) c0 /= 2;
        else c01 /= 2;
        ans += c0 * c01;

        int c30 = remains[30];
        int c301 = c30 - 1;
        if(c30 % 2 == 0) c30 /= 2;
        else c301 /= 2;
        ans += c30 * c301;

        for (int i = 1; i <= 29; i++) {
            ans += remains[i] * remains[60-i];
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40}));
    }
}
