package io.kite.atcoder.abc476.B;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        int n = nextInt();
        String S = next(), T = next();
        int j = 0;
        boolean valid = true;
        for (int i = 0; i < n; i++) {
            char ch = S.charAt(i);
            char chT = T.charAt(j);
            if (ch != chT && chT != '*') {
                valid = false;
                break;
            }
            j++;
        }

        if (valid) System.out.println("Yes");
        else { System.out.println("No"); }
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

