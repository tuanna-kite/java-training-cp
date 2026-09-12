package io.kite.leetcode.lc378;

public class BinarySearchSolution {
    private boolean check(int[][] matrix, int k, int val) {
        int n = matrix.length;
        int row = n-1;
        int col = 0;
        int cnt = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= val) {
                cnt += (row + 1);
                col++;
            } else {
                row--;
            }
        }

        return cnt >= k;
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[n-1][n-1];

        int ans = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(matrix, k, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

}
