import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DeleteUser extends JFrame {
    private final String appUsername;
    private final Home parentHome;

    private JLabel header;
    private JLabel warningText;
    private JButton deleteBtn;
    private JButton cancelBtn;

    private static final Color ACCENT = new Color(172, 110, 142);
    private static final Color PINK = Color.decode("#ea077c");
    private static final Color WHITE = Color.WHITE;

    private static final Font TITLE_FONT = new Font("Roboto", Font.BOLD, 16);
    private static final Font TEXT_FONT = new Font("Ubuntu", Font.PLAIN, 13);
    private static final Font BTN_FONT = new Font("Roboto", Font.BOLD, 11);

    public DeleteUser(Home parentHome, String username) {
        this.parentHome = parentHome;
        this.appUsername = username;

        initFrame();
        initComponents();
        layoutInit();
    }

    private void initFrame() {
        setTitle("Delete Profile - UMS");
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        header = new JLabel("DELETE PROFILE");
        header.setFont(TITLE_FONT);
        header.setForeground(PINK);

        warningText = new JLabel("<html>This will permanently delete your profile:<br><b>"
                + appUsername + "</b><br><br>Are you sure?</html>");
        warningText.setFont(TEXT_FONT);
        warningText.setForeground(Color.DARK_GRAY);

        deleteBtn = new JButton("DELETE");
        deleteBtn.setForeground(WHITE);
        deleteBtn.setBackground(PINK);
        deleteBtn.setFont(BTN_FONT);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelBtn = new JButton("CANCEL");
        cancelBtn.setForeground(Color.BLACK);
        cancelBtn.setBackground(new Color(240, 240, 240));
        cancelBtn.setFont(BTN_FONT);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelBtn.addActionListener(_ -> dispose());

        deleteBtn.addActionListener(_ -> deleteNow());
    }

    private void layoutInit() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(WHITE);

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT, 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );
        card.setBorder(border);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 10, 5);

        card.add(header, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 5, 15, 5);
        card.add(warningText, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        card.add(deleteBtn, gbc);

        gbc.gridx = 1;
        card.add(cancelBtn, gbc);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(250, 250, 250));
        root.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        root.add(card);

        setContentPane(root);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void deleteNow() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete profile for \"" + appUsername + "\"?\nThis cannot be undone.",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        DatabaseFunction db = new DatabaseFunction();
        boolean deleted = db.deleteUser(appUsername);

        if (deleted) {
            new MessageDialog().deleteSuccess();

            if (parentHome != null) {
                parentHome.dispose();
            }

            dispose();          // close DeleteUser
            new Login();        // go to login
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Delete failed. Profile may not exist.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}