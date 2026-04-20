import java.util.*;
import java.io.*;

 class Main {

    static void fwht(long[] a) {
        for (int len = 1; len < a.length; len <<= 1)
            for (int i = 0; i < a.length; i += len << 1)
                for (int j = 0; j < len; j++) {
                    long u = a[i + j], v = a[i + j + len];
                    a[i + j] = u + v;
                    a[i + j + len] = u - v;
                }
    }

    static void ifwht(long[] a) {
        fwht(a);
        long sz = a.length;
        for (int i = 0; i < a.length; i++) a[i] /= sz;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            int mask = 0;
            for (int j = 0; j < k; j++)
                if (s.charAt(j) == '1') mask |= (1 << j);
            masks[i] = mask;
        }

        int HALF = k / 2;
        int HIB = k - HALF;
        int loSz = 1 << HALF;
        int hiSz = 1 << HIB;
        int loMsk = loSz - 1;

        Map<Integer, long[]> cntMap = new HashMap<>();
        for (int m : masks) {
            int hi = m >> HALF, lo = m & loMsk;
            cntMap.computeIfAbsent(hi, x -> new long[loSz])[lo]++;
        }

        Map<Integer, long[]> F = new HashMap<>();
        for (var e : cntMap.entrySet()) {
            long[] row = e.getValue().clone();
            fwht(row);
            F.put(e.getKey(), row);
        }

        int[] hiKeys = F.keySet().stream().mapToInt(x -> x).toArray();

        @SuppressWarnings("unchecked")
        List<Integer>[] hiByPop = new List[HIB + 1];
        for (int p = 0; p <= HIB; p++) hiByPop[p] = new ArrayList<>();
        for (int H = 0; H < hiSz; H++) hiByPop[Integer.bitCount(H)].add(H);

        @SuppressWarnings("unchecked")
        List<Integer>[] loByPop = new List[HALF + 1];
        for (int p = 0; p <= HALF; p++) loByPop[p] = new ArrayList<>();
        for (int L = 0; L < loSz; L++) loByPop[Integer.bitCount(L)].add(L);

        for (int D = 0; D <= k; D++) {
            for (int p = 0; p <= Math.min(D, HIB); p++) {
                int q = D - p;
                if (q > HALF) continue;

                for (int H : hiByPop[p]) {
                    long[] conv = new long[loSz];
                    for (int h : hiKeys) {
                        long[] fh = F.get(h);
                        long[] fhH = F.get(h ^ H);
                        if (fhH == null) continue;
                        for (int x = 0; x < loSz; x++)
                            conv[x] += fh[x] * fhH[x];
                    }
                    ifwht(conv);

                    for (int L : loByPop[q]) {
                        long c = conv[L];
                        if (H == 0 && L == 0) c -= n;
                        if (c > 0) {
                            System.out.println(D);
                            return;
                        }
                    }
                }
            }
        }
    }
}