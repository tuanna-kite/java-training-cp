package io.kite.cf.div2.round1121.a;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class Element {
        int i; int val;

        public Element(int i, int val) {
            this.i = i;
            this.val = val;
        }
    }

    static boolean check(int[] A) {
        ArrayList<Element> wrongs = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if(A[i] != i+1) {
                wrongs.add(new Element(i, A[i]));
            }
        }

        int l = 0, r = wrongs.size() - 1;
        while (l <= r) {
            var e1 = wrongs.get(l);
            var e2 = wrongs.get(r);
            var tmp = e1.val;
            e1.val = e2.val;
            e2.val = tmp;
            l++;
            r--;
        }
        for(var elm: wrongs) {
            A[elm.i] = elm.val;
        }

        for (int i = 0; i < A.length; i++) {
            if(A[i] != i+1) {
                return false;
            }
        }

        return true;
    }

    static void solve() throws Exception {
        int n = nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = nextInt();
        }

        if (check(A)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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

    public static void main(String[] args) throws Exception {
        int t = nextInt();
        for (int i = 0; i < t; i++) {
            solve();
        }
    }
}

