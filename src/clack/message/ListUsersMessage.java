package clack.message;

/**
 * Represents a message requesting a list of users in the Clack system.
 * This is a simple "tag" message that indicates a user is requesting the list of users.
 */
public class ListUsersMessage extends Message {

    /**
     * Constructs a ListUsersMessage with the given username.
     *
     * @param username the username of the user requesting the list of users
     */
    public ListUsersMessage(String username) {
        super(username, MsgTypeEnum.LISTUSERS);
    }

    @Override
    public String toString() {
        return "ListUsersMessage{" + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

