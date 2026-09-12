package io.kite.cf._1883G1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int solve() throws Exception {
        int n = nextInt(), m = nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        A[0] = m;
        for (int i = 1; i < n; i++) {
            A[i] = nextInt();
        }

        for (int i = 0; i < n; i++) {
            B[i] = nextInt();
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int matched = 0;
        int i = 0, j = 0;

        while (i < n && j < n) {
            if (A[i] < B[j]) {
                matched++;
                i++;
                j++;
            } else {
                j++;
            }
        }

        return n - matched;
    }

    public static void main(String[] args) throws Exception {
        int t = nextInt();

        for (int i = 1; i <= t; i++) {
            System.out.println(solve());
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


}

