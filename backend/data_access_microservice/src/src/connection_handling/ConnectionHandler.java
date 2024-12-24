package connection_handling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionHandler {

    static String propertyFilePath = "C:\\Users\\simob\\Desktop\\Git Awesome Projects\\myAirlineReservationSystem\\backend\\data_access_microservice\\src\\resources\\application.properties";

    public static Connection ConnectionOpen() throws SQLException {
        Properties properties = new Properties();
        Connection connection;
        try (InputStream input = new FileInputStream(propertyFilePath)) {
            System.out.println(input.toString());
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String url = properties.getProperty("database.url");
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");

        connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            System.out.println("Connected to the database airlinedata");
        }

        return connection;
    }

    /***
     * Complete Or stop any active transaction before calling this function
     *
     * @param connection
     * @throws SQLException
     */
    public static void ConnectionClose(Connection connection) throws SQLException {
        if(connection != null) {
            connection.close();
            System.out.println("Connection closed successfully");
        }
    }
}
