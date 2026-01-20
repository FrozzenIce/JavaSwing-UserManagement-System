import javax.swing.*;
import java.awt.*;

public class UserDetails {

    public void userDetailsApp(String appUsername) {
        // Frame
        JFrame frame = new JFrame("Project - User Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel
        JPanel userDetailsPanel = new JPanel(new GridBagLayout());
        frame.add(userDetailsPanel);

        // GBC
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 3, 10);

        // Fetch user details from db
        Database database = new Database();
        User user = database.fetchUserDetails(appUsername);

        // Labels
        JLabel usernameLabel = new JLabel("Username: " + user.getUsername());
        JLabel addressLabel = new JLabel("Address: " + user.getAddress());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());
        JLabel phoneLabel = new JLabel("Phone: " + user.getPhone());

        // Button
        JButton backBtn = new JButton("Back");

        // Add usernameLabel, gbc
        gbc.gridx = 0;
        gbc.gridy = 0;
        userDetailsPanel.add(usernameLabel, gbc);

        // Add usernameLabel, gbc
        gbc.gridy = 1;
        userDetailsPanel.add(addressLabel, gbc);

        // Add usernameLabel, gbc
        gbc.gridy = 2;
        userDetailsPanel.add(emailLabel, gbc);

        // Add usernameLabel, gbc
        gbc.gridy = 3;
        userDetailsPanel.add(phoneLabel, gbc);

        // Add backBtn, gbc
        gbc.gridy = 4;
        userDetailsPanel.add(backBtn, gbc);

        // backBtn ActionListener
        backBtn.addActionListener(_ -> {
            Home home = new Home();
            frame.dispose();
            home.homeApp(appUsername);
        });

        // Frame
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
