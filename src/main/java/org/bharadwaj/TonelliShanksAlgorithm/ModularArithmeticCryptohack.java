package org.bharadwaj.TonelliShanksAlgorithm;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class ModularArithmeticCryptohack {
    static BigInteger tonelliShanks(BigInteger n, BigInteger p) {
        if (n.modPow(p.subtract(BigInteger.ONE).divide(BigInteger.TWO), p)
                .compareTo(BigInteger.ONE) != 0) return null;

        if (p.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) {
            return n.modPow(p.add(BigInteger.ONE).divide(BigInteger.valueOf(4)), p);
        }

        BigInteger q = p.subtract(BigInteger.ONE);
        int s = 0;

        while (q.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            q = q.divide(BigInteger.TWO);
            s++;
        }

        BigInteger z = BigInteger.TWO;
        while (z.modPow(p.subtract(BigInteger.ONE).divide(BigInteger.TWO), p)
                .equals(BigInteger.ONE)) {
            z = z.add(BigInteger.ONE);
        }

        BigInteger c = z.modPow(q, p);
        BigInteger r = n.modPow(q.add(BigInteger.ONE).divide(BigInteger.TWO), p);
        BigInteger t = n.modPow(q, p);
        int m = s;

        while (!t.equals(BigInteger.ONE)) {
            int i = 1;
            BigInteger temp = t.modPow(BigInteger.TWO, p);

            while (!temp.equals(BigInteger.ONE)) {
                temp = temp.modPow(BigInteger.TWO, p);
                i++;
            }

            BigInteger b = c.modPow(BigInteger.TWO.pow(m - i - 1), p);

            r = r.multiply(b).mod(p);
            t = t.multiply(b).multiply(b).mod(p);
            c = b.multiply(b).mod(p);
            m = i;
        }

        return r;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        BigInteger a = new BigInteger("8479994658316772151941616510097127087554541274812435112009425778595495359700244470400642403747058566807127814165396640215844192327900454116257979487432016769329970767046735091249898678088061634796559556704959846424131820416048436501387617211770124292793308079214153179977624440438616958575058361193975686620046439877308339989295604537867493683872778843921771307305602776398786978353866231661453376056771972069776398999013769588936194859344941268223184197231368887060609212875507518936172060702209557124430477137421847130682601666968691651447236917018634902407704797328509461854842432015009878011354022108661461024768");
        BigInteger p = new BigInteger("30531851861994333252675935111487950694414332763909083514133769861350960895076504687261369815735742549428789138300843082086550059082835141454526618160634109969195486322015775943030060449557090064811940139431735209185996454739163555910726493597222646855506445602953689527405362207926990442391705014604777038685880527537489845359101552442292804398472642356609304810680731556542002301547846635101455995732584071355903010856718680732337369128498655255277003643669031694516851390505923416710601212618443109844041514942401969629158975457079026906304328749039997262960301209158175920051890620947063936347307238412281568760161");

        BigInteger r = tonelliShanks(a, p);

        BigInteger r2 = p.subtract(r);

        BigInteger ans = r.min(r2);

        out.println(ans);
        out.flush();
    }
}