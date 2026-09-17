package io.kite.leetcode.lc454;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        Map<Integer, Integer> f34 = new HashMap<>((int) (n * n / 0.75f) + 1);

        for (var x3 : nums3) {
            for (var x4 : nums4) {
                int s = x3 + x4;
                f34.put(s, f34.getOrDefault(s, 0) + 1);
            }
        }

        int ans = 0;
        for (var x1 : nums1) {
            for (var x2 : nums2) {
                int s = x1 + x2;
                ans += f34.getOrDefault(-s, 0);
            }
        }

        return ans;
    }

    public int fourSumCount2(
            int[] a,
            int[] b,
            int[] c,
            int[] d
    ) {
        final int capacity = 1 << 16;
        final int mask = capacity - 1;

        int[] keys = new int[capacity];
        int[] freq = new int[capacity];

        for (int x : a) {
            for (int y : b) {
                int key = x + y;
                int slot = key & mask;

                while (freq[slot] != 0 && keys[slot] != key) {
                    slot = (slot + 1) & mask;
                }

                keys[slot] = key;
                freq[slot]++;
            }
        }

        int answer = 0;

        for (int x : c) {
            for (int y : d) {
                int key = -(x + y);
                int slot = key & mask;

                while (freq[slot] != 0 && keys[slot] != key) {
                    slot = (slot + 1) & mask;
                }

                answer += freq[slot];
            }
        }

        return answer;
    }
}
