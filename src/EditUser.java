import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditUser extends JFrame {
    private JTextField usernameEntry;
    private JPasswordField passwordEntry;
    private JTextField addressEntry;
    private JTextField emailEntry;
    private JTextField phoneEntry;

    private JButton updateBtn;
    private JButton backBtn;

    // This is the currently logged-in username (the one passed from Home)
    private String appUsername;

    private static final Color ACCENT = new Color(172, 110, 142);
    private static final Color PINK = Color.decode("#ea077c");
    private static final Color WHITE = Color.WHITE;

    private static final Font TITLE_FONT = new Font("Ubuntu", Font.BOLD, 11);
    private static final Font BTN_FONT = new Font("Roboto", Font.BOLD, 11);

    public EditUser(String username) {
        this.appUsername = username;

        initFrame();
        initComponent();
        preloadUserDetails();
        layoutInit();
    }

    private void initFrame() {
        setTitle("Edit - UMS");
        setResizable(false);
        setSize(360, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponent() {
        Border line = BorderFactory.createLineBorder(ACCENT, 1, true);
        Border padding = BorderFactory.createEmptyBorder(5, 8, 5, 8);

        usernameEntry = new JTextField(20);
        usernameEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(line, " Username ", 0, 0, TITLE_FONT, ACCENT),
                padding
        ));

        passwordEntry = new JPasswordField(20);
        passwordEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(line, " Password (confirm) ", 0, 0, TITLE_FONT, ACCENT),
                padding
        ));

        addressEntry = new JTextField(20);
        addressEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(line, " Address ", 0, 0, TITLE_FONT, ACCENT),
                padding
        ));

        emailEntry = new JTextField(20);
        emailEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(line, " Email ", 0, 0, TITLE_FONT, ACCENT),
                padding
        ));

        phoneEntry = new JTextField(20);
        phoneEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(line, " Phone ", 0, 0, TITLE_FONT, ACCENT),
                padding
        ));

        backBtn = new JButton("BACK");
        backBtn.setForeground(WHITE);
        backBtn.setBackground(PINK);
        backBtn.setFont(BTN_FONT);
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        updateBtn = new JButton("UPDATE");
        updateBtn.setForeground(WHITE);
        updateBtn.setBackground(PINK);
        updateBtn.setFont(BTN_FONT);
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void preloadUserDetails() {
        DatabaseFunction db = new DatabaseFunction();
        User user = db.fetchUserDetails(appUsername);

        if (user == null) {
            usernameEntry.setText(appUsername);
            return;
        }

        usernameEntry.setText(user.getUsername());
        addressEntry.setText(user.getAddress());
        emailEntry.setText(user.getEmail());
        phoneEntry.setText(user.getPhone());
    }

    private void layoutInit() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel header = new JLabel("EDIT USER");
        header.setFont(new Font("Roboto", Font.BOLD, 16));
        header.setForeground(PINK);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(header, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(usernameEntry, gbc);

        gbc.gridy++;
        panel.add(passwordEntry, gbc);

        gbc.gridy++;
        panel.add(addressEntry, gbc);

        gbc.gridy++;
        panel.add(emailEntry, gbc);

        gbc.gridy++;
        panel.add(phoneEntry, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(updateBtn, gbc);

        gbc.gridy++;
        panel.add(backBtn, gbc);

        // Actions
        backBtn.addActionListener(_ -> {
            this.dispose();
            new Home(appUsername);
        });

        updateBtn.addActionListener(_ -> updateEvent());

        // Enter key triggers
        usernameEntry.addKeyListener(myEnterListener);
        passwordEntry.addKeyListener(myEnterListener);
        addressEntry.addKeyListener(myEnterListener);
        emailEntry.addKeyListener(myEnterListener);
        phoneEntry.addKeyListener(myEnterListener);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(WHITE);
        root.add(panel, BorderLayout.CENTER);

        setContentPane(root);
        setVisible(true);
    }

    private final KeyAdapter myEnterListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                updateEvent();
            }
        }
    };

    private void updateEvent() {
        String newUsername = usernameEntry.getText().trim();
        String newAddress = addressEntry.getText().trim();
        String newEmail = emailEntry.getText().trim();
        String newPhone = phoneEntry.getText().trim();
        char[] password = passwordEntry.getPassword();

        if (newUsername.isEmpty()) newUsername = null;
        if (newAddress.isEmpty()) newAddress = null;
        if (newEmail.isEmpty()) newEmail = null;
        if (newPhone.isEmpty()) newPhone = null;

        ErrorDialog dialog = new ErrorDialog();
        DatabaseFunction db = new DatabaseFunction();

        if (!db.validation(appUsername, password)) {
            dialog.updateFailed();
            return;
        }

        db.updateUserDetails(appUsername, newUsername, newAddress, newEmail, newPhone);
        new MessageDialog().updateSuccess();

        String finalUsername = (newUsername != null) ? newUsername : appUsername;

        this.dispose();
        new Home(finalUsername);
    }
}