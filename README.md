<p align="center">
  <img src="https://avatars.githubusercontent.com/u/102406248?v=4" width="100" height="100" style="border-radius: 50%;" alt="Manu577228" />
</p>

<h1 align="center">Advanced Number Theory Algorithms · Java</h1>

<p align="center">
  <em>A rigorous, high-performance library of classical and modern number-theoretic algorithms</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-purple?style=flat-square&logo=java" />
  <img src="https://img.shields.io/badge/Build-Maven-teal?style=flat-square&logo=apachemaven" />
  <img src="https://img.shields.io/badge/Core-O(log%20n)-orange?style=flat-square" />
  <img src="https://img.shields.io/badge/Domain-Number%20Theory-coral?style=flat-square" />
  <img src="https://img.shields.io/badge/License-MIT-blue?style=flat-square" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/author-Manu577228-purple?style=flat-square" />
  <img src="https://img.shields.io/badge/commit-6d3f611-gray?style=flat-square" />
</p>

---

## ∑ What is this?

A comprehensive Java library implementing the most elegant and battle-tested algorithms from number theory — from ancient Euclidean methods to modern cryptographic primitives. Every algorithm is carefully implemented for **correctness**, **performance**, and **educational clarity**.

Whether you're building a cryptographic system, solving competitive programming problems, or exploring the mathematics of integers — this library is your foundation.

---

## ⚙ Algorithms Included

| Algorithm | Description | Complexity |
|---|---|---|
| **Euclidean GCD** (extended & classic) | Foundation for modular arithmetic and RSA | `O(log min(a,b))` |
| **Modular Exponentiation** | Square-and-multiply; core to Diffie-Hellman & RSA | `O(log exp)` |
| **Miller-Rabin Primality** | Probabilistic test with configurable witness count | `O(k·log²n)` |
| **Chinese Remainder Theorem** | Reconstruct integers from residues | `O(n·log n)` |
| **Euler's Totient φ(n)** | Count integers coprime to n; powers RSA key generation | `O(√n)` |
| **Sieve of Eratosthenes** | Segmented & linear sieves, up to 10⁸ | `O(n log log n)` |
| **Modular Inverse** | Via extended Euclidean & Fermat's little theorem | `O(log n)` |
| **Discrete Logarithm** | Baby-step giant-step for solving g^x ≡ h (mod p) | `O(√p)` |
| **Legendre / Jacobi Symbol** | Quadratic residue testing; input to Tonelli-Shanks | `O(log n)` |

---

## 💡 See It in Action

```java
package org.bharadwaj.tT;

import org.bharadwaj.tT.primes.MillerRabin;
import org.bharadwaj.tT.modular.ModularArithmetic;
import org.bharadwaj.tT.sieve.SegmentedSieve;

public class Demo {

    public static void main(String[] args) {

        // Test probable primality — k=40 witnesses
        long p = 998_244_353L;
        boolean isPrime = MillerRabin.isPrime(p, 40);
        // → true  (NTT-friendly prime)

        // Modular exponentiation: 2^(p-1) mod p
        long result = ModularArithmetic.modPow(2L, p - 1, p);
        // → 1  (Fermat's little theorem ✓)

        // Generate all primes up to 10^6
        int[] primes = SegmentedSieve.generate(1_000_000);
        System.out.println("Prime count: " + primes.length);
        // → 78498
    }
}
```

---

## 📁 Project Structure

```
src/main/java/org/bharadwaj/tT/
├── primes/
│   ├── MillerRabin.java
│   └── SegmentedSieve.java
├── modular/
│   ├── ModularArithmetic.java
│   └── ChineseRemainder.java
├── gcd/
│   └── EuclideanGCD.java
├── discrete/
│   └── BabyStepGiantStep.java
├── symbols/
│   └── JacobiSymbol.java
└── totient/
    └── EulerTotient.java
```

---

## 🚀 Getting Started

**1. Clone the repository**
```bash
git clone https://github.com/Manu577228/Advanced-Number-Theory-Algorithms-Java.git
```

**2. Navigate into the project**
```bash
cd Advanced-Number-Theory-Algorithms-Java
```

**3. Build with Maven**
```bash
mvn clean install
```

**4. Run the demo**
```bash
mvn exec:java -Dexec.mainClass="org.bharadwaj.tT.Demo"
```

---

## 🔐 Where to Use This

- **Cryptography** — RSA, Diffie-Hellman, and elliptic curve primitives all rest on number theory.
- **Competitive Programming** — Codeforces, ICPC, and LeetCode problems that demand fast modular arithmetic.
- **Academic Research** — Verified, readable implementations perfect for teaching and experimentation.

---

## 👤 Author

<p>
  <img src="https://avatars.githubusercontent.com/u/102406248?v=4" width="48" height="48" style="border-radius:50%;vertical-align:middle;" />
  &nbsp;&nbsp;<strong>Manu Bharadwaj</strong> &nbsp;·&nbsp; <a href="https://github.com/Manu577228">@Manu577228</a>
</p>

---

## 📄 License

Released under the **MIT License** — free to use, modify, and distribute.

Commit `6d3f611` · Initial release

---

<p align="center">
  <em>∑ ∏ ∫ π φ ζ — where beauty meets computation</em>
</p>
