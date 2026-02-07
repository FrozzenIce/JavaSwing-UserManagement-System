import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Signup extends JFrame {
    private JTextField usernameEntry;
    private JPasswordField passwordEntry;
    private JPasswordField confirmpasswordEntry;
    private JTextField addressEntry;
    private JTextField emailEntry;
    private JTextField phoneEntry;
    private JButton signupBtn;
    private JButton backBtn;

    private static final Color ACCENT = new Color(172, 110, 142);
    private static final Color PINK = Color.decode("#ea077c");
    private static final Color WHITE = Color.WHITE;

    private static final Font TITLE_FONT = new Font("Ubuntu", Font.BOLD, 11);
    private static final Font BTN_FONT = new Font("Roboto", Font.BOLD, 11);

    public Signup() {
        initFrame();
        initComponents();
        layoutInit();
    }

    private void initFrame() {
        setTitle("Signup - UMS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 520);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        Border line = BorderFactory.createLineBorder(ACCENT, 1, true);
        Border padding = BorderFactory.createEmptyBorder(5, 8, 5, 8);

        usernameEntry = new JTextField(20);
        usernameEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(line, " Username ", 0, 0, TITLE_FONT, ACCENT),
                padding
        ));

        passwordEntry = new JPasswordField(20);
        passwordEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(line, " Password ", 0, 0, TITLE_FONT, ACCENT),
                padding
        ));

        confirmpasswordEntry = new JPasswordField(20);
        confirmpasswordEntry.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(line, " Confirm Password ", 0, 0, TITLE_FONT, ACCENT),
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

        signupBtn = new JButton("SIGN UP");
        signupBtn.setForeground(WHITE);
        signupBtn.setBackground(PINK);
        signupBtn.setFont(BTN_FONT);
        signupBtn.setFocusPainted(false);
        signupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Actions
        backBtn.addActionListener(_ -> {
            this.dispose();
            new Login();
        });

        signupBtn.addActionListener(_ -> signupEvent());

        // Enter key triggers
        usernameEntry.addKeyListener(myEnterListener);
        passwordEntry.addKeyListener(myEnterListener);
        confirmpasswordEntry.addKeyListener(myEnterListener);
        addressEntry.addKeyListener(myEnterListener);
        emailEntry.addKeyListener(myEnterListener);
        phoneEntry.addKeyListener(myEnterListener);
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

        JLabel header = new JLabel("SIGN UP");
        header.setFont(new Font("Roboto", Font.BOLD, 16));
        header.setForeground(PINK);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(header, gbc);

        gbc.gridy++;
        panel.add(usernameEntry, gbc);

        gbc.gridy++;
        panel.add(passwordEntry, gbc);

        gbc.gridy++;
        panel.add(confirmpasswordEntry, gbc);

        gbc.gridy++;
        panel.add(addressEntry, gbc);

        gbc.gridy++;
        panel.add(emailEntry, gbc);

        gbc.gridy++;
        panel.add(phoneEntry, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(signupBtn, gbc);

        gbc.gridy++;
        panel.add(backBtn, gbc);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(WHITE);
        root.add(panel, BorderLayout.CENTER);

        setContentPane(root);
        setVisible(true);
    }

    private void signupEvent() {
        String username = usernameEntry.getText();
        char[] password = passwordEntry.getPassword();
        String address = addressEntry.getText();
        String email = emailEntry.getText();
        String phone = phoneEntry.getText();

        ErrorDialog d = new ErrorDialog();
        DatabaseFunction database = new DatabaseFunction();

        if (!database.checkUsernameAvailability(username)) {
            d.errorUsernameAvailability();
        } else if (password.length < 8) {
            d.errorPassSignup();
        } else if (phone.trim().length() != 10) {
            d.phoneLenError();
        } else {
            database.sendSignupData(username, password, address, email, phone);
            this.dispose();
            new Login();
        }
    }

    private final KeyAdapter myEnterListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                signupEvent();
            }
        }
    };
}