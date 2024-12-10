import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerDao {
    public static void main(String[] args) {

        // creates three different Connection objects
        Connection conn1 = null;

        try {
            // connect way #1
            String url1 = "jdbc:mysql://localhost:3306/airlinedata";
            String user = "user";
            String password = "M!SqLW0rd";

            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database airlinedata");
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                    System.out.println("Closing connection");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}