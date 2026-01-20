import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home{
    public void homeApp(String username) {
        // Frame
        JFrame frame = new JFrame("Project - Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels
        JPanel homePanel = new JPanel(new GridBagLayout());
        frame.add(homePanel);

        // Grid Bag Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 3, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label
        JLabel helloUserLabel = new JLabel("Hi, " + username);

        // Buttons
        JButton viewBtn = new JButton("View User Details");
        JButton editBtn = new JButton("Edit User Details");
        JButton logoutBtn = new JButton("Log Out");

        // Add helloUser, gbc
        gbc.gridx = 0;
        gbc.gridy = 0;
        homePanel.add(helloUserLabel, gbc);

        // Add viewBtn, gbc
        gbc.gridx = 0;
        gbc.gridy = 1;
        homePanel.add(viewBtn, gbc);

        // Add editBtn, gbc;
        gbc.gridy = 2;
        homePanel.add(editBtn, gbc);

        // Add logoutBtn, gbc
        gbc.gridy = 3;
        homePanel.add(logoutBtn, gbc);

        // viewBtn ActionListener
        viewBtn.addActionListener(e -> {
            UserDetails userDetails = new UserDetails();
            frame.dispose();
            userDetails.userDetailsApp(username);
        });

        // editBtn ActionListener
        editBtn.addActionListener(e -> {
            EditUser edit = new EditUser();
            frame.dispose();
            edit.editUserApp(username);
        });

        // logoutBtn ActionListener
        logoutBtn.addActionListener(e -> {
            Login login = new Login();
            frame.dispose();
            login.loginApp();
        });

        // Frame window
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
