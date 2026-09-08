package io.kite._1353D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    record Interval(int l, int r) implements Comparable<Interval> {
        int len() {
            return r - l + 1;
        }

        int mid() {
            return (l + r) / 2;
        }

        Interval left() {
            return new Interval(l, mid() - 1);
        }

        Interval right() {
            return new Interval(mid() + 1, r);
        }

        boolean valid() {
            return l <= r && l > 0;
        }

        @Override
        public int compareTo(Interval o) {
            int byLen = Integer.compare(o.len(), len());
            if (byLen == 0) {
                return Integer.compare(l, o.l);
            }
            return byLen;
        }
    }

    static void constructing(int n) {
        int[] A = new int[n];
        PriorityQueue<Interval> pq = new PriorityQueue<>(n);
        pq.add(new Interval(1, n));
        for (int i = 1; i <= n; i++) {
            var interval = pq.remove();
            A[interval.mid() - 1] = i;
            var left = interval.left();
            var right = interval.right();
            if (left.valid()) pq.add(left);
            if (right.valid()) pq.add(right);
        }
        StringBuilder sb = new StringBuilder();
        for (var v : A) {
            sb.append(v).append(' ');
        }
        System.out.println(sb);
    }

    static void solve() throws Exception {
        int T = nextInt();
        for (int t = 1; t <= T; t++) {
            int n = nextInt();
            constructing(n);
        }
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

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public static void main(String[] args) throws Exception {
        solve();
    }
}
