package customer_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDataManagement {
    private final Connection connection;

    public CustomerDataManagement(Connection connection) {
        this.connection = connection;
    }

    public int insertNewCustomer(CustomerDao customerDao) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CustomerQueries.INSERT_CUSTOMER);

        int rowsInserted = preparedStatement.executeUpdate();
        if(rowsInserted > 0) {
            System.out.println("Customer inserted successfully");
        }

        return rowsInserted;
    }
}
