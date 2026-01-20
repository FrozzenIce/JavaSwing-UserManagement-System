import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Signup {
    char[] password, confPassword;
    private String username, address, email, phone;

    public void signupApp() {
        // Frame
        JFrame frame = new JFrame("Project - Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels
        JPanel signupPanel = new JPanel(new GridBagLayout());
        frame.add(signupPanel);

        // Grid Bag Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 3, 2);
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
        JButton loginBtn = new JButton("Login");
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

        // Add LoginBtnInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        signupPanel.add(loginBtn, gbc);

        // Add SignupBtnInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        signupPanel.add(signupBtn, gbc);


        // loginBtn ActionListener
        loginBtn.addActionListener(e -> {
            frame.dispose();
            Login login = new Login();
            login.loginApp();
        });

        // signupBtn ActionListener
        signupBtn.addActionListener(e -> {
            username = usernameEntry.getText();
            password = passwordEntry.getPassword();
            confPassword = confirmPasswordEntry.getPassword();
            address = addressEntry.getText();
            email = emailEntry.getText();
            phone = phoneEntry.getText();
            ErrorDialog d = new ErrorDialog();
            DatabaseFunction database = new DatabaseFunction();
            if (username.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty() || Arrays.equals(password, "".toCharArray()) || Arrays.equals(confPassword, "".toCharArray())) { // throws error if any one field is empty
                d.signupEmpty();
            } else if (!database.checkUsernameAvailability(username)) { // Checks username availability, throws error if unavailable
                d.errorUsernameAvailability();
            } else if (password.length < 8) { // shows error if password length < 8
                d.errorPassSignup();
            } else if (!Arrays.equals(password, confPassword)) { // checks if entered password and confirmation password is correct
                d.signupPassError();
            } else { // Sends the entered details to the database
                database.sendSignupData(username, password, address, email, phone);
                Login login = new Login();
                frame.dispose();
                login.loginApp();
            }
        });

        // Frame window
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
