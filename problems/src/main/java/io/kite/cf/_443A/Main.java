package io.kite.cf._443A;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void solve() throws Exception {
        String inp = nextLine();

        boolean[] seens = new boolean[26];
        int count = 0;

        for (int i = 0; i < inp.length(); i++) {
            int ch = inp.charAt(i);
            if (ch < 'a' || ch > 'z') {
                continue;
            }

            ch -= 'a';
            if (!seens[ch]) count++;
            seens[ch] = true;
        }

        System.out.println(count);
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

    public static void main(String[] args) throws Exception {
        solve();
    }
}

