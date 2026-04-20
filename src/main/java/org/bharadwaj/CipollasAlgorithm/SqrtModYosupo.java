package org.bharadwaj.CipollasAlgorithm;

import java.io.*;
import java.util.*;

public class SqrtModYosupo {
    static long p;
    static long w;
    static Random rnd = new Random(12345);

    static long[] mul(long[] a, long[] b) {
        long r = (a[0] * b[0] % p + a[1] * b[1] % p * w % p) % p;
        long i = (a[0] * b[1] % p + a[1] * b[0] % p) % p;
        return new long[]{
                r, i
        };
    }

    static long[] pow(long[] base, long exp) {
        long[] res = {1, 0};
        while (exp > 0) {
            if ((exp & 1) == 1) res = mul(res, base);
            base = mul(base, base);
            exp >>= 1;
        }

        return res;
    }

    static long modPow(long b, long e, long m) {
        long r = 1;
        b %= m;
        while (e > 0) {
            if ((e & 1) == 1) r = r * b % m;
            b = b * b % m;
            e >>= 1;
        }

        return r;
    }

    static long cipolla(long n) {
        if (n == 0) return 0;
        if (p == 2) return n % 2;

        if (modPow(n, (p - 1) / 2, p) != 1) return -1;

        long t;

        while (true) {
            t = rnd.nextLong() % p;
            if (t < 0) t += p;

            w = ((t * t % p - n) % p + p) % p;

            if (w == 0) continue;

            if (modPow(w, (p - 1) / 2, p) == p - 1) break;
        }

        long[] res = pow(new long[]{t, 1}, (p + 1) / 2);

        return res[0];
    }

    static DataInputStream din = new DataInputStream(new BufferedInputStream(System.in));

    private static long readLong() throws IOException {
        long ret = 0;
        int b = din.read();
        while (b < '0' || b > '9') b = din.read();
        while (b >= '0' && b <= '9') {
            ret = ret * 10 + (b - '0');
            b = din.read();
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        int T = (int) readLong();
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            long y = readLong();
            p = readLong();
            long ans = cipolla(y);
            // return smaller root
            long other = (p - ans) % p;
            sb.append(Math.min(ans, other)).append('\n');
        }
        System.out.print(sb);
    }
}
