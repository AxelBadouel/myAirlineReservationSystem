import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CustomerDao {
    public static void main(String[] args) {

        // creates three different Connection objects
        Connection conn1 = null;
        Connection conn2 = null;
        Connection conn3 = null;

        try {
            // connect way #1
            String url1 = "jdbc:mysql://localhost:3306/test1";
            String user = "user";
            String password = "M!SqLW0rd";

            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test1");
            }

            // connect way #2
            String url2 = "jdbc:mysql://localhost:3306/test2?user=" + user + "&password=" + password;
            conn2 = DriverManager.getConnection(url2);
            if (conn2 != null) {
                System.out.println("Connected to the database test2");
            }

            // connect way #3
            String url3 = "jdbc:mysql://localhost:3306/test3";
            Properties info = new Properties();
            info.put("user", user);
            info.put("password", password);
            conn3 = DriverManager.getConnection(url3, info);
            if (conn3 != null) {
                System.out.println("Connected to the database test3");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn2 != null) {
                try {
                    conn2.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn3 != null) {
                try {
                    conn3.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}