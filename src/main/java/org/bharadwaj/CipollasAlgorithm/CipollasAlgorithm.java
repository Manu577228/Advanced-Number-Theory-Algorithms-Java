package org.bharadwaj.CipollasAlgorithm;

//Cipolla’s Algorithm computes the modular
//square root of a number n under a prime modulus p, i.e.,
//finds x such that
//x² ≡ n (mod p).
//It works efficiently using arithmetic in a
//quadratic extension field instead of brute force.

public class CipollasAlgorithm {
    static long p = 13;
    static long n = 10;

    static long power(long a, long b) {
        long res = 1;
        a %= p;

        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % p;
            a = (a * a) % p;
            b >>= 1;
        }

        return res;
    }

    static long legendre(long x) {
        return power(x, (p - 1) / 2);
    }

    static class Pair {
        long x, y;

        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static long w;

    static Pair multiply(Pair a, Pair b) {
        long newX = (a.x * b.x + a.y * b.y % p * w) % p;
        long newY = (a.x * b.y + a.y * b.x) % p;
        return new Pair(newX, newY);
    }

    static Pair powerPair(Pair a, long b) {
        Pair res = new Pair(1, 0);
        while (b > 0) {
            if ((b & 1) == 1) res = multiply(res, a);
            a = multiply(a, a);
            b >>= 1;
        }

        return res;
    }

    public static void main(String[] args) {

        if (legendre(n) != 1) {
            System.out.println("No Solution");
            return;
        }

        long a = 0;

        while (true) {
            a++;
            w = (a * a - n) % p;
            if (w < 0) w += p;

            if (legendre(w) == p - 1) break;
        }

        Pair base = new Pair(a, 1);
        Pair result = powerPair(base, (p + 1) / 2);

        long x = result.x % p;

        System.out.println("Square Root: " + x);
        System.out.println("Other Root: " + (p - x));
    }
}
