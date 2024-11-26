package clack.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextMessageTest {

    @Test
    void getText() {
        TextMessage hm = new TextMessage("user", "this is the text");
        assertEquals("this is the text", hm.getText());
    }

    /**
     * Test toString. Match all fields except for timestamp.
     */
    @Test
    void testToString() {
        TextMessage hm = new TextMessage("user", "this is the text");
        String expected = "TextMessage{"
                + "Message{msgTypeEnum=TEXT"
                + ", timestamp=omitted"
                + ", username='user'"
                + "}, text='this is the text'}";
        String actual = hm.toString().replaceFirst(
                "timestamp=.*, username=",
                "timestamp=omitted, username=");
        assertEquals(expected, actual);
    }

    /**
     * Test constructor for proper initialization.
     */
    @Test
    void testConstructor() {
        String username = "user";
        String text = "this is the text";

        TextMessage tm = new TextMessage(username, text);

        // Test username
        assertEquals(username, tm.getUsername());

        // Test text content
        assertEquals(text, tm.getText());

        // Ensure the object is not null
        assertNotNull(tm);
    }

    /**
     * Test equals method for various cases.
     */
    @Test
    void testEquals() {
        TextMessage tm1 = new TextMessage("user", "this is the text");
        TextMessage tm2 = new TextMessage("user", "this is the text");
        TextMessage tm3 = new TextMessage("user", "different text");
        TextMessage tm4 = new TextMessage("differentUser", "this is the text");

        // Same object
        assertEquals(tm1, tm1);

        // Same values
        assertEquals(tm1, tm2);

        // Different text
        assertNotEquals(tm1, tm3);

        // Different username
        assertNotEquals(tm1, tm4);

        // Null and different class
        assertNotEquals(tm1, null);
        assertNotEquals(tm1, "string");
    }

    /**
     * Test hashCode method.
     */
    @Test
    void testHashCode() {
        TextMessage tm1 = new TextMessage("user", "this is the text");
        TextMessage tm2 = new TextMessage("user", "this is the text");
        TextMessage tm3 = new TextMessage("user", "different text");

        // Same values have the same hash code
        assertEquals(tm1.hashCode(), tm2.hashCode());

        // Different text produces different hash codes
        assertNotEquals(tm1.hashCode(), tm3.hashCode());
    }
}