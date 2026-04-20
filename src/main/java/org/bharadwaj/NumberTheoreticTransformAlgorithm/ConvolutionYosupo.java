package org.bharadwaj.NumberTheoreticTransformAlgorithm;

import java.io.*;
import java.util.*;

public class ConvolutionYosupo {
    static final long MOD = 998244353;
    static final long G = 3;

    static long pow(long a, long e) {
        long r = 1;
        a %= MOD;
        while (e > 0) {
            if ((e & 1) == 1) r = r * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }

        return r;
    }

    static void ntt(long[] a, boolean inv) {
        int n = a.length;

        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            for (; (j & bit) != 0; bit >>= 1) j ^= bit;
            j ^= bit;
            if (i < j) {
                long t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            long w = inv ? pow(G, MOD - 1 - (MOD - 1) / len) : pow(G, (MOD - 1) / len);

            for (int i = 0; i < n; i += len) {
                long wn = 1;

                for (int j = 0; j < len / 2; j++) {
                    long u = a[i + j];
                    long v = a[i + j + len / 2] * wn % MOD;

                    a[i + j] = (u + v) % MOD;
                    a[i + j + len / 2] = (u - v + MOD) % MOD;

                    wn = wn * w % MOD;
                }
            }
        }

        if (inv) {
            long ni = pow(n, MOD - 2);
            for (int i = 0; i < n; i++) a[i] = a[i] * ni % MOD;
        }
    }

    static long[] multiply(long[] a, long[] b) {
        int result = a.length + b.length - 1;
        int n = 1;
        while (n < result) n <<= 1;
        long[] fa = Arrays.copyOf(a, n), fb = Arrays.copyOf(b, n);
        ntt(fa, false); ntt(fb, false);
        for (int i = 0; i < n; i++) fa[i] = fa[i] * fb[i] % MOD;
        ntt(fa, true);
        return Arrays.copyOf(fa, result);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] a = new long[N];
        long[] b = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            b[i] = Long.parseLong(st.nextToken());
        }

        long[] c = multiply(a, b);

        for (int i = 0; i < c.length; i++) {
            if (i > 0) out.print(" ");
            out.print(c[i]);
        }

        out.println();
        out.flush();
    }
}
