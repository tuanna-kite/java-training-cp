package io.kite.leetcode.lc1439;

public class BinarySearchSolution {

    public int kthSmallest(int[][] mat, int k) {
        int base = 0;
        int maxSum = 0;
        for (var nums : mat) {
            base += nums[0];
            maxSum += nums[nums.length - 1];
        }

        int lo = base;
        int hi = maxSum;
        int ans = maxSum;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (count(mat, 0, mid - base, k) >= k) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    int count(int[][] mat, int row, int remaining, int limit) {
        if (row == mat.length) return 1;

        int c = 0;
        for (int col = 0; col < mat[0].length; col++) {
            int extra = mat[row][col] - mat[row][0];
            if (extra > remaining) {
                break;
            }
            c += count(
                    mat,
                    row + 1,
                    remaining - extra,
                    limit - c
            );

            if (c >= limit) return limit;
        }
        return c;
    }
}
