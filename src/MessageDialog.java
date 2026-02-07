import javax.swing.*;
import java.awt.*;

public class MessageDialog {

    private static final Color PINK = Color.decode("#ea077c");
    private static final Color WHITE = Color.WHITE;

    private static final Font TITLE_FONT = new Font("Roboto", Font.BOLD, 13);
    private static final Font MSG_FONT = new Font("Ubuntu", Font.PLAIN, 12);

    public void signupSuccess() {
        showMessage(
                "Signup Successful",
                "Your account has been created successfully.",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void updateSuccess() {
        showMessage(
                "Update Successful",
                "Your profile has been updated successfully.",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void deleteSuccess() {
        showMessage(
                "Profile Deleted",
                "Your profile was deleted successfully.",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // 🔹 Centralized themed dialog
    private void showMessage(String title, String message, int type) {
        UIManager.put("OptionPane.background", WHITE);
        UIManager.put("Panel.background", WHITE);
        UIManager.put("OptionPane.messageFont", MSG_FONT);
        UIManager.put("OptionPane.buttonFont", TITLE_FONT);

        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                type
        );
    }
}