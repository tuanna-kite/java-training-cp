package io.kite.cf._466C;

import java.io.*;
import java.util.*;


public class Main {

    static void solveCase() throws Exception {
        int n = IO.i32();
        long[] a = IO.longs(n);
        long total = 0L;
        for (int i = 0; i < n; i++) {
            total += a[i];
        }

        if (total % 3 != 0) {
            IO.println(0);
            return;
        }

        long t = total / 3;
        long ans = 0;
        int count = 0;
        long sum = a[0];
        if (sum == t) count++;

        for (int i = 1; i < n-1; i++) {
            sum += a[i];
            if (sum == 2 * t) ans += count;
            if (sum == t) count++;
        }

        IO.println(ans);
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