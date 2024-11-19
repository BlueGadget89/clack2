package clack.message;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Message implements Serializable {
    private final MsgTypeEnum msgType;
    private final LocalDate timestamp;
    private final String username;

    public Message(String username, MsgTypeEnum msgType) {
        this.msgType = msgType;
        this.timestamp = LocalDate.now();
        this.username = username;
    }

    public MsgTypeEnum getMsgType() {
        return msgType;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
        Message that = (Message) o;
        return Objects.equals(this.getMsgType(), that.getMsgType())
                && Objects.equals(this.getTimestamp(), that.getTimestamp())
                && Objects.equals(this.getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgType, timestamp, username);
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgType=" + msgType +
                ", timestamp=" + timestamp +
                ", username='" + username + '\'' +
                '}';
    }
}

