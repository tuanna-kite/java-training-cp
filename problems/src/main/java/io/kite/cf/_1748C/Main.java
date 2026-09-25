package io.kite.cf._1748C;

import java.io.*;
import java.util.*;

public class Main {

    static void solveCase() throws Exception {
        int n = IO.i32();
        int[] a = IO.ints(n);

        long sum = 0;
        int bestScore = 0;
        boolean seenZero = false;

        Map<Long, Integer> freqMap = new HashMap<>();
        int bestFreq = 0;
        for (var x : a) {
            sum += x;

            if (x == 0) {
                if (seenZero) {
                    bestScore += bestFreq;
                }
                seenZero = true;
                bestFreq = 0;
                freqMap.clear();
            }

            if (!seenZero) {
                if (sum == 0) bestScore++;
            } else {
                int f = freqMap.getOrDefault(sum, 0) + 1;
                freqMap.put(sum, f);
                bestFreq = Math.max(f, bestFreq);
            }
        }

        if (seenZero) bestScore += bestFreq;

        IO.println(bestScore);

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
