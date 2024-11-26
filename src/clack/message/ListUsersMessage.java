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

    /**
     * Returns a string representation of the ListUsersMessage.
     *
     * @return a string representation of the ListUsersMessage
     */
    @Override
    public String toString() {
        return "ListUsersMessage{" + super.toString() + "}";
    }

    /**
     * Compares this ListUsersMessage to the specified object for equality.
     * The result is true if and only if the argument is not null and is a ListUsersMessage object
     * that has the same username and type as this object.
     *
     * @param o the object to compare this ListUsersMessage against
     * @return true if the given object represents a ListUsersMessage equivalent to this ListUsersMessage, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        return super.equals(o);
    }

    /**
     * Returns a hash code value for the ListUsersMessage.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

