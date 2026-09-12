package io.kite.leetcode.lc23;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() { }

        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.val)
        );

        for (var node : lists) {
            if (node != null) pq.add(node);
        }

        ListNode result = null;
        ListNode last = null;

        while (!pq.isEmpty()) {
            var best = pq.remove();
            if (result == null) {
                result = best;
            } else {
                last.next = best;
            }
            last = best;
            if (best.next != null) {
                pq.add(best.next);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];

        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(5);
        lists[0].next.next = new ListNode(7);

        lists[1] = new ListNode(4);
        lists[1].next = new ListNode(8);
        lists[1].next.next = new ListNode(9);


        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(3);

        ListNode nodes = new Solution().mergeKLists(lists);

        ListNode current = nodes;
        while(current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

}
