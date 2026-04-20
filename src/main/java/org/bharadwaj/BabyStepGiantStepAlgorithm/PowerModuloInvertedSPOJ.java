package org.bharadwaj.BabyStepGiantStepAlgorithm;

import java.io.*;
import java.util.*;

public class PowerModuloInvertedSPOJ {


    static long powMod(long base, long exp, long mod) {
        if (mod == 1) return 0;

        long result = 1;

        base %= mod;

        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }

        return result;
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long[] extGcd(long a, long b) {
        if (b == 0) return new long[]{
                a, 1, 0
        };
        long[] r = extGcd(b, a % b);
        return new long[]{
                r[0], r[2], r[1] - (a / b) * r[2]
        };
    }

    static long modInverse(long a, long m) {
        if (m == 1) return 0;
        long[] r = extGcd(((a % m) + m) % m, m);
        return (r[1] % m + m) % m;
    }

    static long bsgs(long x, long z, long k) {
        if (z == 1) return 0;

        k %= z;

        long curr = 1 % z;
        for (long i = 0; i <= 100; i++) {
            if (curr == k) return i;
            curr = curr * (x % z) % z;
        }

        long d = 0;
        long acc = 1;
        long zz = z;
        long kk = k;

        long g;

        while ((g = gcd(x % zz, zz)) != 1) {
            if (kk % g != 0) return -1;
            kk /= g;
            zz /= g;
            d++;
            acc = acc * ((x / g) % zz) % zz;
        }

        kk = kk % zz * modInverse(acc, zz) % zz;

        int m = (int) Math.ceil(Math.sqrt(zz)) + 1;

        HashMap<Long, Integer> table = new HashMap<>(m * 2);

        long baby = kk % zz;
        long xModZz = x % zz;

        for (int b = 0; b < m; b++) {
            table.put(baby, b);
            baby = baby * xModZz % zz;
        }

        long xm = powMod(x, m, zz);
        long giant = xm;

        for (int a = 1; a <= m + 1; a++) {
            Integer b = table.get(giant);
            if (b != null) {
                long yPrime = (long) a * m - b;
                if (yPrime >= 0) return yPrime + d;
            }

            giant = giant * xm % zz;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            StringTokenizer st = new StringTokenizer(line);
            long x = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            if (x == 0 && z == 0 && k == 0) break;

            long ans = bsgs(x, z, k);
            sb.append(ans == -1 ? "No Solution" : ans).append('\n');
        }
        System.out.print(sb);
    }
}
