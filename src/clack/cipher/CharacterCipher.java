package clack.cipher;

/**
 * Abstract class for ciphers that work on character data.
 */
public abstract class CharacterCipher {
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Return a copy of a string, but reformatted into groups of
     * <em>n</em> non-whitespace characters, with groups separated
     * by a space. The last group may have fewer than <em>n</em>
     * characters.
     * @param str the string to break into groups
     * @param n how many characters in each group
     * @return the grouped version of the argument string
     */
    public static String group(String str, int n) {
        if (n <= 0) throw new IllegalArgumentException("Group size must be positive");
        if (str == null) {
            return null;
        }

        StringBuilder grouped = new StringBuilder();
        int count = 0;

        for (char ch : str.toCharArray()) {
            if (count > 0 && count % n == 0) {
                grouped.append(' ');
            }
            grouped.append(ch);
            count++;
        }

        return grouped.toString();
    }

    /**
     * Returns the character that is n letters further on in ALPHABET,
     * with wrap around at the end of ALPHABET. Negative values are
     * allowed and cause a shift to the left. A shift of 0 returns
     * the original character.
     * @param c the character to shift.
     * @param n the number of places to shift the character.
     * @return the character at the location n places beyond c.
     * @throws IllegalArgumentException if c is not in ALPHABET.
     */
    public static char shift(char c, int n) {
        int charIndex = ALPHABET.indexOf(c);
        if (charIndex < 0 ) {
            throw new IllegalArgumentException(
                    "Argument ('" + c + "') not in ALPHABET");
        }
        int shiftedIndex = mod(charIndex + n, ALPHABET.length());
        return ALPHABET.charAt(shiftedIndex);
    }

    /**
     * Returns the string resulting from shifting each character of str
     * by n places.
     * @param str the string to shift.
     * @param n the amount to shift each letter.
     * @return the shifted version of str.
     */
    public static String shift(String str, int n) {
        if (str == null) {
            return null;
        }

        StringBuilder shifted = new StringBuilder();

        for (char ch : str.toCharArray()) {
            shifted.append(shift(ch, n));
        }

        return shifted.toString();
    }

    /**
     * Removes all non-alphabet characters from a string, and
     * uppercases all remaining letters. This is a utility
     * method, useful in implementing prep(). If the argument
     * is null or empty, returns it as it is.
     *
     * @param str the string to clean
     * @return the cleaned string (which might be empty), or null.
     */
    public static String clean(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase().replaceAll("[^A-Z]", "");
    }

    /**
     * Mathematical "mod" operator. Use instead of Java's "%"
     * operator when shifting leftward (a negative shift), as
     * this will always return a number in the range [0, modulus).
     *
     * @param n       the number to be "modded".
     * @param modulus the modulus.
     * @throws IllegalArgumentException if modulus < 1.
     */
    public static int mod(int n, int modulus) {
        if (modulus < 1) {
            throw new IllegalArgumentException("modulus cannot be < 1");
        }
        // n % modulus -> in range (-modulus, modulus)
        // (n % modulus) + modulus -> in (0, 2 * modulus)
        // ((n % modulus) + modulus) % modulus -> in [0, modulus)
        return ((n % modulus) + modulus) % modulus;
    }

    /**
     * Prepare cleartext for encrypting. At minimum this requires
     * removing spaces, punctuation, and non-alphabetic characters,
     * then uppercasing what's left. Other cipers, such as PLAYFAIR,
     * may have additional preparation that this method needs to do.
     * @param cleartext
     * @return a version of the cleartext ready for encrypting.
     */
    abstract String prep(String cleartext);

    /**
     * Encrypt a string that's been prepared for encryption.
     * @param preptext a version of a cleartext string, prepared
     *                 for encryption.
     * @return the encryption of the preptext.
     */
    abstract String encrypt(String preptext);

    /**
     * Decrypts an encrypted string. The decrypted text should match
     * the preptext that was encrypted.
     * @param ciphertext the encrypted string to decrypt.
     * @return the decryption of the ciphertext.
     */
    abstract String decrypt(String ciphertext);
}
