package io.kite._681C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        int n = nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(n);
        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (int i = 0; i < n; i++) {
            String cmd = next();
            int x = 0;
            if (cmd.charAt(0) != 'r') {
                x = nextInt();
            }
            switch (cmd.charAt(0)) {
                case 'i' -> {
                    pq.add(x);
                    sb.append(cmd).append(" ").append(x).append("\n");
                    count++;
                }
                case 'r' -> {
                    if (pq.isEmpty()) {
                        sb.append("insert").append(" ").append(1).append("\n");
                        count++;
                        pq.add(1);
                    }
                    sb.append(cmd).append("\n");
                    count++;
                    pq.poll();
                }
                case 'g' -> {
                    while (!pq.isEmpty() && pq.peek() < x) {
                        sb.append("removeMin").append("\n");
                        count++;
                        pq.poll();
                    }
                    if (pq.isEmpty() || pq.peek() > x) {
                        sb.append("insert").append(" ").append(x).append("\n");
                        count++;
                        pq.add(x);
                    }
                    sb.append("getMin").append(" ").append(x).append("\n");
                    count++;
                }
            }
        }

        System.out.println(count);
        System.out.println(sb);

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
