package io.kite.leetcode.lc946;


public class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int[] stack = new int[n];
        int top = -1;
        int i = 0;
        int j = 0;
        while (i < n) {
            if (top > -1 && stack[top] == popped[i]) {
                i++;
                top--;
                continue;
            }
            while (j < n && (top == -1 || stack[top] != popped[i])) {
                top++;
                stack[top] = pushed[j++];
            }
            if(j == n && stack[top] != popped[i]) break;
        }

        return i == n;
    }

    public static void main(String[] args) {
        new Solution().validateStackSequences(
                new int[] {1,2,3,4,5},
                new int[] {4,5,3,2,1}
        );
    }
}
