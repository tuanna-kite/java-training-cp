package io.kite.leetcode.lc992;

public class Solution {

    int atMost(int[] nums, int k) {
        final int N = nums.length;
        int[] freq = new int[N + 1];
        int count = 0;
        int distint = 0;
        int l = 0;
        for (int r = 0; r < N; r++) {
            if (freq[nums[r]] == 0) distint++;
            freq[nums[r]]++;
            while (l <= r && distint > k) {
                if (freq[nums[l]] == 1) distint--;
                freq[nums[l]]--;
                l++;
            }
            count += Math.max(0, r - l + 1);
        }
        return count;
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    public static void main(String[] args) {

        System.out.println(new Solution().subarraysWithKDistinct(
                new int[]{1, 2, 1, 3, 4}, 3
        ));

    }
}
