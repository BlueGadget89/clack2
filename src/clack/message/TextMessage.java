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

    @Override
    public String toString() {
        return "TextMessage{" +
                super.toString() +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
      //  if (!super.equals(o)) return false;
        TextMessage that = (TextMessage) o;
        return Objects.equals(this.getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}

