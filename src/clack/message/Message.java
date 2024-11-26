package clack.message;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an abstract message in the Clack system.
 * This class serves as a base for specific types of messages
 * and implements the Serializable interface for object serialization.
 */
public abstract class Message implements Serializable {
    private final MsgTypeEnum msgType;
    private final Instant timestamp;
   // private final LocalDate timestamp;
    private final String username;

    /**
     * Constructs a Message with the specified username and message type.
     * Initializes the timestamp to the current instant.
     *
     * @param username the username of the user creating the message
     * @param msgType  the type of message
     */
    public Message(String username, MsgTypeEnum msgType) {
        this.msgType = msgType;
        this.timestamp = Instant.now();
       // this.timestamp = LocalDate.now();
        this.username = username;
    }

    /**
     * Gets the message type.
     *
     * @return the message type
     */
    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    /**
     * Gets the timestamp when the message was created.
     *
     * @return the timestamp
     */
    public Instant getTimestamp() {
        return timestamp;
    }
    //public LocalDate getTimestamp() {return timestamp;}

    /**
     * Gets the username of the user who created the message.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Compares this Message to the specified object for equality.
     * The result is true if and only if the argument is not null and is a Message object
     * that has the same message type, timestamp, and username as this object.
     *
     * @param o the object to compare this Message against
     * @return true if the given object represents a Message equivalent to this Message, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        Message that = (Message) o;
        return Objects.equals(this.getMsgType(), that.getMsgType())
                && Objects.equals(this.getTimestamp(), that.getTimestamp())
                && Objects.equals(this.getUsername(), that.getUsername());
    }

    /**
     * Returns a hash code value for the Message.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(msgType, timestamp, username);
    }

    /**
     * Returns a string representation of the Message.
     *
     * @return a string representation of the Message
     */
    @Override
    public String toString() {
        return "Message{" +
                "msgTypeEnum=" + msgType +
                ", timestamp=" + timestamp +
                ", username='" + username + '\'' +
                '}';
    }
}

