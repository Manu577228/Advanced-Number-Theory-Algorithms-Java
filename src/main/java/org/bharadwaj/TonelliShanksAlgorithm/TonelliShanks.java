package org.bharadwaj.TonelliShanksAlgorithm;

//The Tonelli–Shanks algorithm is used to
//find a square root of a number modulo a prime,
//i.e., solve: x^2 ≡ n(mod p)
//
//when a solution exists (i.e., n is a quadratic residue mod p).

import java.io.*;
import java.util.*;

public class TonelliShanks {

    static long modPow(long a, long b, long mod) {
        long r = 1;
        a %= mod;

        while (b > 0) {
            if ((b & 1) == 1) r = (r * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }

        return r;
    }

    static long tonelliShanks(long n, long p) {
        if (modPow(n, (p - 1) / 2, p) != 1) return -1;

        long q = p - 1;
        long s = 0;
        while ((q & 1) == 0) {
            q >>= 1;
            s++;
        }

        long z = 2;
        while (modPow(z, (p - 1) / 2, p) != p - 1) z++;

        long c = modPow(z, q, p);
        long x = modPow(n, (q + 1) / 2, p);
        long t = modPow(n, q, p);
        long m = s;

        while (t != 1) {
            long i = 0;
            long temp = t;

            while (temp != 1) {
                temp = (temp * temp) % p;
                i++;
            }

            long b = modPow(c, 1L << (m - i - 1), p);

            x = (x * b) % p;
            t = (t * b % p * b) % p;
            c = (b * b) % p;
            m = i;
        }

        return x;
    }

    public static void main(String[] args) throws Exception {

        long n = 10;
        long p = 13;

        long root = tonelliShanks(n, p);

        if (root == -1) {
            System.out.println("No solution exists");
        } else {
            System.out.println("Square root of " + n + " mod " + p + " = " + root);
            System.out.println("Second root = " + (p - root));
        }
    }
}
