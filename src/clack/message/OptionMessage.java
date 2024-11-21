package clack.message;

import java.util.Objects;

public class OptionMessage extends Message {
    private final OptionEnum option;
    private final String value;

    public OptionMessage(String username, OptionEnum option, String value) {
        super(username, MsgTypeEnum.OPTION);
        this.option = option;
        this.value = value;
    }

    public OptionEnum getOption() {
        return option;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
     //   if (!super.equals(o)) return false;
        OptionMessage that = (OptionMessage) o;
        return Objects.equals(this.getOption(), that.getOption())
                && Objects.equals(this.getValue(), that.getValue());
       // return option == that.option &&
              //  Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), option, value);
    }

    @Override
    public String toString() {
        return "OptionMessage{" +
                "option=" + option +
                ", value='" + value + '\'' +
                "} " + super.toString();
    }
}

