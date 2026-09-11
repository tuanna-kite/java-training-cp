package io.kite.cf._1520D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static long solve() throws Exception {
        int n = nextInt();
        String str = next();
        List<Integer> sheeps = new ArrayList<>(n);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '*') sheeps.add(i);
        }

        if (sheeps.size() <= 1) {
            return 0;
        }

        for (int i = 0; i < sheeps.size(); i++) {
            sheeps.set(i, sheeps.get(i) - i);
        }

        int med1 = sheeps.get(sheeps.size() / 2 - 1);
        int med2 = sheeps.get(sheeps.size() / 2);

        long sum1 = 0, sum2 = 0;

        for (var sheep : sheeps) {
            sum1 += Math.abs(sheep - med1);
            sum2 += Math.abs(sheep - med2);
        }

        return Math.min(sum1, sum2);
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
        while (t-- > 0) { System.out.println(solve()); }
    }
}

