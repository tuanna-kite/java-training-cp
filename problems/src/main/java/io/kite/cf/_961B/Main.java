package io.kite.cf._961B;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        int n = nextInt(), k = nextInt();
        int[] A = nextIntArray(n);
        int[] T = nextIntArray(n);

        int[] extra = new int[n + 1];
        int base = 0;

        for (int i = 0; i < n; i++) {
            base += T[i] * A[i];
            extra[i + 1] = extra[i] + A[i] * (1 - T[i]);
        }

        int maxExtra = 0;

        for (int l = 0; l + k <= n; l++) {
            maxExtra = Math.max(
                    maxExtra,
                    extra[l + k] - extra[l]
            );
        }

        System.out.println(base + maxExtra);
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

    static String nextLine() throws IOException {
        st = null;
        return br.readLine();
    }
}

