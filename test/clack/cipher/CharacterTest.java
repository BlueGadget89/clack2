package clack.cipher;

import org.junit.jupiter.api.Test;

import static clack.cipher.CharacterCipher.*;
import static clack.cipher.CharacterCipher.ALPHABET;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {
    /**
     * Test clean().
     */
    @Test
    void testClean() {
        assertNull(clean(null));
        assertEquals("", clean(""));
        assertThrows(IllegalArgumentException.class,
                () -> new CaesarCipher("!test"));
        assertThrows(IllegalArgumentException.class,
                () -> new CaesarCipher(".FAIL"));
    }

    @Test
    void testGroup() {
        // Bad n
        assertThrows(IllegalArgumentException.class,
                () -> group(null, 0));
        assertEquals("", group("", 3));
        assertEquals("a b c d e", group("abcde", 1));
        assertEquals("abc def g", group("abcdefg", 3));
    }

    @Test
    void testMod() {
        assertEquals(1, mod(10, 3));
        assertThrows(IllegalArgumentException.class, () -> mod(10, 0));
        assertEquals(0, mod(3, 3));
    }

    @Test
    void testShiftChar() {
        assertThrows(IllegalArgumentException.class, () -> shift('1', 2));
        assertEquals('C', shift('A', 2));
        assertEquals('A', shift('A', 0));
        assertEquals('A', shift('A', ALPHABET.length()));
    }

    @Test
    void testShiftString() {
        assertEquals("CDE", shift("ABC", 2));
        assertEquals("ABC", shift("ABC", 0));
        assertEquals("", shift("", 2));
    }
}
