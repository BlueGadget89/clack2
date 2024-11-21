package clack.message;

public class LogInMessage
        extends Message
{
    private String password;

    public LogInMessage(String username, String password)
    {
        super(username, MsgTypeEnum.LOGIN);
        this.password = password;
    }

    @Override
    public String toString() //returns it as asterix
    {
        return "LogInMessage{"
                + super.toString()
                + "}";
    }

    public String getPassword()
    {
        return password;
    }
}
