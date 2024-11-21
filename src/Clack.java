import clack.endpoint.Client;
import clack.endpoint.Server;

/**
 * Main program. Based on command-line arguments (see USAGE
 * string), creates and starts either a client or a server.
 * To use, create a single server on a given host and port.
 * Clients (possibly multiple) can then be created, each in
 * their own terminal/command window. The client can accept
 * "localhost" as a server name. If a client cannot connect
 * to a server on a remote host, check firewall settings on
 * the host.
 * <p>
 * The usage message assumes this program has been built as
 * a jar file. IDEs vary in how they do this; check the IDE
 * documentation.
 */
public class Clack
{
    private final static String USAGE =
            "Usage: java Clack client <server name> <server port>\n"
                    + "       java Clack server <server port>";

    public static void main(String[] args)
    {
//        System.out.println("myTest!!");
        //server
        if (args.length < 2) {
            System.err.println(USAGE);
        } else if (args.length == 2
                && args[0].equalsIgnoreCase("server"))
        {
            try {
                int port = Integer.parseInt(args[1]);
                Server server = new Server(port, Server.DEFAULT_SERVERNAME);
                server.start();
            } catch (NumberFormatException e) {
                System.err.println(args[1] + " cannot be parsed as an int.");
                System.err.println(USAGE);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println(USAGE);
            }

            //client
        } else if (args.length == 3
                && args[0].equalsIgnoreCase("client"))
        {
            try {
                String serverName = args[1];
//                String password = "text";
                int port = Integer.parseInt(args[2]);
//                System.out.println("hehe Connecting to " + serverName + " on port " + port);
                Client client =
                        new Client(serverName, port, Client.DEFAULT_USERNAME);
                System.out.println("gooot heeerree v1");
                client.start(); //TODO: there is an issue with the start method....needs fixing
                System.out.println("gooot heeerree v2");
            } catch (NumberFormatException e) {
                System.err.println(args[2] + " cannot be parsed as an int.");
                System.err.println(USAGE);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println(USAGE);
            }
        } else {
            System.err.println(USAGE);
        }
    }
}
