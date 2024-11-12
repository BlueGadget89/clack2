package clack.endpoint;

import clack.message.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This is a simple client class for exchanging Message objects
 * with a server. The exchange is conversational, that is, one
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
public class Client {
    public static final String DEFAULT_USERNAME = "client";

    private final String hostname;
    private final int port;
    private final String prompt;
    private final String username;

    /**
     * Creates a client for exchanging Message objects.
     *
     * @param hostname the hostname of the server.
     * @param port     the service's port on the server.
     * @param username username to include in Messages.
     * @throws IllegalArgumentException if port not in range [1-49151]
     */
    public Client(String hostname, int port, String username) {
        if (port < 1 || port > 49151) {
            throw new IllegalArgumentException(
                    "Port " + port + " not in range 1 - 49151.");
        }
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.prompt = "hostname:" + port + "> ";
    }

    /**
     * Creates a client for exchanging Message objects, using the
     * default username (Client.DEFAULT_USERNAME).
     *
     * @param hostname the hostname of the server.
     * @param port     the service's port on the server.
     * @throws IllegalArgumentException if port not in range [1-49151]
     */
    public Client(String hostname, int port) {
        this(hostname, port, DEFAULT_USERNAME);
    }

    /**
     * Starts this client, connecting to the server and port that
     * it was given when constructed.
     *
     * @throws UnknownHostException if hostname is not resolvable.
     * @throws IOException          if socket creation, wrapping, or IO fails.
     */
    public void start() throws UnknownHostException, IOException, ClassNotFoundException {
        System.out.println("Attempting connection to " + hostname + ":" + port);
        Scanner keyboard = new Scanner(System.in);

        try (
                Socket socket = new Socket(hostname, port);

                ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream()); //reads from socket
                ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream()); //writes to socket
        ) {
            String userInput;
            Message inMsg;
            Message outMsg;

            // Take turns talking. Server goes first.
            do {
                // Get server message and show it to user.
                inMsg = (Message) inObj.readObject();

                outMsg = switch (inMsg.getMsgType()) {
                    case MsgTypeEnum.LISTUSERS -> new TextMessage(username, "LISTUSERS requested");
                    case MsgTypeEnum.LOGOUT -> new TextMessage(username, "GOOD_BYE");
                    case MsgTypeEnum.TEXT -> new TextMessage(username,
                            "TEXT: '" + ((TextMessage) inMsg).getText() + "'");
                    case MsgTypeEnum.LOGIN -> new TextMessage(username, "LOGIN requested");
                };

                // Get user input
                System.out.print(prompt);
                userInput = keyboard.nextLine();
                String[] tokens = userInput.trim().split("\\s+");
                // DEBUG
                // System.out.println("tokens: " + Arrays.toString(tokens));

                // Construct Message based on user input and send it to server.
                outMsg = switch (inMsg.getMsgType()) {
                    case MsgTypeEnum.LISTUSERS -> new ListUsersMessage(username);
                    case MsgTypeEnum.LOGOUT -> new LogoutMessage(username);
                    case MsgTypeEnum.TEXT -> new TextMessage(username,
                            "TEXT: '" + ((TextMessage) inMsg).getText() + "'");
                    case MsgTypeEnum.LOGIN -> new LogInMessage(username);
                };

                outObj.writeObject(outMsg);
                outObj.flush();
            } while (outMsg.getMsgType() != MsgTypeEnum.LOGOUT);

            // Get server's closing reply and show it to user.
            inMsg = (Message) inObj.readObject();
            System.out.println(
                    switch (inMsg.getMsgType()) {
                        case LISTUSERS -> "UNEXPECTED RESPONSE: " + inMsg;
                        case LOGOUT -> "UNEXPECTED RESPONSE: " + inMsg;
                        case TEXT -> ((TextMessage) inMsg).getText();
                        case LOGIN -> "UNEXPECTED RESPONSE: " + inMsg;
                    });
        }   // Streams and sockets closed by try-with-resources

        System.out.println("Connection to " + hostname + ":" + port
                + " closed, exiting.");
    }
}

