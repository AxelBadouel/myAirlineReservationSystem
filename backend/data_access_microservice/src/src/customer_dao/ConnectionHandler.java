import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
    protected Connection openConnection() {
        // creates three different Connection objects
        Connection connection = null;

        // accessing sensitive data in application.properties
        Properties properties = new Properties();

        try(InputStream input = CustomerDao.class.getClassLoader().getResourceAsStream("application.properties"))
         {
             if(input != null) {
                 properties.load(input);
             }
            /*try (FileInputStream file = new FileInputStream("application.properties")) {
                if (file != null) {
                    properties.load(file);
                }
            } catch (IOException exception) {
            throw new RuntimeException();
            }*/

            String dbUrl = properties.getProperty("database.url");
            String user = properties.getProperty("database.user");
            String password = properties.getProperty("database.password");

            connection = DriverManager.getConnection(dbUrl, user, password);
            if (connection != null) {
                System.out.println("Connected to the database airlinedata");
            }

        } catch (SQLException | IOException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }

        return connection;
    }

    protected void closeConnection(Connection connection) throws SQLException {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
