package org.bharadwaj.BinaryTrieAlgorithm;

import java.io.*;
import java.util.*;

public class VasiliysMultiset706DCodeforces {
    static int[] ch = new int[6_000_001 * 2];
    static int[] cnt = new int[6_000_001];
    static int sz = 1;

    static void update(int x, int v) {
        int curr = 0;

        for (int i = 29; i >= 0; i--) {
            int b = (x >> i) & 1;

            if (ch[curr * 2 + b] == 0) ch[curr * 2 + b] = sz++;

            curr = ch[curr * 2 + b];

            cnt[curr] += v;
        }
    }

    static int query(int x) {
        int curr = 0;
        int res = 0;

        for (int i = 29; i >= 0; i--) {
            int want = 1 - ((x >> i) & 1);

            if (ch[curr * 2 + want] != 0 && cnt[ch[curr * 2 + want]] > 0) {
                res |= (1 << i);
                curr = ch[curr * 2 + want];
            } else {
                curr = ch[curr * 2 + (1 - want)];
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine().trim());
        update(0, 1);
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char op = st.nextToken().charAt(0);
            int x = Integer.parseInt(st.nextToken());
            if (op == '+') update(x, 1);
            else if (op == '-') update(x, -1);
            else sb.append(query(x)).append('\n');
        }
        System.out.print(sb);
    }
}
