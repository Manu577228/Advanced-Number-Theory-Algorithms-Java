package org.bharadwaj.FastWalshHadamardTransformAlgorithm;

//Fast Walsh–Hadamard Transform (FWHT) is an algorithm
//used to efficiently compute convolutions under XOR, AND, or
//OR operations.
//It transforms an array into a frequency-like domain in O(n log n) time.
//Most commonly used for XOR convolution in competitive programming.

import java.io.*;

public class FastWalshHadamardTransform {
    static void fwht(long[] a, int n, boolean inverse) {
        for (int len = 1; len < n; len <<= 1) {
            for (int i = 0; i < n; i += (len << 1)) {
                for (int j = 0; j < len; j++) {
                    long u = a[i + j];
                    long v = a[i + j + len];

                    a[i + j] = u + v;
                    a[i + j + len] = u - v;
                }
            }
        }

        if (inverse) {
            for (int i = 0; i < n; i++) {
                a[i] /= n;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        long[] a = {1, 2, 3, 4};
        int n = a.length;

        fwht(a, n, false);

        out.println("FWHT Result:");
        for (int i = 0; i < n; i++) {
            out.print(a[i] + " ");
        }

        out.println();

        fwht(a, n, true);

        out.println("After Inverse FWHT:");
        for (int i = 0; i < n; i++) {
            out.print(a[i] + " ");
        }

        out.flush();
    }
}
