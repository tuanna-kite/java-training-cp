package io.kite.cf._227B;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        int n = nextInt();
        int[] A = new int[n];
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            A[i] = nextInt();
            maxVal = Math.max(A[i], maxVal);
        }
        int m = nextInt();
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = nextInt();
        }

        int[] pos = new int[maxVal + 2];

        for (int i = 0; i < n; i++) {
            pos[A[i]] = i;
        }

        long s1 = 0, s2 = 0;

        for (var val : B) {
            s1 += (pos[val] + 1);
            s2 += (n - pos[val]);
        }

        System.out.println(s1 + " " + s2);
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
        solve();
    }
}

