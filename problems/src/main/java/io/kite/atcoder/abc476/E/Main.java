package io.kite.atcoder.abc476.E;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] p;
    static Node[] tree;

    static class Node {
        int minValue;
        int minIndex;

        int maxValue;
        int maxIndex;

        Node(int minValue, int minIndex, int maxValue, int maxIndex) {
            this.minValue = minValue;
            this.minIndex = minIndex;
            this.maxValue = maxValue;
            this.maxIndex = maxIndex;
        }
    }

    static void solve() throws Exception {
        n = nextInt();
        m = nextInt();

        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = nextInt();
        }

        tree = new Node[4 * n];
        build(1, 0, n - 1);

        for (int q = 0; q < m; q++) {
            int l = nextInt() - 1;
            int r = nextInt() - 1;

            Node result = query(1, 0, n - 1, l, r);

            int minPos = result.minIndex;
            int maxPos = result.maxIndex;

            int temp = p[minPos];
            p[minPos] = p[maxPos];
            p[maxPos] = temp;

            update(1, 0, n - 1, minPos, p[minPos]);
            update(1, 0, n - 1, maxPos, p[maxPos]);
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(p[i]);
        }

        System.out.println(out);
    }

    static void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(p[l], l, p[l], l);
            return;
        }

        int mid = (l + r) >>> 1;

        build(node << 1, l, mid);
        build(node << 1 | 1, mid + 1, r);

        tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
    }

    static Node merge(Node a, Node b) {
        int minValue;
        int minIndex;

        if (a.minValue < b.minValue) {
            minValue = a.minValue;
            minIndex = a.minIndex;
        } else {
            minValue = b.minValue;
            minIndex = b.minIndex;
        }

        int maxValue;
        int maxIndex;

        if (a.maxValue > b.maxValue) {
            maxValue = a.maxValue;
            maxIndex = a.maxIndex;
        } else {
            maxValue = b.maxValue;
            maxIndex = b.maxIndex;
        }

        return new Node(minValue, minIndex, maxValue, maxIndex);
    }

    static Node query(
            int node,
            int l,
            int r,
            int ql,
            int qr
    ) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) >>> 1;

        if (qr <= mid) {
            return query(node << 1, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node << 1 | 1, mid + 1, r, ql, qr);
        }

        Node left = query(node << 1, l, mid, ql, qr);
        Node right = query(node << 1 | 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    static void update(
            int node,
            int l,
            int r,
            int pos,
            int value
    ) {
        if (l == r) {
            tree[node] = new Node(value, pos, value, pos);
            return;
        }

        int mid = (l + r) >>> 1;

        if (pos <= mid) {
            update(node << 1, l, mid, pos, value);
        } else {
            update(node << 1 | 1, mid + 1, r, pos, value);
        }

        tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
    }

    public static void main(String[] args) throws Exception {
        solve();
    }

    static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    static StringTokenizer st;

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
