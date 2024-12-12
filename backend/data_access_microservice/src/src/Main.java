import connection_handling.ConnectionHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // creates three different Connection objects
        Connection connection = null;

        try {
            connection = ConnectionHandler.ConnectionOpen();

        } catch (SQLException ex){
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    ConnectionHandler.ConnectionClose(connection);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}