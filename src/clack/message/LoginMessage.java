package clack.message;

import java.util.Objects;

/**
 * Represents a login message in the Clack system.
 * <p>
 * A login message is used when a user logs in to the system. It contains
 * the username and password associated with the user.
 */
public class LoginMessage extends Message {
    private final String password;

    /**
     * Constructs a LoginMessage with the given username and password.
     *
     * @param username the username of the user logging in
     * @param password the password for the user
     */
    public LoginMessage(String username, String password) {
        super(username, MsgTypeEnum.LOGIN);
        this.password = password;
    }

    /**
     * Gets the password associated with this login message.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Compares this LoginMessage to another object for equality.
     *
     * @param o the object to compare with
     * @return true if this object is equal to o, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        //if (!super.equals(o)) return false;
        LoginMessage that = (LoginMessage) o;
        return Objects.equals(this.getUsername(), that.getUsername()) // compare username
                && Objects.equals(this.getPassword(), that.getPassword());
    }

    /**
     * Returns a hash code value for this LoginMessage.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password);
    }

    /**
     * Returns a string representation of the LoginMessage.
     *
     * @return a string representation of this object with the password masked
     */
    @Override
    public String toString() {
        return "LoginMessage{" +
                super.toString() +  // Include the message details first
                ", password='**********'" +  // Mask the password
                '}';
    }
}