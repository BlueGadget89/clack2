package clack;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ClientGUI {

    /**
     * Constructs a client GUI.
     */
    public ClientGUI(int x, int y) {
        // The border style we'll use
        final Border BORDER =
                BorderFactory.createLineBorder(Color.LIGHT_GRAY);

        // Connection panel: host, port, buttons
        //host
        final JLabel hostLabel = new JLabel("Host: ");
        final JTextField hostField = new JTextField("localhost", 20); //

        //port
        final JLabel portLabel = new JLabel("Port: ");
        final JTextField portField = new JTextField("4466", 5);

        //adds in the elements
        final JPanel connectionInfoPanel = new JPanel();
        connectionInfoPanel.setLayout(
                new BoxLayout(connectionInfoPanel, BoxLayout.LINE_AXIS));
        connectionInfoPanel.add(hostLabel);
        connectionInfoPanel.add(hostField);
        connectionInfoPanel.add(portLabel);
        connectionInfoPanel.add(portField);

        // Connect and Disconnect buttons, each centered in their own
        // small pane, and these panes side-by-side.
        final JButton connectButton = new JButton("Connect");
        final JButton disconnectButton = new JButton("Disconnect");

        final JPanel connectionButtonsPanel = new JPanel();
        connectionButtonsPanel.setLayout(new FlowLayout());
        connectionButtonsPanel.add(connectButton);
        connectionButtonsPanel.add(Box.createRigidArea((new Dimension(20, 0))));
        connectionButtonsPanel.add(disconnectButton);

        // Connect and Disconnect buttons, each centered in their own
        // small pane, and these panes side-by-side.
        final JButton LoginButton = new JButton("Log In");
        final JButton LogoutButton = new JButton("Log Out");

        final JPanel FunctionsButtonsPanel = new JPanel();
        FunctionsButtonsPanel.setLayout(new FlowLayout());
        FunctionsButtonsPanel.add(LoginButton);
        FunctionsButtonsPanel.add(Box.createRigidArea((new Dimension(20, 0))));
        FunctionsButtonsPanel.add(LogoutButton);

        final JPanel connectionPanel = new JPanel();
        connectionPanel.setBorder(BORDER);
        connectionPanel.setLayout(
                new BoxLayout(connectionPanel, BoxLayout.PAGE_AXIS));
        connectionPanel.add(connectionInfoPanel);
        connectionPanel.add(connectionButtonsPanel);

        // Build logPane: conversation log
        final JTextArea log = new JTextArea();
        log.setEditable(false);
        log.setRows(6);
        log.setLineWrap(true);
        final JScrollPane logPane = new JScrollPane(log);

        // Build questionPanel: where user enters questions
        final JLabel questionLabel = new JLabel("Your question: ");
        final JTextField questionField = new JTextField(30);
        final JPanel questionPanel = new JPanel();
        questionPanel.setBorder(BORDER);
        questionPanel.setLayout(
                new BoxLayout(questionPanel, BoxLayout.LINE_AXIS));
        questionPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 5, 10, 5));
        questionPanel.add(questionLabel);
        questionPanel.add(questionField);

        // Put panels together
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Magic 8 Ball");
        frame.setLayout(new BorderLayout());
        frame.add(connectionPanel, BorderLayout.NORTH);
        frame.add(logPane, BorderLayout.CENTER);
        frame.add(questionPanel, BorderLayout.SOUTH);

        // Wire up the listeners
        connectButton.addActionListener((e) ->
                {
                    log.append("Connect Button clicked, connect to "
                            + hostField.getText() + ":" + portField.getText() + "\n");
                    hostField.setEnabled(false);
                    portField.setEnabled(false);
                    connectButton.setEnabled(false);
                }
        );
        disconnectButton.addActionListener((e) ->
                {
                    log.append("Disconnect Button clicked\n");
                    hostField.setEnabled(true);
                    portField.setEnabled(true);
                    connectButton.setEnabled(true);
                }
        );
        questionField.addActionListener((e) ->
                log.append("User entered: '" + questionField.getText() + "'\n")
        );

        frame.pack();
        frame.setLocation(x, y);
        frame.setVisible(true);
    }
}
