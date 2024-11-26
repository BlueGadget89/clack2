package clack.cipher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VignereCipher extends CharacterCipher{
    private final String key;

    /**
     * Constructs a VignereCipher object that has the ability to
     * prep, encrypt, and decrypt strings. Using polyalphabetic
     * substitution to encrypt and decrypt the messages
     * @param key the desired key to be used
     */
    public VignereCipher(String key) {
        if (key == null || key.equals("")) {
            throw new IllegalArgumentException(
                    "Need a non-null, non-empty string");
        }

        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(key);
        boolean found = matcher.find();

        if(found){
            throw new IllegalArgumentException(
                    "Key must not contain spaces");
        }

        if (key.matches(".*[a-z].*")) {
            throw new IllegalArgumentException(
                    "Key must not be all upper case characters");
        }

        this.key = key.toUpperCase();
    }

    /**
     * Prepare cleartext for encrypting. At minimum this requires
     * removing spaces, punctuation, and non-alphabetic characters,
     * then uppercasing what's left.
     *
     * @param cleartext
     * @return a version of the cleartext ready for encrypting.
     */
    @Override
    String prep(String cleartext) {
        return cleartext.toUpperCase().replaceAll("^[A-Z]", "");
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
        StringBuilder encrypted = new StringBuilder();
        String fullKey = generateKey(preptext, this.key);

        for (int i = 0; i < preptext.length(); i++) {
            char plainChar = preptext.charAt(i);
            char keyChar = fullKey.charAt(i);
            int shift = (plainChar - 'A' + keyChar - 'A') % 26;
            encrypted.append((char) ('A' + shift));
        }

        return encrypted.toString();
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
        StringBuilder decrypted = new StringBuilder();
        String fullKey = generateKey(ciphertext, this.key);

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = fullKey.charAt(i);
            int shift = (cipherChar - keyChar + 26) % 26;
            decrypted.append((char) ('A' + shift));
        }

        return decrypted.toString();
    }


    /**
     * This function generates the encription key in a cyclic manner
     * until the generated key is the same length as the str length.
     *
     * @param str message that is used for encryption/decryption
     * @param key key that is defined for the encryption object
     * @return generated key that is to be used
     */
    private String generateKey(String str, String key) {
        StringBuilder keyOut = new StringBuilder();
        int x = str.length();
        int keyLength = key.length();

        for (int i = 0; keyOut.length() < x; i++) {
            keyOut.append(key.charAt(i % keyLength));
        }

        return keyOut.toString();
    }
}

