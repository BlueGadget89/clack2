package clack.message;

import java.util.Objects;

/**
 * Represents a text message in the Clack system.
 */
public class TextMessage extends Message {
    private final String text;

    /**
     * Constructs a TextMessage with the given username and text content.
     *
     * @param username the username of the sender
     * @param text the content of the text message
     */
    public TextMessage(String username, String text) {
        super(username, MsgTypeEnum.TEXT);
        this.text = text;
    }

    /**
     * Gets the text content of the message.
     *
     * @return the text content
     */
    public String getText() {
        return text;
    }

    /**
     * Returns a string representation of the TextMessage.
     *
     * @return a string representation of the TextMessage
     */
    @Override
    public String toString() {
        return "TextMessage{" +
                super.toString() +
                ", text='" + text + '\'' +
                '}';
    }

    /**
     * Compares this TextMessage to the specified object for equality.
     * The result is true if and only if the argument is not null and is a TextMessage object
     * that has the same username, type, and text content as this object.
     *
     * @param o the object to compare this TextMessage against
     * @return true if the given object represents a TextMessage equivalent to this TextMessage, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
      //  if (!super.equals(o)) return false;
        TextMessage that = (TextMessage) o;
        return Objects.equals(this.getUsername(), that.getUsername()) // compare username
                && Objects.equals(this.getText(), that.getText()); // compare text
    }

    /**
     * Returns a hash code value for the TextMessage.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
