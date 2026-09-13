package io.kite.cf._1426D;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static FastScanner io = new FastScanner();

    static void solve() throws Exception {
        int n = io.nextInt();
        long[] A = new long[n];

        Map<Long, Integer> lookup = new HashMap<>();
        lookup.put(0L, -1);
        int ans = 0;
        long sum = 0;
        int lastSeg = 0;

        for (int i = 0; i < n; i++) {
            A[i] = io.nextLong();
            sum += A[i];
            int foundIdx = lookup.getOrDefault(sum, -1000);
            if (foundIdx + 1 >= lastSeg) {
                ans++;
                lastSeg = i;
            }
            lookup.put(sum, i);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solve();
    }
}

final class FastScanner {
    private final java.io.InputStream in = System.in;
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0;
    private int len = 0;

    private int read() throws IOException {
        if (ptr >= len) {
            len = in.read(buffer);
            ptr = 0;

            if (len <= 0) {
                return -1;
            }
        }

        return buffer[ptr++];
    }

    public long nextLong() throws IOException {
        int c;

        do {
            c = read();
        } while (c <= ' ');

        boolean negative = false;

        if (c == '-') {
            negative = true;
            c = read();
        }

        long value = 0;

        while (c > ' ') {
            value = value * 10 + (c - '0');
            c = read();
        }

        return negative ? -value : value;
    }

    public int nextInt() throws IOException {
        return (int) nextLong();
    }
}

