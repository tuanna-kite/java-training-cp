package io.kite.leetcode.lc950;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Arrays.sort(deck);
        Deque<Integer> deque = new ArrayDeque<>(n);

        int[] results = new int[n];

        for (int i = 0; i < n; i++) {
            deque.add(i);
        }

        int i = 0;
        while (!deque.isEmpty()) {
            int idx = deque.removeFirst();
            results[idx] = deck[i];
            i++;
            if(!deque.isEmpty()) {
                int v = deque.removeFirst();
                deque.addLast(v);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        int[] results = new Solution().deckRevealedIncreasing(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8}
        );

        Deque<Integer> deque = new ArrayDeque<>();
        for (int result : results) {
            deque.addLast(result);
        }

        while (!deque.isEmpty()) {
            System.out.println(deque.remove());
            if (!deque.isEmpty()) {
                int v = deque.remove();
                deque.addLast(v);
            }
        }
    }
}
