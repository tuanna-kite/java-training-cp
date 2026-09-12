package io.kite.leetcode.lc23;

public class SolutionOptimize {
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
        if (lists.length == 0) {
            return null;
        }

        for (int interval = 1; interval < lists.length; interval *= 2) {
            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeTwo(lists[i], lists[i + interval]);
            }
        }

        return lists[0];
    }

    private ListNode mergeTwo(ListNode a, ListNode b) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }

            tail = tail.next;
        }

        tail.next = a != null ? a : b;

        return dummy.next;
    }

    public static void main(String[] args) {
        Solution.ListNode[] lists = new Solution.ListNode[3];

        lists[0] = new Solution.ListNode(1);
        lists[0].next = new Solution.ListNode(5);
        lists[0].next.next = new Solution.ListNode(7);

        lists[1] = new Solution.ListNode(4);
        lists[1].next = new Solution.ListNode(8);
        lists[1].next.next = new Solution.ListNode(9);


        lists[2] = new Solution.ListNode(2);
        lists[2].next = new Solution.ListNode(3);

        Solution.ListNode nodes = new Solution().mergeKLists(lists);

        Solution.ListNode current = nodes;
        while(current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

}
