package huffman;

import java.util.*;

/**
 * ============================================================
 *  HUFFMAN COMPRESSION - STARTER CODE
 *  Exercise: Compress a Base64-encoded image string
 * ============================================================
 *
 *  Your goal: implement Huffman coding to compress any String.
 *  We'll test it on a Base64-encoded image.
 *
 *  Steps you need to complete:
 *    1. Count character frequencies
 *    2. Build a min-heap (priority queue) of leaf nodes
 *    3. Build the Huffman tree
 *    4. Generate the code table (char → bit string)
 *    5. Encode the input
 *    6. Decode back and verify
 *
 *  Methods marked with TODO are yours to implement.
 *  Do NOT change method signatures.
 */
public class HuffmanEncoding {

    // ----------------------------------------------------------------
    //  Node class — represents a node in the Huffman tree
    // ----------------------------------------------------------------
    static class Node implements Comparable<Node> {
        char ch;          // character (only meaningful in leaf nodes)
        int  freq;        // frequency of this character (or subtree sum)
        Node left, right; // left & right children

        /** Leaf node constructor */
        Node(char ch, int freq) {
            this.ch   = ch;
            this.freq = freq;
        }

        /** Internal node constructor */
        Node(int freq, Node left, Node right) {
            this.ch    = '\0'; // not a real character
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.freq, other.freq);
        }
    }

    // ----------------------------------------------------------------
    //  STEP 1 — Count character frequencies
    // ----------------------------------------------------------------

    /**
     * TODO: Count how often each character appears in the input.
     *
     * @param text input string
     * @return map from character to its frequency
     */
    static Map<Character, Integer> buildFrequencyMap(String text) {
        // Hint: iterate over text.toCharArray() and use a HashMap
        throw new UnsupportedOperationException("TODO: implement buildFrequencyMap");
    }

    // ----------------------------------------------------------------
    //  STEP 2 & 3 — Build the Huffman Tree
    // ----------------------------------------------------------------

    /**
     * TODO: Build the Huffman tree from a frequency map.
     *
     * Algorithm:
     *   1. Create one leaf Node per character and add them all to a
     *      PriorityQueue<Node> (min-heap by frequency).
     *   2. While the queue has more than one node:
     *        a. Poll the two nodes with the lowest frequencies (left, right).
     *        b. Create a new internal Node whose frequency = left.freq + right.freq.
     *        c. Add the new node back to the queue.
     *   3. The last remaining node is the root — return it.
     *
     * @param freqMap character frequency map
     * @return root of the Huffman tree
     */
    static Node buildTree(Map<Character, Integer> freqMap) {
        // Hint: use new PriorityQueue<>() — Node already implements Comparable
        throw new UnsupportedOperationException("TODO: implement buildTree");
    }

    // ----------------------------------------------------------------
    //  STEP 4 — Generate code table
    // ----------------------------------------------------------------

    /**
     * TODO: Traverse the tree and record the bit-string for every leaf.
     *
     * Convention: going LEFT appends "0", going RIGHT appends "1".
     *
     * Implement this recursively:
     *   - If node is a leaf → store (node.ch, prefix) in the codeTable.
     *   - Otherwise → recurse left with prefix+"0", recurse right with prefix+"1".
     *
     * Edge case: if the tree has only one distinct character,
     * assign that character the code "0".
     *
     * @param node      current tree node
     * @param prefix    bit-string accumulated so far
     * @param codeTable map to populate: char → bit-string
     */
    static void buildCodeTable(Node node, String prefix, Map<Character, String> codeTable) {
        throw new UnsupportedOperationException("TODO: implement buildCodeTable");
    }

    // ----------------------------------------------------------------
    //  STEP 5 — Encode
    // ----------------------------------------------------------------

    /**
     * TODO: Encode the input string using the code table.
     *
     * For each character in the text, look up its bit-string in
     * codeTable and append it to a StringBuilder.
     *
     * @param text      original input
     * @param codeTable map from char to bit-string
     * @return encoded bit-string (e.g. "010011101...")
     */
    static String encode(String text, Map<Character, String> codeTable) {
        throw new UnsupportedOperationException("TODO: implement encode");
    }

    // ----------------------------------------------------------------
    //  STEP 6 — Decode
    // ----------------------------------------------------------------

    /**
     * TODO: Decode a bit-string back to the original text.
     *
     * Walk the Huffman tree bit by bit:
     *   - '0' → go left
     *   - '1' → go right
     *   - when you reach a leaf → append its character, restart from root
     *
     * @param bits encoded bit-string
     * @param root root of the Huffman tree
     * @return decoded original string
     */
    static String decode(String bits, Node root) {
        throw new UnsupportedOperationException("TODO: implement decode");
    }

    // ----------------------------------------------------------------
    //  Utility — compression stats (already implemented for you)
    // ----------------------------------------------------------------

    static void printStats(String label, String original, String encoded) {
        int originalBits = original.length() * 8; // 8 bits per ASCII/UTF-16 char
        int encodedBits  = encoded.length();       // each char in our string IS one bit
        double ratio     = 100.0 * (1.0 - (double) encodedBits / originalBits);
        System.out.printf("[%s]%n", label);
        System.out.printf("  Original : %,d chars × 8 bits = %,d bits%n",
                original.length(), originalBits);
        System.out.printf("  Encoded  : %,d bits%n", encodedBits);
        System.out.printf("  Savings  : %.1f%%%n%n", ratio);
    }

    // ----------------------------------------------------------------
    //  Main — wires everything together
    // ----------------------------------------------------------------

    public static void main(String[] args) {

        // --- Test 1: simple string ---
        runTest("Hello Huffman!", "Simple string");

        // --- Test 2: repeated pattern (high compressibility) ---
        runTest("aaaaaabbbbcccdde", "Skewed frequencies");

        // --- Test 3: tiny simulated Base64 snippet ---
        // (In a real exercise, replace this with Base64.getEncoder()
        //  .encodeToString(imageBytes) where imageBytes come from a file.)
        String fakeBase64 = "iVBORw0KGgoAAAANSUhEUgAAAAUA"
                + "AAAFCAYAAACNbyblAAAAHElEQVQI12P4"
                + "AAQIBAED/wAA/wAA/wAAAAABJRU5ErkJggg==";
        runTest(fakeBase64, "Base64-encoded PNG (snippet)");
    }

    static void runTest(String input, String label) {
        System.out.println("=".repeat(50));
        System.out.println("Test: " + label);
        System.out.println("=".repeat(50));

        // Build frequency map
        Map<Character, Integer> freq = buildFrequencyMap(input);

        // Build Huffman tree
        Node root = buildTree(freq);

        // Build code table
        Map<Character, String> codeTable = new HashMap<>();
        buildCodeTable(root, "", codeTable);

        // Encode
        String encoded = encode(input, codeTable);

        // Decode and verify
        String decoded = decode(encoded, root);
        boolean ok = input.equals(decoded);

        // Print results
        printStats(label, input, encoded);
        System.out.println("  Decode check: " + (ok ? "PASS ✓" : "FAIL ✗"));
        if (!ok) {
            System.out.println("  Expected : " + input);
            System.out.println("  Got      : " + decoded);
        }

        // Print code table for short inputs
        if (input.length() <= 40) {
            System.out.println("\n  Code table:");
            codeTable.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(String::length)))
                    .forEach(e -> System.out.printf("    '%s'  →  %s (freq=%d)%n",
                            e.getKey(), e.getValue(), freq.get(e.getKey())));
        }
        System.out.println();
    }
}