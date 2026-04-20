package org.bharadwaj.BerlekampMasseyAlgorithm;

import java.io.*;
import java.util.*;

public class ASquirrelsLifeDMOJ {
    static final long MOD = 998244353;
    static int SK;

    static long mod(long x) {
        return ((x % MOD) + MOD) % MOD;
    }

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

    static long inv(long a) {
        return pow(a, MOD - 2);
    }

    static long[] berlekampMassey(long[] s) {
        int n = s.length;
        long[] C = {1}, B = {1};
        long b = 1;
        int L = 0, x = 1;

        for (int i = 0; i < n; i++) {
            long d = 0;

            for (int j = 0; j < C.length; j++)
                d = mod(d + C[j] * s[i - j]);

            if (d == 0) {
                x++;
                continue;
            }

            long[] T = C.clone();
            long coef = mod(d * inv(b));

            long[] newC = new long[Math.max(C.length, B.length + x)];

            for (int j = 0; j < C.length; j++)
                newC[j] = mod(newC[j] + C[j]);

            for (int j = 0; j < B.length; j++)
                newC[j + x] = mod(newC[j + x] - coef * B[j]);

            C = newC;

            if (2 * L <= i) {
                L = i + 1 - L;
                B = T;
                b = d;
                x = 1;
            } else x++;
        }

        long[] rec = new long[C.length - 1];

        for (int i = 1; i < C.length; i++)
            rec[i - 1] = mod(-C[i]);

        return rec;
    }

    static long[] polyMul(long[] A, long[] B, long[] rec) {
        int d = rec.length;
        long[] res = new long[2 * d - 1];

        for (int i = 0; i < d; i++)
            for (int j = 0; j < d; j++)
                res[i + j] = mod(res[i + j] + A[i] * B[j]);


        for (int i = 2 * d - 2; i >= d; i--) {
            long c = res[i];
            if (c == 0) continue;
            for (int j = 0; j < d; j++)
                res[i - d + j] = mod(res[i - d + j] + c * rec[d - 1 - j]);
        }

        return Arrays.copyOf(res, d);
    }

    static long kitamasa(long[] rec, long[] init, long n) {
        int d = rec.length;

        if (d == 0) return 0;
        if (n < d) return init[(int) n];

        long[] base = new long[d];

        if (d == 1) base[0] = rec[0];
        else base[1] = 1;

        long[] r = new long[d];
        r[0] = 1;

        long m = n;

        while (m > 0) {
            if ((m & 1) == 1) r = polyMul(r, base, rec);
            base = polyMul(base, base, rec);
            m >>= 1;
        }

        long ans = 0;

        for (int i = 0; i < d; i++)
            ans = mod(ans + r[i] * init[i]);

        return ans;
    }

    static int encodeState(int node, int v2, int v3, int acorn) {
        return node * 4 * SK + v2 * 2 * SK + v3 * SK + acorn;
    }

    static int[] decodeState(int s) {
        int acorn = s % SK;
        s /= SK;
        int v3 = s % 2;
        s /= 2;
        int v2 = s % 2;
        s /= 2;
        return new int[]{
                s, v2, v3, acorn
        };
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long E = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        SK = K + 1;

        boolean[][] adj = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < N; j++) adj[i][j] = line.charAt(j) == '1';
        }

        int STATES = N * 4 * SK;
        int terms = 2 * STATES + 20;

        long[][] dp = new long[2][STATES];
        dp[0][encodeState(0, 0, 0, 0)] = 1;

        long[] seq = new long[terms];

        for (int t = 0; t < terms; t++) {
            int cur = t & 1, nxt = 1 - cur;
            Arrays.fill(dp[nxt], 0);
            seq[t] = dp[cur][encodeState(N - 1, 1, 1, K)];
            for (int s = 0; s < STATES; s++) {
                if (dp[cur][s] == 0) continue;
                int[] dec = decodeState(s);
                int node = dec[0], v2 = dec[1], v3 = dec[2], acorn = dec[3];
                if (node == N - 1) continue;
                for (int nn = 0; nn < N; nn++) {
                    if (!adj[node][nn]) continue;
                    int nv2 = v2 | (nn == 1 ? 1 : 0);
                    int nv3 = v3 | (nn == 2 ? 1 : 0);
                    int na = acorn + (nn == 3 ? 1 : 0);
                    if (na > K) continue;
                    int ns = encodeState(nn, nv2, nv3, na);
                    dp[nxt][ns] = mod(dp[nxt][ns] + dp[cur][s]);
                }
            }
        }

        long[] rec = berlekampMassey(seq);
        System.out.println(kitamasa(rec, seq, E - 1));
    }
}


