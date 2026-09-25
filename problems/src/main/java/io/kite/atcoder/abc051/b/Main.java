package io.kite.atcoder.abc051.b;

import java.io.*;
import java.util.*;

// https://atcoder.jp/contests/abc051/tasks/abc051_b
public class Main {

    static void solveCase() throws Exception {
        int K = IO.i32();
        int S = IO.i32();

        int count = 0;
        for (int x = 0; x <= Math.min(S, K); x++) {
            int s1 = S - x;
            for (int y = 0; y <= Math.min(s1, K); y++) {
                if (s1 - y <= K) count++;
            }
        }

        IO.println(count);
    }

    public static void main(String[] args) throws Exception {
        solveCase();
        IO.flush();
    }

    static final class IO {

        private static final BufferedReader IN =
                new BufferedReader(new InputStreamReader(System.in));

        private static final StringBuilder OUT =
                new StringBuilder();

        private static StringTokenizer st;

        private IO() {}

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
