package io.kite.leetcode.lc238;

public class Solution {

    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int cZero = 0;
        for (var x : nums) {
            if (x == 0) {
                cZero++;
                continue;
            }
            product *= x;
        }

        for (int i = 0; i < nums.length; i++) {
            if (cZero == 0 && nums[i] != 0) {
                nums[i] = product / nums[i];
            } else if (cZero == 1 && nums[i] == 0) {
                nums[i] = product;
            } else {
                nums[i] = 0;
            }
        }

        return nums;
    }

}
