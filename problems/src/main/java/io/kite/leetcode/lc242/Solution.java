package io.kite.leetcode.lc242;

public class Solution {

    int[] computeFreq(String str) {
        int[] freq = new int[26];

        for (int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i) - 'a';
            freq[ch]++;
        }

        return freq;
    }

    boolean sameFreq(int[] freq1, int[] freq2) {
        int n = freq1.length;
        for (int i = 0; i < n; i++) {
            if (freq1[i] != freq2[i]) return false;
        }

        return true;
    }

    public boolean isAnagram(String s, String t) {
        return sameFreq(computeFreq(s), computeFreq(t));
    }

}
