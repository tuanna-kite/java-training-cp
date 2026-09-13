package io.kite.cf.div2.round1121.b;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        int n = nextInt();
        int m = nextInt();

        int[] A = new int[n];
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            A[i] = nextInt();
            maxVal = Math.max(maxVal, A[i]);
        }

        if (m == 1) {
            System.out.println(maxVal);
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(m - 1 ,(a, b) -> Integer.compare(b, a));
        long sum = 0;
        for (int i = 0; i < m - 1; i++) {
            sum += A[i];
            pq.add(A[i]);
        }

        long ans = Long.MIN_VALUE;
        for (int i = m-1; i < n; i++) {
            long currentAns = (long)m * A[i] - sum;
            ans = Math.max(ans, currentAns);
            if (!pq.isEmpty() && pq.peek() > A[i]) {
                sum = sum - pq.peek() + A[i];
                pq.remove();
                pq.add(A[i]);
            }
        }

        System.out.println(ans);
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

    public static void main(String[] args) throws Exception {
        int t = nextInt();
        for (int i = 0; i < t; i++) {
            solve();
        }
    }
}

