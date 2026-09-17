package io.kite.cf._1296C;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static long encode(int x, int y) {
        return ((long) x << 32) | (y & 0xffffffffL);
    }

    static void solve() throws Exception {
        int n = nextInt();
        String str = next();

        Map<Long, Integer> map = new HashMap<>((int) (n / 0.75f) + 1);
        map.put(encode(0, 0), 0);
        int minLen = n + 1;

        int x = 0, y = 0;
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            switch (str.charAt(i)) {
                case 'L' -> x--;
                case 'R' -> x++;
                case 'U' -> y++;
                case 'D' -> y--;
            }
            long key = encode(x, y);
            int current = i + 1;
            int prev = map.getOrDefault(key, -n);
            int len = current - prev;
            if (len < minLen) {
                l = prev;
                r = current;
                minLen = len;
            }
            map.put(key, current);
        }

        if (minLen == n + 1) {
            System.out.println(-1);
        } else {
            System.out.println((l + 1) + " " + r);
        }
    }

    public static void main(String[] args) throws Exception {
        int t = nextInt();
        for (int i = 0; i < t; i++) {
            solve();
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

    static String nextLine() throws IOException {
        st = null;
        return br.readLine();
    }

    static int[] nextIntArray(int n) throws IOException {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nextInt();
        }
        return nums;
    }

    static long[] nextLongArray(int n) throws IOException {
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nextLong();
        }
        return nums;
    }
}

