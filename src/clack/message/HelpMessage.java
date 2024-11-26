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

    /**
     * Returns a string representation of the HelpMessage.
     *
     * @return a string representation of the HelpMessage
     */
    public String toString()
    {
        return "HelpMessage{"
                + super.toString()
                + "}";
    }

    /**
     * Compares this HelpMessage to the specified object for equality.
     * The result is true if and only if the argument is not null and is a HelpMessage object
     * that has the same username and type as this object.
     *
     * @param o the object to compare this HelpMessage against
     * @return true if the given object represents a HelpMessage equivalent to this HelpMessage, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        return super.equals(o);
    }

    /**
     * Returns a hash code value for the HelpMessage.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
