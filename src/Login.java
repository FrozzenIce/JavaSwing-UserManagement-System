import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Login {
    private String username;
    private char[] password;

    public void loginApp() {
        // Frame
        JFrame frame = new JFrame("Project - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels
        JPanel loginPanel = new JPanel(new GridBagLayout());
        frame.add(loginPanel);

        // Grid Bag Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 3, 2);
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


        // ActionListener loginBtn
        loginBtn.addActionListener(e -> {
            username = usernameEntry.getText();
            password = passwordEntry.getPassword();
            ErrorDialog d = new ErrorDialog();
            if (username.isEmpty()) { // throws error if username is empty
                d.errorEmptyUsername();
            } else if (Arrays.equals(password, "".toCharArray())) { // throws error if password is empty
                d.errorEmptyPassword();
            } else if (password.length < 8) { // throws error if password length < 8
                d.errorPasswordLength();
                passwordEntry.setText("");
            } else {
                Database database = new Database();
                if (database.validation(username, password)) { // Validates user details
                    Arrays.fill(password, '\0');
                    Home home = new Home();
                    frame.dispose();
                    home.homeApp(username);
                } else { // throws login failure window
                    d.loginFailure();
                }
                usernameEntry.setText("");
                passwordEntry.setText("");
            }
        });

        // ActionListener signupBtn
        signupBtn.addActionListener(e -> {
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
