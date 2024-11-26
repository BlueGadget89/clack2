package clack.message;

/**
 * Represents a logout message in the Clack system.
 * This is a simple "tag" message that indicates a user is logging out.
 */
public class LogoutMessage extends Message {

    /**
     * Constructs a LogoutMessage with the given username.
     *
     * @param username the username of the user logging out
     */
    public LogoutMessage(String username) {
        super(username, MsgTypeEnum.LOGOUT);
    }

    /**
     * Returns a string representation of this LogoutMessage.
     * <p>
     * The string representation includes the username and type
     * of the message.
     *
     * @return a string representation of this LogoutMessage
     */
    @Override
    public String toString() {
        return "LogoutMessage{" + super.toString() + "}";
    }

    /**
     * Compares this LogoutMessage to the specified object for equality.
     * The result is true if and only if the argument is not null and is a LogoutMessage object
     * that has the same username and type as this object.
     *
     * @param o the object to compare this LogoutMessage against
     * @return true if the given object represents a LogoutMessage equivalent to this LogoutMessage, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        return super.equals(o);
    }

    /**
     * Returns a hash code value for this LogoutMessage.
     * <p>
     * The hash code is based on the username and type of the message.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}