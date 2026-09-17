package io.kite.cf._236A;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        String inp = next();
        boolean[] seen = new boolean[26];
        int count = 0;
        for (int i = 0; i < inp.length(); i++) {
            int ch = inp.charAt(i) - 'a';
            if (!seen[ch]) count++;
            seen[ch] = true;
        }

        if (count % 2 == 0) { System.out.println("CHAT WITH HER!"); }
        else { System.out.println("IGNORE HIM!"); }
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
