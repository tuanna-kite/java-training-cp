package io.kite.atcoder.abc122.c;

import java.io.*;
import java.util.*;

public class Main {

    static void solveCase() throws Exception {
        int n = IO.i32();
        int q = IO.i32();
        char[] str = IO.str().toCharArray();

        int[] count = new int[n+1];

        for (int i = 0; i < n-1; i++) {
            count[i+1] = count[i];
            if (str[i] == 'A' && str[i+1] == 'C') {
                ++count[i+1];
            }
        }

        for (int i = 0; i < q; i++) {
            int l = IO.i32();
            int r = IO.i32();

            IO.println(count[r-1] - count[l-1]);
        }
    }

    static void solve() throws Exception {
        int t = 1;

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
