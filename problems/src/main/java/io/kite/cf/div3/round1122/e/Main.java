package io.kite.cf.div3.round1122.e;

import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_N = 200_000;
    static final int[] spf = new int[MAX_N + 1];

    static void solveCase() throws Exception {
        int n = IO.i32();
        int k = IO.i32();
        int[] A = IO.ints(n);

        long[] dp = new long[n + 1];

        for (int x = k + 1; x <= n; x++) {
            long best = Long.MAX_VALUE;
            int remain = x;
            while (remain > 1) {
                int p = spf[remain];
                long cand = 1L + (long) p * dp[x / p];
                best = Math.min(best, cand);
                while (remain % p == 0) {
                    remain /= p;
                }
                dp[x] = best;
            }
        }

        long ans = 0;
        for (var x : A) {
            ans += dp[x];
        }

        IO.println(ans);
    }

    static void solve() throws Exception {
        int t = IO.i32();

        while (t-- > 0) {
            solveCase();
        }
    }

    static void buildSPF() {
        for (int i = 2; i < MAX_N; i++) {
            if (spf[i] != 0) continue;
            for (int j = i; j <= MAX_N; j += i) {
                if (spf[j] == 0) {
                    spf[j] = i;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        buildSPF();
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
