package clack.cipher;

public class PlayfairCipher extends CharacterCipher {
    private final char[][] matrix;

    /**
     * Constructs a PlayfairCipher object that has the ability to
     * prep, encrypt, and decrypt strings. Using a key matrix to
     * encrypt and decrypt the messages
     * @param key the desired key to be used
     */
    public PlayfairCipher(String key) {
        if (key == null) {
            throw new IllegalArgumentException(
                    "Need a non-null, non-empty string");
        }

        this.matrix = generateMatrix(prep(key));
    }

    /**
     * Prepare cleartext for encrypting. Removes punctuation, spaces, and converts
     * characters to uppercase and cleans the cleartext in sets of two. Also
     * replaces `J` with `I`, Will use `X` in the case that a pair of letters
     * are the same two letters. It will also append a `Z` at the end of the text
     * to make it an even amount of characters if its odd.
     *
     * @param cleartext
     * @return a version of the cleartext ready for encrypting.
     */
    @Override
    String prep(String cleartext) {
        if (cleartext == null) return null;
        StringBuilder prepared = new StringBuilder();

        for (char c : cleartext.toUpperCase().toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                if (c == 'J') c = 'I';
                prepared.append(c);
            }
        }

        for (int i = 0; i < prepared.length() - 1; i += 2) {
            if (prepared.charAt(i) == prepared.charAt(i + 1)) {
                prepared.insert(i + 1, 'X');
            }
        }

        if (prepared.length() % 2 != 0) {
            prepared.append('Z');
        }
        return prepared.toString();
    }


    /**
     * Encrypt a string that's been prepared for encryption.
     *
     * @param preptext a version of a cleartext string, prepared
     *                 for encryption.
     * @return the encryption of the preptext.
     */
    @Override
    String encrypt(String preptext) {
        if (preptext == null) return null;
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < preptext.length(); i += 2) {
            char ch1 = preptext.charAt(i);
            char ch2 = preptext.charAt(i + 1);
            int[] pos1 = search(ch1);
            int[] pos2 = search(ch2);

            if (pos1[0] == pos2[0]) {   // same row
                ciphertext.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                ciphertext.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) {    // same column
                ciphertext.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                ciphertext.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else {    // different column and different row
                ciphertext.append(matrix[pos1[0]][pos2[1]]);
                ciphertext.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return ciphertext.toString();
    }


    /**
     * Decrypts an encrypted string. The decrypted text should match
     * the preptext that was encrypted.
     *
     * @param ciphertext the encrypted string to decrypt.
     * @return the decryption of the ciphertext.
     */
    @Override
    String decrypt(String ciphertext) {
        if (ciphertext == null) return null;
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char ch1 = ciphertext.charAt(i);
            char ch2 = ciphertext.charAt(i + 1);
            int[] pos1 = search(ch1);
            int[] pos2 = search(ch2);

            if (pos1[0] == pos2[0]) {   // same row
                plaintext.append(matrix[pos1[0]][(pos1[1] + 4) % 5]);
                plaintext.append(matrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) {    // same column
                plaintext.append(matrix[(pos1[0] + 4) % 5][pos1[1]]);
                plaintext.append(matrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else {    // different column and different row
                plaintext.append(matrix[pos1[0]][pos2[1]]);
                plaintext.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return plaintext.toString();
    }

    /**
     * Generates the key matrix - a 5×5 grid of alphabets that acts as the key
     * for encrypting the plaintext. Each of the 25 alphabets must be unique with
     * `J` being omitted.
     *
     * @param key the prepped key to be used to generate the matrix
     * @return the matrix kay that's generated
     */
    private char[][] generateMatrix(String key) {
        char[][] matrix = new char[5][5];
        boolean[] used = new boolean[26];
        StringBuilder keyBuilder = new StringBuilder();

        for (char ch : key.toCharArray()) {
            if (ch == 'J') ch = 'I';
            if (!used[ch - 'A']) {
                used[ch - 'A'] = true;
                keyBuilder.append(ch);
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue;
            if (!used[ch - 'A']) {
                used[ch - 'A'] = true;
                keyBuilder.append(ch);
            }
        }

        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = keyBuilder.charAt(index++);
            }
        }
        return matrix;
    }

    /**
     * Helper function to be able to find the position
     * of a character within the matrix key, by searching
     * through the rows and the columns.
     *
     * @param ch character to search for
     * @return location of the character
     */
    private int[] search(char ch) {
        int[] position = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        throw new IllegalArgumentException("Character not found in matrix");
    }
}
