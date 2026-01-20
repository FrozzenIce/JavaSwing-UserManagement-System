import javax.swing.*;
import java.awt.*;

public class EditUser {
    char[] password;
    private String username, address, email, phone;

    public void editUserApp(String appUsername) {
        // Frame
        JFrame frame = new JFrame("Project - Edit User Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels
        JPanel editPanel = new JPanel(new GridBagLayout());
        frame.add(editPanel);

        // Grid Bag Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 3, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels
        JLabel noteLabel = new JLabel("<html>Only fill data that is to be updated, fields with <span style='color:red'>*</span> must be filled</html>");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel addressLabel = new JLabel(("Address: "));
        JLabel phoneLabel = new JLabel("Phone no.: ");
        JLabel emailLabel = new JLabel("Email: ");
        JLabel changePasswordLabel = new JLabel("<html>Enter Password <span style='color:red'>*</span></html>");
        JLabel passwordLabel = new JLabel("Password: ");


        // TextFields
        JTextField usernameEntry = new JTextField(20);
        JTextField addressEntry = new JTextField(30);
        JTextField emailEntry = new JTextField(20);
        JTextField phoneEntry = new JTextField(20);
        JPasswordField passwordEntry = new JPasswordField(20);

        // Buttons
        JButton updateBtn = new JButton("Update");
        JButton backBtn = new JButton("Back");

        // Add NoteLabel, gbc
        gbc.gridx = 0;
        gbc.gridy = 0;
        editPanel.add(noteLabel, gbc);

        // Add UsernameInsertion, GBC
        gbc.gridy = 1;
        editPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        editPanel.add(usernameEntry, gbc);


        // Add AddressInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 2;
        editPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        editPanel.add(addressEntry, gbc);

        // Add EmailInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 3;
        editPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        editPanel.add(emailEntry, gbc);

        // Add PhoneInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 4;
        editPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        editPanel.add(phoneEntry, gbc);

        // Add ChangePasswordLabel, GBC
        gbc.gridx = 0;
        gbc.gridy = 5;
        editPanel.add(changePasswordLabel, gbc);

        // Add OldPasswordInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 6;
        editPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        editPanel.add(passwordEntry, gbc);


        // Add LoginBtnInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        editPanel.add(updateBtn, gbc);

        // Add SignupBtnInsertion, GBC
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        editPanel.add(backBtn, gbc);

        // updateBtn ActionListener
        updateBtn.addActionListener(_ -> {
            username = usernameEntry.getText();
            if (username.trim().isEmpty()) {
                username = null;
            }
            address = addressEntry.getText();
            if (address.trim().isEmpty()) {
                address = null;
            }
            email = emailEntry.getText();
            if (email.trim().isEmpty()) {
                email = null;
            }
            phone = phoneEntry.getText();
            if (phone.trim().isEmpty()) {
                phone = null;
            }
            password = passwordEntry.getPassword();
            ErrorDialog dialog = new ErrorDialog();
            DatabaseFunction database = new DatabaseFunction();
            if (database.validation(appUsername, password)) {
                database.updateUserDetails(appUsername, username, address, email, phone);
                usernameEntry.setText("");
                passwordEntry.setText("");
                addressEntry.setText("");
                emailEntry.setText("");
                phoneEntry.setText("");
            } else {
                dialog.updateFailed();
            }
        });

        // backBtn ActionListener
        backBtn.addActionListener(_ -> {
            frame.dispose();
            Home home = new Home();
            home.homeApp(appUsername);
        });

        // Frame window
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
