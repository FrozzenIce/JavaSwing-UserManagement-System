import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class Database {
    Connection conn = DBConnection.getInstance().getConnection();

    public void sendSignupData(String username, char[] password, String address, String email, String phone) {
        String insertQuery = """
                INSERT INTO userdetails (Username, Password, Address, Email, Phone)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            // Bind variables to placeholders (index starts at 1)
            pstmt.setString(1, username);
            // Convert char[] to String for the DB driver, then clear the array
            pstmt.setString(2, new String(password));
            pstmt.setString(3, address);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);

            pstmt.executeUpdate();
            MessageDialog messageDialog = new MessageDialog();
            messageDialog.signupSuccess();
            System.out.println("Signup data sent successfully.");

        } catch (SQLException e) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.databaseException(e.getMessage());
            System.err.println("Database Error: " + e.getMessage());
        }
    }

    public boolean checkUsernameAvailability(String username) {
        String query = """
                SELECT username FROM userdetails WHERE username = ?;
                """;
        String storedUsername = "";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                storedUsername = resultSet.getString("Username");
            }
        } catch (SQLException e) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.databaseException(e.getMessage());
            System.err.println("Database Error: " + e.getMessage());
        }
        return Objects.equals(storedUsername, "");
    }

    public boolean validation(String username, char[] password) {
        String query = """
                SELECT Username, Password FROM userdetails WHERE Username = ?;
                """;
        String storedUsername = "";
        char[] storedPassword = "".toCharArray();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                storedUsername = resultSet.getString("Username");
                storedPassword = resultSet.getString("Password").toCharArray();
            }
        } catch (SQLException e) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.databaseException(e.getMessage());
            System.out.println(e.getMessage());
        }
        boolean valid = Arrays.equals(password, storedPassword) && Objects.equals(username, storedUsername);
        System.out.println("Validation: " + valid);
        return (valid);
    }

    public User fetchUserDetails(String username) {
        String query = """
                SELECT Username, Address, Email, Phone FROM userdetails WHERE Username = ?""";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("Username"),
                        rs.getString("Address"),
                        rs.getString("Email"),
                        rs.getString("Phone")
                );
            }
        } catch (SQLException e) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.databaseException(e.getMessage());
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    public void updateUserDetails(String appUsername, String username, String address, String email, String phone) {
        String query = """
                    UPDATE userdetails SET
                    Username = COALESCE(?, Username),
                    Address = COALESCE(?, Address),
                    Email = COALESCE(?, Email),
                    Phone = COALESCE(?, Phone)
                    WHERE Username = ?;
                """;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, address);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, appUsername);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.databaseException(e.getMessage());
            System.err.println("Database Error: " + e.getMessage());
        }
    }
}
