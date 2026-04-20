package org.bharadwaj.BinaryTrieAlgorithm;

//A Binary Trie is a tree data structure used to store
//integers in binary form (bit by bit).
//Each level represents a bit (0 or 1), enabling
//efficient bitwise queries like maximum XOR.

import java.io.*;

class Node {
    Node[] child;

    Node() {
        child = new Node[2];
    }
}

public class BinaryTrieAlgorithm {
    static Node root = new Node();

    static void insert(int num) {
        Node curr = root;

        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;

            if (curr.child[bit] == null) {
                curr.child[bit] = new Node();
            }

            curr = curr.child[bit];
        }
    }

    static int maxXor(int num) {
        Node curr = root;
        int max = 0;

        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;

            int opposite = 1 - bit;

            if (curr.child[opposite] != null) {
                max |= (1 << i);
                curr = curr.child[opposite];
            } else {
                curr = curr.child[bit];
            }
        }

        return max;
    }

    public static void main(String[] args) throws Exception {

        int[] arr = {5, 2, 8};

        for (int x : arr) {
            insert(x);
        }

        int ans = 0;

        for (int x : arr) {
            ans = Math.max(ans, maxXor(x));
        }

        System.out.println("Maximum XOR = " + ans);
    }
}
