package clack.endpoint;

import clack.message.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is a simple server class for exchanging Message objects
 * with a client. The exchange is conversational, that is, one
 * side sends a Message, then waits for a reply Message from the
 * other side, then sends another Message, waits for a reply,
 * and so on.
 * <p>
 * To begin a conversation, a client connects to the server
 * and waits for the server to send the first Message.
 * <p>
 * The conversation ends when the client sends a LogoutMessage.
 * The server replies with a last TextMessage, closes the
 * connection, and waits for a new connection.
 */
public class Server
{
    public static final String DEFAULT_SERVERNAME = "server";

    // For strings sent to client.
    private static final String GREETING =
            "[Server listening. 'Logout' (case insensitive) closes connection.]";
    private static final String GOOD_BYE =
            "[Closing connection, good-bye.]";

    // Object variables.
    private final int port;
    private final String serverName;
    private final boolean SHOW_TRAFFIC = true;      // FOR DEBUGGING

    /**
     * Creates a server for exchanging Message objects.
     *
     * @param port       the port to listen on.
     * @param serverName the name to use when constructing Message objects.
     * @throws IllegalArgumentException if port not in range [1024, 49151].
     */
    public Server(int port, String serverName)
            throws IllegalArgumentException
    {
        if (port < 1024 || port > 49151) {
            throw new IllegalArgumentException(
                    "Port " + port + " not in range 1024-49151.");
        }
        this.port = port;
        this.serverName = serverName;
    }

    /**
     * Creates a server for exchanging Message objects, using the
     * default servername (Server.DEFAULT_SERVERNAME).
     *
     * @param port       the port to listen on.
     * @throws IllegalArgumentException if port not in range [1024, 49151].
     */
    public Server(int port) {
        this(port, DEFAULT_SERVERNAME);
    }

    /**
     * Starts this server, listening on the port it was
     * constructed with.
     *
     * @throws IOException if ServerSocket creation, connection
     *                     acceptance, wrapping, or IO fails.
     */
    public void start() throws IOException, ClassNotFoundException
    {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server starting on port " + port + ".");
            System.out.println("Ctrl + C to exit.");
            try (
                    // Wait for connection.
                    Socket clientSocket = serverSocket.accept();

                    // Build streams on the socket.
                    ObjectOutputStream outObj =
                            new ObjectOutputStream(clientSocket.getOutputStream());
                    ObjectInputStream inObj =
                            new ObjectInputStream(clientSocket.getInputStream());
            )
            {
                Message inMsg;
                Message outMsg;
                boolean loginSuccess = false;

                //log in conversation
                do {
                    // Connection made. Greet client. SendGreeting to client
                    outMsg = new TextMessage(serverName, GREETING);
                    outObj.writeObject(outMsg);
                    outObj.flush();
                    if (SHOW_TRAFFIC) {
                        System.out.println("=> " + outMsg);
                    }

                    inMsg = (Message) inObj.readObject(); //this is a Message

                    //process the password
                    if (inMsg.getMsgType() == MsgTypeEnum.LOGIN) {
                        String password = ((LoginMessage) inMsg).getPassword(); // get the password
                        StringBuilder strBld = new StringBuilder(password);
                        StringBuilder strBld_reverse = strBld.reverse();
                        String reversePass = strBld_reverse.toString();
                        if ((inMsg.getUsername()).equalsIgnoreCase(reversePass)) {
                            //print success to client
                            outMsg = new TextMessage(serverName, "Log in successful.");
                            outObj.writeObject(outMsg);
                            outObj.flush();
                            loginSuccess = true;
                        }else{

                        }
                    }
                } while(!loginSuccess);

                //after log in
                // Converse with client.
                do {
                    inMsg = (Message) inObj.readObject();
                    if (SHOW_TRAFFIC) {
                        System.out.println("<= " + inMsg);
                    }

                    //if it is an option message, store the value
                    if (inMsg.getMsgType() == MsgTypeEnum.OPTION) {
                        //get the option
                        OptionEnum storedOption = ((OptionMessage) inMsg).getOption();
                    }

                    // Process the received message
                    outMsg = switch (inMsg.getMsgType()) {
                        case MsgTypeEnum.HELP ->
                                new TextMessage(serverName, "Command Choices: " +
                                        "HELP, " +
                                        "LIST USERS," +
                                        "OPTION, " +
                                        "SEND FILE, " +
                                        "LOGOUT");
                        case MsgTypeEnum.LISTUSERS ->
                                new TextMessage(serverName, "LIST USERS requested");
                        case MsgTypeEnum.OPTION ->
                                new TextMessage(serverName, "OptionsMsg requested"); //TODO: How should it store the 'value' argument?
                        case MsgTypeEnum.FILE ->
                                new TextMessage(serverName, "FILE received");
                        case MsgTypeEnum.TEXT ->
                                new TextMessage(serverName, "TEXT: '" + ((TextMessage) inMsg).getText() + "'");
                        case MsgTypeEnum.LOGIN ->
                                new TextMessage(serverName, "You are already logged in.");
                        case MsgTypeEnum.LOGOUT ->
                                new TextMessage(serverName, GOOD_BYE);
                    };
                    //send it back to the Client
                    outObj.writeObject(outMsg);
                    outObj.flush();
                    if (SHOW_TRAFFIC) {
                        System.out.println("=> " + outMsg);
                    }

                } while (inMsg.getMsgType() != MsgTypeEnum.LOGOUT);

                System.out.println("=== Terminating connection. ===");
            }   // Streams and socket closed by try-with-resources.
        } // Server socket closed by try-with-resources.
    }
}
