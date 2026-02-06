import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.Arrays;

public class ButtonFunctions {
    public void loginEvent(JTextField usernameEntry, JPasswordField passwordEntry, JFrame frame){
        String username = usernameEntry.getText();
        char[] password = passwordEntry.getPassword();
        ErrorDialog d = new ErrorDialog();
        if (username.isEmpty()) { // throws error if username is empty
            d.errorEmptyUsername();
        } else if (Arrays.equals(password, "".toCharArray())) { // throws error if password is empty
            d.errorEmptyPassword();
        } else if (password.length < 8) { // throws error if password length < 8
            d.errorPasswordLength();
            passwordEntry.setText("");
        } else {
            DatabaseFunction database = new DatabaseFunction();
            if (database.validation(username, password)) { // Validates user details
                Arrays.fill(password, '\0');
                Home home = new Home();
                frame.dispose();
                home.homeApp(username);
            } else { // throws login failure window
                d.loginFailure();
            }
            usernameEntry.setText("");
            passwordEntry.setText("");
        }
    }

    public void signupEvent(JTextField usernameEntry, JPasswordField passwordEntry, JPasswordField confirmPasswordEntry, JTextField addressEntry, JTextField emailEntry, JTextField phoneEntry, JFrame frame){
        String username = usernameEntry.getText();
        char[] password = passwordEntry.getPassword();
        char[] confPassword = confirmPasswordEntry.getPassword();
        String address = addressEntry.getText();
        String email = emailEntry.getText();
        String phone = phoneEntry.getText();
        ErrorDialog d = new ErrorDialog();
        DatabaseFunction database = new DatabaseFunction();
        if (username.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty() || Arrays.equals(password, "".toCharArray()) || Arrays.equals(confPassword, "".toCharArray())) { // throws error if any one field is empty
            d.signupEmpty();
        } else if (!database.checkUsernameAvailability(username)) { // Checks username availability, throws error if unavailable
            d.errorUsernameAvailability();
        } else if (password.length < 8) { // shows error if password length < 8
            d.errorPassSignup();
        } else if (!Arrays.equals(password, confPassword)) { // checks if entered password and confirmation password is correct
            d.signupPassError();
        } else if(phone.trim().length()!= 10){
            d.phoneLenError();
        }else { // Sends the entered details to the database
            database.sendSignupData(username, password, address, email, phone);
            Login login = new Login();
            frame.dispose();
            login.loginApp();
        }
    }

    public void updateEvent(String appUsername, JTextField usernameEntry, JPasswordField passwordEntry, JTextField addressEntry, JTextField emailEntry, JTextField phoneEntry, JFrame frame){
        String username = usernameEntry.getText();
        String address = addressEntry.getText();
        String email = emailEntry.getText();
        String phone = phoneEntry.getText();
        char[] password = passwordEntry.getPassword();
        if (username.trim().isEmpty()) {
            username = null;
        }
        if (address.trim().isEmpty()) {
            address = null;
        }
        if (email.trim().isEmpty()) {
            email = null;
        }
        if (phone.trim().isEmpty()) {
            phone = null;
        }
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
        frame.dispose();
        Login login = new Login();
        login.loginApp();
    }
}
