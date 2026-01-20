import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private Connection connection;
    private static DBConnection instance = null;
    private DBConnection() {
        Properties prop = new Properties();
        try(FileInputStream file = new FileInputStream("config.properties");){
            prop.load(file);
            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
            connection = DriverManager.getConnection(url, username, password);
        }catch(IOException e) {
            throw new RuntimeException("Failed to initialize DB pool", e);
        }catch (SQLException e){
            throw  new RuntimeException("Database error: " + e.getMessage());
        }
    }

    public static DBConnection getInstance() {
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
