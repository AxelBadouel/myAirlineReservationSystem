import connection_handling.ConnectionHandler;
import customer_dao.CustomerDao;
import customer_dao.CustomerDataManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = mainConnectionOpener();
        CustomerDataManagement customerDataManagement = new CustomerDataManagement(connection);

        /*DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        CustomerDao customerDao = new CustomerDao(55,
                "Malik Bayes",
                 "San Antonio",
                "Moscow",
                             LocalDateTime.parse("2024-12-29 15:51:17", dateTimeFormatter),
                1,
                   536.0,
                       "malikbayes@example.com",
                      4);
        System.out.println(customerDao.toString());*/

        try {
            int deleted = customerDataManagement.deleteCustomer(54);
            if (deleted > 0) {
                System.out.println("Customer deleted successfully");
            } else {
                System.out.println("Issue with the customers retrieval");
            }
        } catch (SQLException e) {
            System.out.println("Error adding the user");
        }

        mainConnectionCloser(connection);
    }

    public static Connection mainConnectionOpener() {
        Connection connection = null;

        try {
            connection = ConnectionHandler.ConnectionOpen();

        } catch (SQLException ex){
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }

        return connection;
    }

    public static void mainConnectionCloser(Connection connection) {
        try {
            ConnectionHandler.ConnectionClose(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}