package org.bharadwaj.NumberTheoreticTransformAlgorithm;

//Number Theoretic Transform (NTT) is a discrete transform
//similar to FFT, but performed over a finite
//field using modular arithmetic.
//It is mainly used for fast polynomial
//multiplication without floating-point errors.

import java.io.*;

public class NumberTheoreticTransform {
    static final int MOD = 998244353;
    static final int Root = 3;

    static long power(long a, long b) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) == 1) result = (result * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }

        return result;
    }

    static void bitReverse(int[] a, int n) {
        int j = 0;
        for (int i = 1; i < n; i++) {
            int bit = n >> 1;
            while ((j & bit) != 0) {
                j ^= bit;
                bit >>= 1;
            }

            j |= bit;

            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
    }

    static void ntt(int[] a, int n, boolean invert) {
        bitReverse(a, n);
        for (int len = 2; len <= n; len <<= 1) {

            long wlen = power(Root, (MOD - 1) / len);

            if (invert) {
                wlen = power(wlen, MOD - 2);
            }

            for (int i = 0; i < n; i += len) {
                long w = 1;
                for (int j = 0; j < len / 2; j++) {
                    int u = a[i + j];
                    int v = (int) ((a[i + j + len / 2] * w) % MOD);

                    a[i + j] = (u + v) % MOD;
                    a[i + j + len / 2] = (u - v + MOD) % MOD;

                    w = (w * wlen) % MOD;
                }
            }
        }

        if (invert) {
            long inv_n = power(n, MOD - 2);
            for (int i = 0; i < n; i++) {
                a[i] = (int) (a[i] * inv_n % MOD);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        PrintWriter out = new PrintWriter(System.out);

        int[] A = {1, 2};
        int[] B = {3, 4};

        int n = 1;

        while (n < A.length + B.length) n <<= 1;

        int[] fa = new int[n];
        int[] fb = new int[n];

        for (int i = 0; i < A.length; i++) fa[i] = A[i];
        for (int i = 0; i < B.length; i++) fb[i] = B[i];

        ntt(fa, n, false);
        ntt(fb, n, false);

        for (int i = 0; i < n; i++) {
            fa[i] = (int)((long)fa[i] * fb[i] % MOD);
        }

        ntt(fa, n, true);

        for (int i = 0; i < n; i++) {
            out.print(fa[i] + " ");
        }

        out.flush();
    }
}
