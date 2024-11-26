package clack.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginMessageTest {

    /**
     * Test constructor for correct initialization.
     */
    @Test
    void testConstructor() {
        String username = "user";
        String password = "opensesame";

        // Create an instance using the constructor
        LoginMessage lm = new LoginMessage(username, password);

        // Check that the username is correctly initialized
        assertEquals(username, lm.getUsername());

        // Check that the password is correctly initialized
        assertEquals(password, lm.getPassword());

        // Check that the message type is correctly set
        assertEquals(MsgTypeEnum.LOGIN, lm.getMsgType());

        // Ensure the object is not null
        assertNotNull(lm);
    }

    /**
     * Test getPassword.
     */
    @Test
    void getPassword() {
        LoginMessage lm = new LoginMessage("user", "opensesame");
        assertEquals("opensesame", lm.getPassword());
    }

    /**
     * Test toString. Match all fields except for timestamp.
     */
    @Test
    void testToString() {
        LoginMessage lm = new LoginMessage("user", "opensesame");
        String expected = "LoginMessage{"
                + "Message{msgTypeEnum=LOGIN"
                + ", timestamp=omitted"
                + ", username='user'"
                + "}, password='**********'}";
        String actual = lm.toString().replaceFirst(
                "timestamp=.*, username=",
                "timestamp=omitted, username=");
        assertEquals(expected, actual);
    }

    /**
     * Test equals method for various scenarios.
     */
    @Test
    void testEquals() {
        LoginMessage lm1 = new LoginMessage("user", "password123");
        LoginMessage lm2 = new LoginMessage("user", "password123");
        LoginMessage lm3 = new LoginMessage("user", "differentPassword");
        LoginMessage lm4 = new LoginMessage("differentUser", "password123");

        // Same object reference
        assertTrue(lm1.equals(lm1));

        // Different objects with the same content
        assertTrue(lm1.equals(lm2));
        assertEquals(lm1.hashCode(), lm2.hashCode());

        // Different objects with different passwords
        assertFalse(lm1.equals(lm3));
        assertNotEquals(lm1.hashCode(), lm3.hashCode());

        // Different objects with different usernames
        assertFalse(lm1.equals(lm4));
        assertNotEquals(lm1.hashCode(), lm4.hashCode());

        // Null and different class
        assertFalse(lm1.equals(null));
        assertFalse(lm1.equals("Not a LoginMessage"));
    }

    /**
     * Test hashCode consistency.
     */
    @Test
    void testHashCode() {
        LoginMessage lm1 = new LoginMessage("user", "password123");
        LoginMessage lm2 = new LoginMessage("user", "password123");

        // Consistent hashCode for the same content
        assertEquals(lm1.hashCode(), lm2.hashCode());

        // Different hashCodes for different passwords
        LoginMessage lm3 = new LoginMessage("user", "differentPassword");
        assertNotEquals(lm1.hashCode(), lm3.hashCode());

        // Different hashCodes for different usernames
        LoginMessage lm4 = new LoginMessage("differentUser", "password123");
        assertNotEquals(lm1.hashCode(), lm4.hashCode());
    }
}