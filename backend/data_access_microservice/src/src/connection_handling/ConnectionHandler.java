package connection_handling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionHandler {

    public static Connection ConnectionOpen() throws SQLException {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/airlinedata";
        String username = "user";
        String password = "M!SqLW0rd";

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
