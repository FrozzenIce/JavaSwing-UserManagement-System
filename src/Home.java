import javax.swing.*;
import java.awt.*;

public class Home extends JFrame{
    private JLabel usernameLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;

    private JMenuBar menuBar;
    private JMenu accountMenu;
    private JMenuItem editMenuItem;
    private JMenuItem logoutMenuItem;
    private JMenuItem deleteMenuItem;

    private String username;
    private String address;
    private String email;
    private String phone;

    private static final Color ACCENT = new Color(172, 110, 142);
    private static final Color PINK = Color.decode("#ea077c");
    private static final Color WHITE = Color.WHITE;

    private static final Font DETAIL_FONT = new Font("Ubuntu", Font.BOLD, 15);
    private static final Font MENU_FONT = new Font("Roboto", Font.BOLD, 12);

    public Home(String appUsername) {
        this.username = appUsername;
        initFrame();
        initMenuBar();
        initComponents();
        layoutInit();
    }

    private void addHoverEffect(JMenuItem item) {
        item.addChangeListener(e -> {
            ButtonModel model = item.getModel();
            item.setBackground((model.isArmed() || model.isRollover()) ? new Color(240, 240, 240) : WHITE);
        });
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(WHITE);
        menuBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));

        accountMenu = new JMenu("Account");
        accountMenu.setFont(MENU_FONT);
        accountMenu.setOpaque(true);
        accountMenu.setBackground(WHITE);
        accountMenu.setForeground(Color.BLACK);
        accountMenu.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        editMenuItem = new JMenuItem("Edit Profile");
        editMenuItem.setOpaque(true);
        editMenuItem.setBackground(WHITE);
        editMenuItem.setForeground(Color.BLACK);
        editMenuItem.setFont(MENU_FONT);
        editMenuItem.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));

        logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.setOpaque(true);
        logoutMenuItem.setBackground(WHITE);
        logoutMenuItem.setForeground(Color.BLACK);
        logoutMenuItem.setFont(MENU_FONT);
        logoutMenuItem.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));


        deleteMenuItem = new JMenuItem("Delete Profile");
        deleteMenuItem.setOpaque(true);
        deleteMenuItem.setBackground(WHITE);
        deleteMenuItem.setForeground(Color.BLACK);
        deleteMenuItem.setFont(MENU_FONT);
        deleteMenuItem.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));

        addHoverEffect(editMenuItem);
        addHoverEffect(logoutMenuItem);
        addHoverEffect(deleteMenuItem);

        deleteMenuItem.addActionListener(_ -> {
            new DeleteUser(this, username);
        });

        editMenuItem.addActionListener(_ -> {
            this.dispose();
            new EditUser(username);
        });

        logoutMenuItem.addActionListener(_ -> {
            this.dispose();
            new Login();
        });

        accountMenu.add(editMenuItem);
        accountMenu.addSeparator();
        accountMenu.add(deleteMenuItem);
        accountMenu.addSeparator();
        accountMenu.add(logoutMenuItem);

        menuBar.add(accountMenu);
        setJMenuBar(menuBar);
    }

    public void initFrame() {
        setTitle("Home - UMS");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void getDetails() {
        DatabaseFunction databaseFunction = new DatabaseFunction();
        User user = databaseFunction.fetchUserDetails(username);

        if (user == null) {
            username = "(unknown)";
            address = "(unknown)";
            email = "(unknown)";
            phone = "(unknown)";
            return;
        }

        username = user.getUsername();
        address = user.getAddress();
        email = user.getEmail();
        phone = user.getPhone();
    }

    public void initComponents() {
        getDetails();

        usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        usernameLabel.setForeground(Color.DARK_GRAY);

        addressLabel = new JLabel("Address: " + address);
        addressLabel.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        addressLabel.setForeground(Color.DARK_GRAY);

        emailLabel = new JLabel("Email: " + email);
        emailLabel.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        emailLabel.setForeground(Color.DARK_GRAY);

        phoneLabel = new JLabel("Phone: " + phone);
        phoneLabel.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        phoneLabel.setForeground(Color.DARK_GRAY);
    }

    public void layoutInit() {
        JPanel homePanel = new JPanel(new GridBagLayout());
        homePanel.setBackground(Color.WHITE);
        homePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PINK, 2),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);

        // HEADER
        JLabel header = new JLabel("USER PROFILE");
        header.setFont(new Font("Roboto", Font.BOLD, 16));
        header.setForeground(PINK);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        gbc.gridy = 0;
        homePanel.add(header, gbc);

        gbc.gridy++;
        homePanel.add(usernameLabel, gbc);

        gbc.gridy++;
        homePanel.add(addressLabel, gbc);

        gbc.gridy++;
        homePanel.add(emailLabel, gbc);

        gbc.gridy++;
        homePanel.add(phoneLabel, gbc);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(250, 250, 250));
        wrapper.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        wrapper.add(homePanel);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(250, 250, 250));
        root.add(wrapper, BorderLayout.CENTER);

        setContentPane(root);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
