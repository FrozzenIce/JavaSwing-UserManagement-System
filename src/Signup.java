import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Signup extends ButtonFunctions {

    public void signupApp() {

        // Frame
        JFrame frame = new JFrame("Project - Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels
        JPanel signupPanel = new JPanel(new GridBagLayout());
        frame.add(signupPanel);

        // Grid Bag Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        JLabel addressLabel = new JLabel(("Address: "));
        JLabel phoneLabel = new JLabel("Phone no.: ");
        JLabel emailLabel = new JLabel("Email: ");

        // TextFields
        JTextField usernameEntry = new JTextField(20);
        JPasswordField passwordEntry = new JPasswordField(20);
        JPasswordField confirmPasswordEntry = new JPasswordField(20);
        JTextField addressEntry = new JTextField(30);
        JTextField emailEntry = new JTextField(20);
        JTextField phoneEntry = new JTextField(20);

        // Buttons
        JButton backBtn = new JButton("Back");
        JButton signupBtn = new JButton("Sign up");

        // Add UsernameInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 0;
        signupPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        signupPanel.add(usernameEntry, gbc);

        // Add PasswordInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 1;
        signupPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        signupPanel.add(passwordEntry, gbc);

        // Add ConfirmPasswordInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 2;
        signupPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        signupPanel.add(confirmPasswordEntry, gbc);

        // Add AddressInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 3;
        signupPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        signupPanel.add(addressEntry, gbc);

        // Add EmailInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 4;
        signupPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        signupPanel.add(emailEntry, gbc);

        // Add PhoneInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 5;
        signupPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        signupPanel.add(phoneEntry, gbc);

        // Add SignupBtnInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        signupPanel.add(signupBtn, gbc);

        // Add LoginBtnInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        signupPanel.add(backBtn, gbc);

        // backBtn ActionListener
        backBtn.addActionListener(_ -> {
            frame.dispose();
            Login login = new Login();
            login.loginApp();
        });

        // signupBtn ActionListener
        KeyAdapter myEnterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signupEvent(usernameEntry, passwordEntry, confirmPasswordEntry, addressEntry, emailEntry, phoneEntry, frame);
                }
            }
        };
        signupBtn.addActionListener(_ -> signupEvent(usernameEntry, passwordEntry, confirmPasswordEntry, addressEntry, emailEntry, phoneEntry, frame));
        usernameEntry.addKeyListener(myEnterListener);
        passwordEntry.addKeyListener(myEnterListener);
        confirmPasswordLabel.addKeyListener(myEnterListener);
        addressEntry.addKeyListener(myEnterListener);
        emailEntry.addKeyListener(myEnterListener);
        passwordEntry.addKeyListener(myEnterListener);

        // Frame window
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
