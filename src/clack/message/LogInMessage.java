package clack.message;

public class LogInMessage
        extends Message
{
    public LogInMessage(String username)
    {
        super(username, MsgTypeEnum.LOGIN);
    }

    @Override
    public String toString()
    {
        return "LogInMessage{"
                + super.toString()
                + "}";
    }
}
