package org.bharadwaj.FastWalshHadamardTransformAlgorithm;

import java.io.*;
import java.util.*;

public class BitwiseXorConvolutionLibraryChecker {
    static final long MOD = 998244353;
    static final long INV2 = (MOD + 1) >> 1;

    static void fwht(long[] a, boolean inv) {
        int n = a.length;

        for (int len = 1; len < n; len <<= 1) {
            for (int i = 0; i < n; i += len << 1) {
                for (int j = 0; j < len; j++) {
                    long u = a[i + j];
                    long v = a[i + j + len];

                    a[i + j] = (u + v) % MOD;
                    a[i + j + len] = (u - v + MOD) % MOD;

                    if (inv) {
                        a[i + j] = a[i + j] * INV2 % MOD;
                        a[i + j + len] = a[i + j + len] * INV2 % MOD;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int sz = 1 << N;

        long[] a = new long[sz];
        long[] b = new long[sz];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sz; i++) a[i] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sz; i++) b[i] = Long.parseLong(st.nextToken());

        fwht(a, false);
        fwht(b, false);

        for (int i = 0; i < sz; i++) a[i] = a[i] * b[i] % MOD;

        fwht(a, true);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sz; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb);
    }
}
