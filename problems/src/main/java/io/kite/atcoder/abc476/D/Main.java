package io.kite.atcoder.abc476.D;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        int n = nextInt();
        int m = nextInt();
        long k = nextLong();

        long x = nextLong();
        long y = nextLong();

        long[] a = nextLongArray(n);
        long[] b = nextLongArray(m);

        Arrays.sort(a);
        Arrays.sort(b);

        long[] prefixA = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixA[i + 1] = prefixA[i] + a[i];
        }

        long[] prefixB = new long[m + 1];
        long[] prefixBills = new long[m + 1];

        for (int i = 0; i < m; i++) {
            prefixB[i + 1] = prefixB[i] + b[i];

            long bills = (b[i] + k - 1) / k;
            prefixBills[i + 1] = prefixBills[i] + bills;
        }

        long totalMoney = x + y * k;

        int ans = 0;
        int d = n;

        for (int q = 0; q <= m; q++) {
            if (prefixBills[q] > y) {
                break;
            }

            while (d > 0 &&
                    prefixA[d] + prefixB[q] > totalMoney) {
                d--;
            }

            if (prefixA[d] + prefixB[q] <= totalMoney) {
                ans = Math.max(ans, d + q);
            }
        }

        System.out.println(ans);
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

