import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class StringSearching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator the comparator to use when checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The pattern given was either null"
                    + " or had a length of zero. Give a pattern of at least length 1.");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("The text or comparator given was null. "
                    + "Give real arguments.");
        }

        List<Integer> startingIndices = new ArrayList<>();
        if (text.length() == 0 || text.length() < pattern.length()) {
            return startingIndices;
        }
        int[] failureTable = buildFailureTable(pattern, comparator);
        int textPointer = 0;
        int patternPointer = 0;

        while (textPointer <= text.length() - pattern.length()) {
            while (patternPointer < pattern.length()
                    && comparator.compare(text.charAt(textPointer + patternPointer),
                    pattern.charAt(patternPointer)) == 0) {
                patternPointer++;
            }
            if (patternPointer == 0) {
                textPointer++;
            } else {
                if (patternPointer == pattern.length()) {
                    startingIndices.add(textPointer);
                }
                textPointer += patternPointer - failureTable[patternPointer - 1];
                patternPointer = failureTable[patternPointer - 1];
            }
        }
        return startingIndices;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern or comparator is null
     * @param pattern a {@code CharSequence} you're building a failure table for
     * @param comparator the comparator to use when checking character equality
     * @return integer array holding your failure table
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null || comparator == null) {
            throw new IllegalArgumentException("The pattern and the comparator given must be non-null.");
        }
        int[] failureTable = new int[pattern.length()];
        if (pattern.length() != 1) {
            int i = 0;
            int j = 1;
            while (j < pattern.length()) {
                if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                    failureTable[j++] = ++i;
                } else {
                    if (i == 0) {
                        failureTable[j++] = 0;
                    } else {
                        i = failureTable[i - 1];
                    }
                }
            }
        } else {
            failureTable[0] = 0;
        }
        return failureTable;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the last occurrence table before implementing this
     * method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for the pattern
     * @param comparator the comparator to use when checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                       CharSequence text, CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The pattern given was either "
                    + "null or of length 0. Give a real pattern to search for.");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("The text or comparator given was "
                    + "null. Please give real parameters.");
        }

        Map<Character, Integer> lastOccurrence = buildLastTable(pattern);
        List<Integer> startingIndices = new ArrayList<>();
        if (text.length() == 0 || text.length() < pattern.length()) {
            return startingIndices;
        }

        int patternPointer;
        int textPointer = 0;
        while (textPointer <= (text.length() - pattern.length())) {
            patternPointer = pattern.length() - 1;
            while (patternPointer >= 0 &&
                    comparator.compare(pattern.charAt(patternPointer),
                    text.charAt(textPointer + patternPointer)) == 0) {
                patternPointer--;
            }
            if (patternPointer == -1) {
                startingIndices.add(textPointer);
                //why????????
                //textPointer = textPointer + pattern.length();
                textPointer++;
            } else {
                int i = lastOccurrence.getOrDefault(text.charAt(textPointer + patternPointer), -1);
                if (i < patternPointer) {
                    textPointer = textPointer + (patternPointer - i);
                } else {
                    textPointer++;
                }
            }
        }
        return startingIndices;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("The pattern given was null. Provide"
                    + " a real pattern.");
        }

        Map<Character, Integer> lastOccurrence = new Hashtable<>();
        if (pattern.length() == 0) {
            return lastOccurrence;
        }
        for (int i = 0; i < pattern.length(); i++) {
                lastOccurrence.put(pattern.charAt(i), i);
        }
        return lastOccurrence;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 181;

    /**
     * Runs Rabin-Karp algorithm. Generate the pattern hash, and compare it with
     * the hash from a substring of text that's the same length as the pattern.
     * If the two hashes match, compare their individual characters, else update
     * the text hash and continue.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator the comparator to use when checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                      CharSequence text, CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The pattern given was either null "
                    + "or of length 0. Give a real pattern.");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("The text or comparator given was "
                    + "null. Give real parameters.");
        }

        List<Integer> startingIndices = new ArrayList<>();
        if (text.length() == 0 || text.length() < pattern.length()) {
            return startingIndices;
        }

        int pHash = generateHash(pattern, pattern.length());
        int tHash = generateHash(text, pattern.length());
        int patternPointer;
        int textPointer = 0;
        while (textPointer <= text.length() - pattern.length()) {
            if (pHash == tHash) {
                patternPointer = 0;
                while (patternPointer < pattern.length()
                        && (comparator.compare(text.charAt(textPointer + patternPointer),
                        pattern.charAt(patternPointer)) == 0)) {
                    patternPointer++;
                }
                if (patternPointer == pattern.length()) {
                    startingIndices.add(textPointer);
                }
            }
            if (textPointer < text.length() - pattern.length()) {
                tHash = updateHash(tHash, pattern.length(),
                        text.charAt(textPointer), text.charAt(textPointer + pattern.length()));
            }
            textPointer++;
        }
        return startingIndices;
    }

    /**
     * Hash function used for Rabin-Karp. The formula for hashing a string is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 181 hash
     * = b * 181 ^ 3 + u * 181 ^ 2 + n * 181 ^ 1 + n * 181 ^ 0 = 98 * 181 ^ 3 +
     * 117 * 181 ^ 2 + 110 * 181 ^ 1 + 110 * 181 ^ 0 = 584967675
     *
     * Note that the largest possible value for an int is 2147483647, so it is
     * possible that the calculated hash may roll over.
     *
     *
     * Do NOT use {@code Math.pow()} in this method. We have provided a pow()
     * method for you to use.
     *
     * @throws IllegalArgumentException if current is null
     * @throws IllegalArgumentException if length is negative, 0, or greater
     *     than the length of current
     * @param current substring you are generating hash function for
     * @param length the length of the string you want to generate the hash for,
     * starting from index 0. For example, if length is 4 but current's length
     * is 6, then you include indices 0-3 in your hash (and pretend the actual
     * length is 4)
     * @return hash of the substring
     */
    public static int generateHash(CharSequence current, int length) {
        if (current == null) {
            throw new IllegalArgumentException("Must pass in a non-null char sequence.");
        }
        if (length <= 0 || length > current.length()) {
            throw new IllegalArgumentException("The length must be greater than or equal "
                    + "to zero and less than or equal to the char sequence length.");
        }

        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash += current.charAt(i) * pow(BASE, length - 1 - i);
        }
        return hash;
    }

    /**
     * Updates a hash in constant time to avoid constantly recalculating
     * entire hash. To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 181
     * hash("unny") = (hash("bunn") - b * 181 ^ 3) * 181 + y * 181 ^ 0 =
     * (584967675 - 98 * 181 ^ 3) * 181 + 121 * 181 ^ 0 = 697403438
     *
     *
     * The computation of BASE raised to length - 1 may require O(log n) time,
     * but the method should otherwise run in O(1).
     *
     * Do NOT use {@code Math.pow()} in this method. We have provided a pow()
     * method for you to use.
     *
     * @throws IllegalArgumentException if length is negative or 0
     * @param oldHash hash generated by generateHash
     * @param length length of pattern/substring of text
     * @param oldChar character we want to remove from hashed substring
     * @param newChar character we want to add to hashed substring
     * @return updated hash of this substring
     */
    public static int updateHash(int oldHash, int length, char oldChar,
                                 char newChar) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        return BASE * (oldHash - oldChar * pow(BASE, length - 1)) + newChar;
    }

    /**
     * Calculate the result of a number raised to a power.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
