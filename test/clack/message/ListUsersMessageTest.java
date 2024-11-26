package clack.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListUsersMessageTest {

    /**
     * Test the constructor for the ListUsersMessage class.
     */
    @Test
    void testConstructor() {
        String username = "user";
        ListUsersMessage lm = new ListUsersMessage(username);

        // Validate that the username is correctly passed into the constructor
        assertEquals(username, lm.getUsername());
        assertEquals(MsgTypeEnum.LISTUSERS, lm.getMsgType()); // Assuming MsgTypeEnum.LIST_USERS is the correct type
    }

    /**
     * Test the constructor with different valid usernames.
     */
    @Test
    void testConstructorWithDifferentUsernames() {
        String[] usernames = {"user1", "user2", "guest"};
        for (String username : usernames) {
            ListUsersMessage lm = new ListUsersMessage(username);
            assertEquals(username, lm.getUsername());
            assertEquals(MsgTypeEnum.LISTUSERS, lm.getMsgType());
        }
    }

    /**
     * Test the constructor with null username.
     * (Assuming the constructor allows null, otherwise adjust the test accordingly)
     */
    @Test
    void testConstructorWithNullUsername() {
        String username = null;
        ListUsersMessage lm = new ListUsersMessage(username);

        // Assuming that null username is valid, otherwise modify as needed
        assertNull(lm.getUsername());
        assertEquals(MsgTypeEnum.LISTUSERS, lm.getMsgType());
    }

    /**
     * Test toString. Match all fields except for timestamp.
     */
    @Test
    void testToString() {
        ListUsersMessage lm = new ListUsersMessage("user");
        String expected = "ListUsersMessage{"
                + "Message{msgTypeEnum=LISTUSERS"
                + ", timestamp=omitted"
                + ", username='user'"
                + "}}";
        String actual = lm.toString().replaceFirst(
                "timestamp=.*, username=",
                "timestamp=omitted, username=");
        assertEquals(expected, actual);
    }

    /**
     * Test equals method for different scenarios.
     */
    @Test
    void testEquals() {
        ListUsersMessage lm1 = new ListUsersMessage("user1");
        ListUsersMessage lm2 = new ListUsersMessage("user1");
        ListUsersMessage lm3 = new ListUsersMessage("user2");

        // Same object reference
        assertTrue(lm1.equals(lm1));

        // Different object with the same content
        assertTrue(lm1.equals(lm2));
        assertEquals(lm1.hashCode(), lm2.hashCode());

        // Different object with different content
        assertFalse(lm1.equals(lm3));
        assertNotEquals(lm1.hashCode(), lm3.hashCode());

        // Null and different class
        assertFalse(lm1.equals(null));
        assertFalse(lm1.equals("Not a ListUsersMessage"));
    }

    /**
     * Test hashCode consistency.
     */
    @Test
    void testHashCode() {
        ListUsersMessage lm1 = new ListUsersMessage("user1");
        ListUsersMessage lm2 = new ListUsersMessage("user1");

        // Consistent hashCode for the same content
        assertEquals(lm1.hashCode(), lm2.hashCode());

        // Different hashCode for different content
        ListUsersMessage lm3 = new ListUsersMessage("user2");
        assertNotEquals(lm1.hashCode(), lm3.hashCode());
    }
}