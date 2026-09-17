package io.kite.cf._4C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        int n = nextInt();
        Map<String, Integer> counts = new HashMap<>((int) (n / 0.75f) + 1);
        for (int i = 0; i < n; i++) {
            String name = next();
            int freq = counts.getOrDefault(name, 0);
            if (freq == 0) {
                System.out.println("OK");
            } else {
                String nameWithSuffix = name + freq;
                System.out.println(nameWithSuffix);
            }
            counts.put(name, freq + 1);
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
}
