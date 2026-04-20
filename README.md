<p align="center">
  <img src="https://avatars.githubusercontent.com/u/102406248?v=4" alt="Manu Bharadwaj" width="140" style="border-radius:50%;" />
</p>

<h1 align="center">⚙️ Advanced Number Theory Algorithms · Java</h1>

<p align="center">
  <em>A rigorous, high-performance Java library of 6 advanced number-theoretic algorithms —<br/>
  from discrete logarithms to algebraic transforms, built for cryptographers and competitive programmers.</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-purple?style=flat-square&logo=openjdk" />
  <img src="https://img.shields.io/badge/Build-Maven-teal?style=flat-square&logo=apachemaven" />
  <img src="https://img.shields.io/badge/Algorithms-6-orange?style=flat-square" />
  <img src="https://img.shields.io/badge/Domain-Number%20Theory-coral?style=flat-square" />
  <img src="https://img.shields.io/badge/License-MIT-blue?style=flat-square" />
  <img src="https://img.shields.io/badge/Author-Manu577228-blueviolet?style=flat-square&logo=github" />
</p>

---

## ∑ What Is This?

A carefully curated Java library implementing **6 advanced Number Theory algorithms** — spanning cryptographic primitives, algebraic transforms, and modular arithmetic. Each algorithm lives in its own dedicated package under `org.bharadwaj.tT`, with clean, readable code and a unified `Main.java` entry point for demonstration.

Whether you're solving ICPC problems, building cryptographic systems, or diving deep into the mathematics of integers — this is your go-to reference.

---

## 📁 Project Structure

```
Advanced-Number-Theory-Algorithms-Java/
├── pom.xml
├── LICENSE
├── .gitignore
└── src/
    └── main/
        └── java/
            └── org/
                └── bharadwaj/
                    └── tT/
                        ├── BabyStepGiantStepAlgorithm/
                        ├── BerlekampMasseyAlgorithm/
                        ├── CipollasAlgorithm/
                        ├── FastWalshHadamardTransformAlgorithm/
                        ├── NumberTheoreticTransformAlgorithm/
                        ├── TonelliShanksAlgorithm/
                        └── Main.java
```

---

## 🧮 Algorithms Included

### 1. 👣 Baby-Step Giant-Step (BSGS)
**Package:** `BabyStepGiantStepAlgorithm`

Solves the **Discrete Logarithm Problem**: find `x` such that `g^x ≡ h (mod p)`.
A meet-in-the-middle technique that reduces brute-force `O(p)` down to `O(√p)` using hash maps.

- **Use case:** Cryptanalysis, Diffie-Hellman attacks, competitive programming
- **Complexity:** `O(√p)` time · `O(√p)` space

---

### 2. 📐 Berlekamp-Massey Algorithm
**Package:** `BerlekampMasseyAlgorithm`

Finds the **shortest Linear Feedback Shift Register (LFSR)** that generates a given binary or integer sequence — reconstructing the minimal polynomial of a sequence.

- **Use case:** Decoding linear error-correcting codes, sequence analysis, LFSR-based cryptography
- **Complexity:** `O(n²)`

---

### 3. 🔵 Cipolla's Algorithm
**Package:** `CipollasAlgorithm`

Computes **modular square roots**: finds `x` such that `x² ≡ n (mod p)` for a prime `p`.
Works in the extension field `𝔽p²` to handle cases where no solution exists in `𝔽p`.

- **Use case:** Elliptic curve cryptography, quadratic residue problems
- **Complexity:** `O(log p)`

---

### 4. ⚡ Fast Walsh-Hadamard Transform (FWHT)
**Package:** `FastWalshHadamardTransformAlgorithm`

Computes **AND / OR / XOR convolutions** in `O(n log n)` — the binary analog of the Fast Fourier Transform.
Transforms sequences for fast subset-sum convolution over boolean domains.

- **Use case:** XOR/AND/OR convolution, Codeforces Div. 1 problems, signal processing
- **Complexity:** `O(n log n)`

---

### 5. 🔢 Number Theoretic Transform (NTT)
**Package:** `NumberTheoreticTransformAlgorithm`

A **modular FFT** — performs polynomial multiplication exactly under a prime modulus, with zero floating-point error.
Uses NTT-friendly primes (e.g., `998244353`) and primitive roots.

