package clack.cipher;

import org.junit.jupiter.api.Test;

import static clack.cipher.CharacterCipher.ALPHABET;
import static clack.cipher.CharacterCipher.clean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CaesarTest {

    @Test
    void testConstructorString() {
        assertThrows(IllegalArgumentException.class,
                () -> new CaesarCipher(null));
        assertThrows(IllegalArgumentException.class,
                () -> new CaesarCipher(""));
        assertThrows(IllegalArgumentException.class,
                () -> new CaesarCipher("!test"));
        assertThrows(IllegalArgumentException.class,
                () -> new CaesarCipher(".FAIL"));

    }

    /**
     * Test prep(). Should behave exactly as CharacterCipher.clean().
     */
    @Test
    void testPrep() {
        CaesarCipher cc = new CaesarCipher(3);
        assertEquals("THISISATEST", cc.prep("this is a test."));
    }

    @Test
    void encrypt() {
        CaesarCipher cc = new CaesarCipher(4);
        assertEquals("EXXEGOEXSRGI", cc.encrypt("ATTACKATONCE"));
    }

    @Test
    void decrypt() {
        CaesarCipher cc = new CaesarCipher(4);
        assertEquals("ATTACKATONCE", cc.decrypt("EXXEGOEXSRGI"));
    }
}
