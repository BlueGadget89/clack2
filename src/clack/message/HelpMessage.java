package clack.message;

/**
 * Represents a help request message in the Clack system.
 * This is a simple "tag" message that indicates a user is requesting help information.
 */
public class HelpMessage extends Message {

    /**
     * Constructs a HelpMessage with the given username.
     *
     * @param username the username of the user requesting help
     */
    public HelpMessage(String username) {
        super(username, MsgTypeEnum.HELP);
    }

    public String toString()
    {
        return "HelpMessage{"
                + super.toString()
                + "}";
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