- **Use case:** Exact polynomial multiplication, competitive programming, cryptographic protocols
- **Complexity:** `O(n log n)`

---

### 6. 🌀 Tonelli-Shanks Algorithm
**Package:** `TonelliShanksAlgorithm`

Solves `x² ≡ n (mod p)` for **arbitrary odd primes** `p`, including cases where `p ≡ 1 (mod 4)` where simpler formulas fail.
The gold-standard general-purpose modular square root algorithm.

- **Use case:** Elliptic curves (secp256k1), primality proofs, RSA variants
- **Complexity:** `O(log² p)` expected

---

## 🔗 Algorithm Relationships

```
Discrete Log              Modular Square Roots          Algebraic Transforms
────────────              ────────────────────          ────────────────────
BabyStepGiantStep  ──►   Cipolla's Algorithm            NTT  (polynomial multiply)
                          TonelliShanks                  FWHT (XOR/AND/OR convolve)

Sequence Analysis
─────────────────
BerlekampMassey    ──►   minimal LFSR reconstruction
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+

### Clone & Build

```bash
git clone https://github.com/Manu577228/Advanced-Number-Theory-Algorithms-Java.git
cd Advanced-Number-Theory-Algorithms-Java
mvn clean install
```

### Run the Demo

```bash
mvn exec:java -Dexec.mainClass="org.bharadwaj.tT.Main"
```

---

## 💡 Quick Usage Examples

```java
// ── Baby-Step Giant-Step ──────────────────────────────────────
// Solve: 3^x ≡ 7 (mod 11)
long x = BabyStepGiantStep.solve(3, 7, 11);
// → x = 5  (since 3^5 = 243 ≡ 7 mod 11 ✓)

// ── Number Theoretic Transform ────────────────────────────────
// Multiply polynomials [1,2,3] × [4,5,6] under mod 998244353
long[] a = {1, 2, 3};
long[] b = {4, 5, 6};
long[] product = NTT.multiply(a, b);
// → [4, 13, 28, 27, 18]

// ── Cipolla's Algorithm ───────────────────────────────────────
// Find x such that x² ≡ 5 (mod 11)
long root = Cipolla.sqrt(5, 11);
// → 4  (since 4² = 16 ≡ 5 mod 11 ✓)

// ── Tonelli-Shanks ────────────────────────────────────────────
// Find x such that x² ≡ 2 (mod 113)
long r = TonelliShanks.sqrt(2, 113);
// → 62  (since 62² ≡ 2 mod 113 ✓)

// ── Fast Walsh-Hadamard Transform ─────────────────────────────
// XOR convolution of [1,0,1] and [0,1,1]
int[] result = FWHT.xorConvolve(new int[]{1, 0, 1}, new int[]{0, 1, 1});
// → [1, 2, 0, 1]
```

---

## 🎯 Who Is This For?

| Audience | How This Helps |
|----------|----------------|
| 🏆 Competitive Programmers | Ready-to-use NTT, FWHT, BSGS templates for Codeforces / ICPC |
| 🔐 Security Engineers | Cryptographic primitives: Cipolla, Tonelli-Shanks, discrete log |
| 🎓 CS / Math Students | Annotated, readable implementations with full complexity analysis |
| 👩‍🏫 Educators | Perfect reference code for teaching advanced number theory topics |

---

## 🤝 Contributing

Contributions are welcome! Please follow the existing package naming convention: `<AlgorithmName>Algorithm/`.

```bash
# Fork → Branch → Commit → Pull Request
git checkout -b feature/PollardRho
git commit -m "Add: Pollard's Rho factorization"
git push origin feature/PollardRho
```

---

## 📄 License

Released under the **MIT License** — free to use, modify, and distribute. See [LICENSE](LICENSE) for details.

---

<p align="center">
  <img src="https://avatars.githubusercontent.com/u/102406248?v=4" width="72" style="border-radius:50%;" /><br/><br/>
  <b>Manu Bharadwaj</b> &nbsp;·&nbsp; <a href="https://github.com/Manu577228">@Manu577228</a>
</p>

<p align="center">
  <i>∑ ∏ ∫ π φ ζ — where beauty meets computation</i><br/><br/>
  ⭐ <b>Star this repo</b> if it helped you — it keeps the algorithms coming!
</p>
