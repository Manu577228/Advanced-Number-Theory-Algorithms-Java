package org.bharadwaj.BerlekampMasseyAlgorithm;

//The Berlekamp–Massey Algorithm finds the shortest linear recurrence
//relation (minimal polynomial) that generates a given sequence over a field.
//In simple terms, it compresses a sequence into the
//smallest formula needed to reproduce it.

import java.io.*;
import java.util.*;

public class BerlekampMasseyAlgorithm {
    static long MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        long[] s = {1, 1, 2, 3, 5, 8};

        long[] res = berlekampMassey(s);

        System.out.println("Recurrence Coefficients:");
        for (long x : res) {
            System.out.print(x + " ");
        }
    }

    static long[] berlekampMassey(long[] s) {
        int n = s.length;

        long[] C = new long[n];
        long[] B = new long[n];

        C[0] = 1;
        B[0] = 1;

        int L = 0;
        int m = 1;
        long b = 1;

        for (int i = 0; i < n; i++) {
            long d = 0;

            for (int j = 0; j <= L; j++) {
                d = (d + C[j] * s[i - j]) % MOD;
            }

            if (d == 0) {
                m++;
            } else {
                long[] T = C.clone();
                long coef = d * modInverse(b) % MOD;

                for (int j = m; j < n; j++) {
                    C[j] = (C[j] - coef * B[j - m] % MOD + MOD) % MOD;
                }

                if (2 * L > i) {
                    m++;
                } else {
                    L = i + 1 - L;
                    B = T;
                    b = d;
                    m = 1;
                }
            }
        }

        long[] res = new long[L];
        for (int i = 0; i < L; i++) {
            res[i] = (MOD - C[i + 1]) % MOD;
        }

        return res;
    }

    static long modInverse(long x) {
        return power(x, MOD - 2);
    }

    static long power(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }

        return res;
    }
}
