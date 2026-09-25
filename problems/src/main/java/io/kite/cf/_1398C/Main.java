package io.kite.cf._1398C;

import java.io.*;
import java.util.*;

public class Main {

    static void solveCase() throws Exception {
        int n = IO.i32();
        char[] a = IO.str().toCharArray();
        int[] ps = new int[n+1];
        for (int i = 0; i < n; i++) {
            ps[i + 1] = ps[i] + (a[i] - '0');
        }

        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            int key = ps[i] - i;
            int f = freq.getOrDefault(key, 0);
            ans += f;
            freq.put(key, f + 1);
        }

        IO.println(ans);
    }

    static void solve() throws Exception {
        int t = IO.i32();

        while (t-- > 0) {
            solveCase();
        }
    }

    public static void main(String[] args) throws Exception {
        solve();
        IO.flush();
    }

    static final class IO {

        private static final BufferedReader IN =
                new BufferedReader(new InputStreamReader(System.in));

        private static final StringBuilder OUT =
                new StringBuilder();

        private static StringTokenizer st;

        private IO() { }

        // ========================= INPUT =========================

        static String str() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = IN.readLine();

                if (line == null) {
                    throw new EOFException();
                }

                st = new StringTokenizer(line);
            }

            return st.nextToken();
        }

        static String line() throws IOException {
            st = null;
            return IN.readLine();
        }

        static int i32() throws IOException {
            return Integer.parseInt(str());
        }

        static long i64() throws IOException {
            return Long.parseLong(str());
        }

        static double f64() throws IOException {
            return Double.parseDouble(str());
        }

        static int[] ints(int n) throws IOException {
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = i32();
            }

            return a;
        }

        static long[] longs(int n) throws IOException {
            long[] a = new long[n];

            for (int i = 0; i < n; i++) {
                a[i] = i64();
            }

            return a;
        }

        // ========================= OUTPUT ========================

        static void print(Object x) {
            OUT.append(x);
        }

        static void println(Object x) {
            OUT.append(x).append('\n');
        }

        static void println() {
            OUT.append('\n');
        }

        static void flush() {
            System.out.print(OUT);
        }
    }
}
