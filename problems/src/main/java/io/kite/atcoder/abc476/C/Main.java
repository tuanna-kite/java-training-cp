package io.kite.atcoder.abc476.C;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static void solve() throws Exception {
        int n = nextInt();
        int[] A = nextIntArray(n);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < 3; i++) pq.add(A[i]);

        for (int i = 3; i <= n; i++) {
            System.out.println(pq.peek());
            if(i == n) break;

            if (A[i] >= pq.peek()) {
                pq.poll();
                pq.add(A[i]);
            }
        }
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
