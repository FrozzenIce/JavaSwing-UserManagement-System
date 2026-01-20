import javax.swing.*;

public class ErrorDialog {
    public void errorEmptyUsername() {
        JOptionPane.showMessageDialog(null, "Username cannot be empty!", "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    public void errorEmptyPassword() {
        JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    public void errorPasswordLength() {
        JOptionPane.showMessageDialog(null, "Password cannot have character less than 8", "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    public void loginFailure() {
        JOptionPane.showMessageDialog(null, "Login Failed! Password or Username incorrect", "Login Failure", JOptionPane.ERROR_MESSAGE);
    }

    public void errorUsernameAvailability() {
        JOptionPane.showMessageDialog(null, "Username unavailable", "Username Unavailability", JOptionPane.ERROR_MESSAGE);
    }

    public void errorPassSignup() {
        JOptionPane.showMessageDialog(null, "Password cannot have character less than 8", "Signup Error", JOptionPane.ERROR_MESSAGE);
    }

    public void signupEmpty() {
        JOptionPane.showMessageDialog(null, "Details can't be empty", "Signup Error", JOptionPane.ERROR_MESSAGE);
    }

    public void signupPassError() {
        JOptionPane.showMessageDialog(null, "Passwords do not match", "Matching Error", JOptionPane.ERROR_MESSAGE);
    }

    public void updateFailed() {
        JOptionPane.showMessageDialog(null, "Incorrect password, update failed", "Update Failure", JOptionPane.ERROR_MESSAGE);
    }

    public void databaseException(String message) {
        JOptionPane.showMessageDialog(null, "Database error (SQL Exception): " + message, "Signup Failure", JOptionPane.ERROR_MESSAGE);
    }
}
