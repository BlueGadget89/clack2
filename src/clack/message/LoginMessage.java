package clack.message;

import java.util.Objects;

/**
 * Represents a login message in the Clack system.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        //if (!super.equals(o)) return false;
        LoginMessage that = (LoginMessage) o;
        return Objects.equals(this.getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password);
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "password='" + password + '\'' +
                "} " + super.toString();
    }
}
