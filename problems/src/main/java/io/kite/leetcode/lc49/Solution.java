package io.kite.leetcode.lc49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    char[] computeFreq(String str) {
        char[] freq = new char[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a']++;
        }

        return freq;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>((int) (strs.length / 0.75f) + 1);

        for (var str : strs) {
            char[] freq = computeFreq(str);
            String key = new String(freq);
            List<String> group = groups.computeIfAbsent(
                    key,
                    k -> new ArrayList<>()
            );

            group.add(str);
        }

        return new ArrayList<>(groups.values());
    }
}
