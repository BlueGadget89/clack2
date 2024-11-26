package clack.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionMessageTest {

    /**
     * Test all OptionEnum values.
     */
    @Test
    void getOption() {
        for (OptionEnum opt : OptionEnum.values()) {
            OptionMessage om = new OptionMessage("user", opt, "setting");
            assertEquals(opt, om.getOption());
        }
    }

    /**
     * Test for value of null, empty string, non-empty string.
     */
    @Test
    void getValue() {
        OptionEnum opt = OptionEnum.CIPHER_KEY;
        String[] values = {null, "", "playfair"};
        for (String v : values) {
            OptionMessage om = new OptionMessage("user", opt, v);
            assertEquals(v, om.getValue());
        }
    }

    /**
     * Test toString. Match all fields except for timestamp.
     * Test for value of null, empty string, non-empty string.
     */
    @Test
    void testToString() {
        OptionEnum opt = OptionEnum.CIPHER_KEY;
        String[] values = {null, "", "playfair"};
        for (String v : values) {
            OptionMessage om = new OptionMessage("user", opt, v);
            String expected = "OptionMessage{"
                    + "Message{msgTypeEnum=OPTION"
                    + ", timestamp=omitted"
                    + ", username='user'"
                    + "}"
                    + ", option=" + opt.toString()
                    + ", value='" + v
                    + "'}";
            String actual = om.toString().replaceFirst(
                    "timestamp=.*, username=",
                    "timestamp=omitted, username=");
            assertEquals(expected, actual);
        }
    }

    /**
     * Test constructor for proper initialization.
     */
    @Test
    void testConstructor() {
        String username = "user";
        OptionEnum opt = OptionEnum.CIPHER_KEY;
        String value = "playfair";

        OptionMessage om = new OptionMessage(username, opt, value);

        // Test username
        assertEquals(username, om.getUsername());

        // Test option
        assertEquals(opt, om.getOption());

        // Test value
        assertEquals(value, om.getValue());

        // Ensure the object is not null
        assertNotNull(om);
    }

    /**
     * Test equals method.
     */
    @Test
    void testEquals() {
        OptionMessage om1 = new OptionMessage("user", OptionEnum.CIPHER_KEY, "playfair");
        OptionMessage om2 = new OptionMessage("user", OptionEnum.CIPHER_KEY, "playfair");
        OptionMessage om3 = new OptionMessage("user", OptionEnum.CIPHER_NAME, "anotherValue");

        // Same object
        assertEquals(om1, om1);

        // Same values
        assertEquals(om1, om2);

        // Different option or value
        assertNotEquals(om1, om3);

        // Null and different class
        assertNotEquals(om1, null);
        assertNotEquals(om1, "string");
    }

    /**
     * Test hashCode method.
     */
    @Test
    void testHashCode() {
        OptionMessage om1 = new OptionMessage("user", OptionEnum.CIPHER_KEY, "playfair");
        OptionMessage om2 = new OptionMessage("user", OptionEnum.CIPHER_KEY, "playfair");
        OptionMessage om3 = new OptionMessage("user", OptionEnum.CIPHER_NAME, "anotherValue");

        // Same values have the same hash code
        assertEquals(om1.hashCode(), om2.hashCode());

        // Different values have different hash codes
        assertNotEquals(om1.hashCode(), om3.hashCode());
    }
}