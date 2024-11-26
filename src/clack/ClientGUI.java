package clack;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

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

        final JPanel connectionPanel = new JPanel();
        connectionPanel.setBorder(BORDER);
        connectionPanel.setLayout(
                new BoxLayout(connectionPanel, BoxLayout.PAGE_AXIS));
        connectionPanel.add(connectionInfoPanel);
        connectionPanel.add(connectionButtonsPanel);


        JButton loginButton = new JButton("Log In");
        JButton logOutButton = new JButton("Log Out");
        JButton listUsersBtn = new JButton("List Users");
        JButton helpBtn = new JButton("Help");
        JButton sndFlBtn = new JButton("Send File");
        JButton clearBtn = new JButton("Clear Conversation");


        final JPanel controlPanelPt1 = new JPanel();
        controlPanelPt1.setLayout(
                new BoxLayout(controlPanelPt1, BoxLayout.X_AXIS));

        controlPanelPt1.add(loginButton);
        controlPanelPt1.add(logOutButton);
        controlPanelPt1.add(listUsersBtn);

        final JPanel controlPanelPt2 = new JPanel();
        controlPanelPt2.setLayout(
                new BoxLayout(controlPanelPt2, BoxLayout.X_AXIS));
        controlPanelPt2.add(helpBtn);
        controlPanelPt2.add(sndFlBtn);
        controlPanelPt2.add(clearBtn);

        final JTextField cipherKeyField = new JTextField(10);
        final JComboBox<String> cipherDropdown = new JComboBox<>(new String[]{"Caeser", "Playfair", "Vignere"});
        final JCheckBox cipherEnabledCheckbox = new JCheckBox("Enabled");

        final JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.add(new JLabel("Cipher Key:"));
        optionsPanel.add(cipherKeyField);
        optionsPanel.add(new JLabel("Cipher:"));
        optionsPanel.add(cipherDropdown);
        optionsPanel.add(cipherEnabledCheckbox);

        final JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(controlPanelPt1);
        controlPanel.add(controlPanelPt2);
        controlPanel.add(optionsPanel);

        final JTextArea textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        final JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));

        final JLabel questionLabel = new JLabel("Conversation: ");
        final JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(questionLabel);
        textPanel.add(areaScrollPane);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(connectionPanel);
        mainPanel.add(controlPanel);
        mainPanel.add(optionsPanel);
        mainPanel.add(textPanel);

        // Put panels together
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Clack");
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // Wire up the listeners
        connectButton.addActionListener((e) ->
                {
                    textArea.append("Connect Button clicked, connect to "
                            + hostField.getText() + ":" + portField.getText() + "\n");
                    hostField.setEnabled(false);
                    portField.setEnabled(false);
                    connectButton.setEnabled(false);
                }
        );
        disconnectButton.addActionListener((e) ->
                {
                    textArea.append("Disconnect Button clicked\n");
                    hostField.setEnabled(true);
                    portField.setEnabled(true);
                    connectButton.setEnabled(true);
                }
        );
        loginButton.addActionListener((e) ->
                {
                    JTextField usernameField = new JTextField();
                    JPasswordField passwordField = new JPasswordField();
                    Object[] message = {
                            "Username:", usernameField,
                            "Password:", passwordField
                    };
                    int option = JOptionPane.showConfirmDialog(frame, message, "Log In", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        textArea.append("\nLog In: clicked OK: username=" + usernameField.getText() + ", password=" + new String(passwordField.getPassword()) + "\n");
                    } else {
                        textArea.append("\nLog In: clicked Cancel\n");
                    }
                }
        );
        logOutButton.addActionListener((e) ->
                {
                    JTextField usernameField = new JTextField();
                    JPasswordField passwordField = new JPasswordField();
                    Object[] message = {
                            "Username:", usernameField,
                            "Password:", passwordField
                    };
                    int option = JOptionPane.showConfirmDialog(frame, message, "Log Out", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        textArea.append("\nLog Out: clicked OK: username=" + usernameField.getText() + ", password=" + new String(passwordField.getPassword()) + "\n");
                    } else {
                        textArea.append("\nLog Out: clicked Cancel\n");
                    }
                }
        );
        listUsersBtn.addActionListener((e) ->
                {
                    textArea.append("\nList Users: clicked\n");
                }
        );
        helpBtn.addActionListener((e) ->
                {
                    textArea.append("\nHelp: clicked\n");
                }
        );
        clearBtn.addActionListener((e) ->
                {
                    textArea.setText("");
                }
        );
        sndFlBtn.addActionListener((e) ->
                {
                    final JFileChooser fc = new JFileChooser();
                    int returnVal = fc.showOpenDialog(frame);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        textArea.append("Send File: clicked OK: file=" + file.toString() + ".\n");
                    } else {
                        textArea.append("Send File: clicked Cancel\n");
                    }

                }
        );

        frame.pack();
        frame.setLocation(x, y);
        frame.setVisible(true);
    }
}
