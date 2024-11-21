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

    @Override
    public String toString() {
        return "LogoutMessage{" + super.toString() + "}";
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


