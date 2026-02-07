import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Login extends JFrame {

    private JTextField usernameEntry;
    private JPasswordField passwordEntry;
    private JButton loginBtn;
    private JButton signupBtn;

    private static final Color ACCENT = new Color(172, 110, 142);
    private static final Color PINK = Color.decode("#ea077c");
    private static final Color WHITE = Color.WHITE;

    private static final Font TITLE_FONT = new Font("Ubuntu", Font.BOLD, 11);
    private static final Font BTN_FONT = new Font("Roboto", Font.BOLD, 12);
    private static final Font LINK_FONT = new Font("Roboto", Font.BOLD, 11);

    public Login() {
        initFrame();
        initComponents();
        layoutInit();
    }

    private void initFrame() {
        setTitle("Login - UMS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 420);               // closer to EditUser sizing feel
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

        loginBtn = new JButton("LOGIN");
        loginBtn.setForeground(WHITE);
        loginBtn.setBackground(PINK);
        loginBtn.setFont(BTN_FONT);
        loginBtn.setFocusPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        signupBtn = new JButton("Don't have an account? Sign up");
        signupBtn.setForeground(PINK);
        signupBtn.setBackground(WHITE);
        signupBtn.setFont(LINK_FONT);
        signupBtn.setBorder(BorderFactory.createLineBorder(WHITE));
        signupBtn.setFocusPainted(false);
        signupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupBtn.setContentAreaFilled(false); // makes it look more like a link
        signupBtn.setOpaque(false);

        // Actions
        loginBtn.addActionListener(_ -> onLoginClicked());
        signupBtn.addActionListener(_ -> {
            this.dispose();
            new Signup();
        });

        usernameEntry.addKeyListener(myEnterListener);
        passwordEntry.addKeyListener(myEnterListener);
    }

    private void layoutInit() {
        // Main form panel (same style as EditUser)
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel header = new JLabel("LOGIN");
        header.setFont(new Font("Roboto", Font.BOLD, 16));
        header.setForeground(PINK);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(header, gbc);

        gbc.gridy++;
        panel.add(usernameEntry, gbc);

        gbc.gridy++;
        panel.add(passwordEntry, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(loginBtn, gbc);

        gbc.gridy++;
        panel.add(signupBtn, gbc);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(WHITE);
        root.add(panel, BorderLayout.CENTER);

        setContentPane(root);
        setVisible(true);
    }

    private void onLoginClicked() {
        String username = usernameEntry.getText();
        char[] password = passwordEntry.getPassword();
        ErrorDialog d = new ErrorDialog();

        if (username.isEmpty()) {
            d.errorEmptyUsername();
        } else if (Arrays.equals(password, "".toCharArray())) {
            d.errorEmptyPassword();
        } else if (password.length < 8) {
            d.errorPasswordLength();
            passwordEntry.setText("");
        } else {
            DatabaseFunction database = new DatabaseFunction();
            if (database.validation(username, password)) {
                Arrays.fill(password, '\0');
                this.dispose();
                new Home(username);
            } else {
                d.loginFailure();
            }
            usernameEntry.setText("");
            passwordEntry.setText("");
        }
    }

    private final KeyAdapter myEnterListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                onLoginClicked();
            }
        }
    };
}