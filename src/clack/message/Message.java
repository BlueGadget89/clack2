package clack.message;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public abstract class Message implements Serializable
{
    private final MsgTypeEnum msgTypeEnum;
    private final Instant timestamp;
    private final String username;

    public Message(String username, MsgTypeEnum msgTypeEnum)
    {
        this.msgTypeEnum = msgTypeEnum;
        this.timestamp = Instant.now();
        this.username = username;
    }

    public MsgTypeEnum getMsgType()
    {
        return msgTypeEnum;
    }

    public Instant getTimestamp()
    {
        return timestamp;
    }

    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return msgTypeEnum == message.msgTypeEnum
                && Objects.equals(timestamp, message.timestamp)
                && Objects.equals(username, message.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgTypeEnum, timestamp, username);
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "msgType=" + msgTypeEnum +
                ", timestamp=" + timestamp +
                ", username='" + username + '\'' +
                '}';
    }
}
