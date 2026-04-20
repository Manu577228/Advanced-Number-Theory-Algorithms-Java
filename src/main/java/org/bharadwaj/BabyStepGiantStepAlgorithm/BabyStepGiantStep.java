package org.bharadwaj.BabyStepGiantStepAlgorithm;

//The Baby-Step Giant-Step algorithm is used to solve the
//discrete logarithm problem:
//
//a^x ≡ b (mod m)
//
//It efficiently finds x by splitting the search into
//two parts (baby steps and giant steps),
//reducing time complexity from O(√m) to about O(√m)
//space and time compared to brute force O(m).

import java.io.*;
import java.util.*;

public class BabyStepGiantStep {

    static long pow(long a, long b, long mod) {
        long res = 1;
        a %= mod;

        while (b > 0) {
            if ((b & 1) == 1)
                res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(System.out);

        long a = 2;
        long b = 5;
        long m = 13;

        long n = (long) Math.sqrt(m) + 1;

        HashMap<Long, Long> map = new HashMap<>();

        long val = 1;
        for (long j = 0; j < n; j++) {
            if (!map.containsKey(val))
                map.put(val, j);
            val = (val * a) % m;
        }

        long inv = pow(a, m - 1 - n, m);
        long cur = b;

        for (long i = 0; i <= n; i++) {
            if (map.containsKey(cur)) {
                long j = map.get(cur);
                long x = i * n + j;
                out.println("Solution x = " + x);
                out.flush();
                return;
            }

            cur = (cur * inv) % m;
        }

        out.println("No Solution");
        out.flush();
    }
}
