import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Login extends ButtonFunctions{

    public void loginApp() {
        // Frame
        JFrame frame = new JFrame("Project - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels
        JPanel loginPanel = new JPanel(new GridBagLayout());
        frame.add(loginPanel);

        // Grid Bag Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 3, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        // TextFields
        JTextField usernameEntry = new JTextField(20);
        JPasswordField passwordEntry = new JPasswordField(20);

        // Buttons
        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign up");

        // Add UsernameInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameEntry, gbc);

        // Add PasswordInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordEntry, gbc);

        // Add loginBtn, GBC
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(loginBtn, gbc);

        // Add signupBtn, GBC
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        loginPanel.add(signupBtn, gbc);


        // Key/ActionListener loginBtn
        KeyAdapter myEnterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginEvent(usernameEntry, passwordEntry, frame);
                }
            }
        };
        loginBtn.addActionListener(_ -> loginEvent(usernameEntry, passwordEntry, frame));
        usernameEntry.addKeyListener(myEnterListener);
        passwordEntry.addKeyListener(myEnterListener);
        // ActionListener signupBtn
        signupBtn.addActionListener(_ -> {
            frame.dispose();
            Signup signup = new Signup();
            signup.signupApp();
        });


        //Frame window
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
