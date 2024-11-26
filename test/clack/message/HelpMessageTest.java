package clack.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelpMessageTest {

    /**
     * Test the constructor for the HelpMessage class.
     */
    @Test
    void testConstructor() {
        String username = "user";
        HelpMessage hm = new HelpMessage(username);

        // Validate that the username is correctly passed into the constructor
        assertEquals(username, hm.getUsername());
        assertEquals(MsgTypeEnum.HELP, hm.getMsgType()); // Assuming MsgTypeEnum.HELP is the correct type
    }

    /**
     * Test the constructor with different valid usernames.
     */
    @Test
    void testConstructorWithDifferentUsernames() {
        String[] usernames = {"user1", "user2", "guest"};
        for (String username : usernames) {
            HelpMessage hm = new HelpMessage(username);
            assertEquals(username, hm.getUsername());
            assertEquals(MsgTypeEnum.HELP, hm.getMsgType());
        }
    }

    /**
     * Test the constructor with null username.
     * (Assuming your constructor handles null, or if you want it to throw an exception, adjust accordingly)
     */
    @Test
    void testConstructorWithNullUsername() {
        String username = null;
        HelpMessage hm = new HelpMessage(username);

        // Assuming that your constructor allows null username, otherwise, update as necessary
        assertNull(hm.getUsername());
        assertEquals(MsgTypeEnum.HELP, hm.getMsgType());
    }

    /**
     * Test toString. Match all fields except for timestamp.
     */
    @Test
    void testToString() {
        HelpMessage hm = new HelpMessage("user");
        String expected = "HelpMessage{"
                + "Message{msgTypeEnum=HELP"
                + ", timestamp=omitted"
                + ", username='user'"
                + "}}";
        String actual = hm.toString().replaceFirst(
                "timestamp=.*, username=",
                "timestamp=omitted, username=");
        assertEquals(expected, actual);
    }

    /**
     * Test equals method. Verify that equal objects are considered equal and unequal objects are not.
     */
    @Test
    void testEquals() {
        HelpMessage hm1 = new HelpMessage("user1");
        HelpMessage hm2 = new HelpMessage("user1");
        HelpMessage hm3 = new HelpMessage("user2");

        // Reflexivity
        assertTrue(hm1.equals(hm1));

        // Symmetry
        assertTrue(hm1.equals(hm2));
        assertTrue(hm2.equals(hm1));

        // Null comparison
        assertFalse(hm1.equals(null));

        // Different usernames
        assertFalse(hm1.equals(hm3));

        // Different class
        Object notHelpMessage = new Object();
        assertFalse(hm1.equals(notHelpMessage));
    }

    /**
     * Test hashCode method. Verify that equal objects have the same hash code.
     */
    @Test
    void testHashCode() {
        HelpMessage hm1 = new HelpMessage("user1");
        HelpMessage hm2 = new HelpMessage("user1");
        HelpMessage hm3 = new HelpMessage("user2");

        // Equal objects have the same hash code
        assertEquals(hm1.hashCode(), hm2.hashCode());

        // Unequal objects have different hash codes
        assertNotEquals(hm1.hashCode(), hm3.hashCode());
    }
}