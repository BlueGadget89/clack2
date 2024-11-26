package clack.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutMessageTest {

    /**
     * Test constructor and getters.
     */
    @Test
    void testConstructor() {
        String username = "user";
        LogoutMessage lm = new LogoutMessage(username);

        // Check the username
        assertEquals(username, lm.getUsername());

        // Verify the message type indirectly through toString()
        String expectedTypePart = "msgTypeEnum=LOGOUT";
        assertTrue(lm.toString().contains(expectedTypePart));

        // Ensure the object is not null
        assertNotNull(lm);
    }

    /**
     * Test toString. Match all fields except for timestamp.
     */
    @Test
    void testToString() {
        LogoutMessage lm = new LogoutMessage("user");
        String expected = "LogoutMessage{"
                + "Message{msgTypeEnum=LOGOUT"
                + ", timestamp=omitted"
                + ", username='user'"
                + "}}";
        String actual = lm.toString().replaceFirst(
                "timestamp=.*, username=",
                "timestamp=omitted, username=");
        assertEquals(expected, actual);
    }

    /**
     * Test equals method.
     */
    @Test
    void testEquals() {
        LogoutMessage lm1 = new LogoutMessage("user");
        LogoutMessage lm2 = new LogoutMessage("user");
        LogoutMessage lm3 = new LogoutMessage("differentUser");

        // Same object
        assertEquals(lm1, lm1);

        // Same values
        assertEquals(lm1, lm2);

        // Different username
        assertNotEquals(lm1, lm3);

        // Null and different class
        assertNotEquals(lm1, null);
        assertNotEquals(lm1, "string");
    }

    /**
     * Test hashCode method.
     */
    @Test
    void testHashCode() {
        LogoutMessage lm1 = new LogoutMessage("user");
        LogoutMessage lm2 = new LogoutMessage("user");
        LogoutMessage lm3 = new LogoutMessage("differentUser");

        // Same values have the same hash code
        assertEquals(lm1.hashCode(), lm2.hashCode());

        // Different values have different hash codes
        assertNotEquals(lm1.hashCode(), lm3.hashCode());
    }
}