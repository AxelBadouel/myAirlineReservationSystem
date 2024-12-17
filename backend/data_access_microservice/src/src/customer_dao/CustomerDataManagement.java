package customer_dao;

import java.sql.*;
import java.util.List;

public class CustomerDataManagement {
    private final Connection connection;

    public CustomerDataManagement(Connection connection) {
        this.connection = connection;
    }

    public int insertNewCustomer(CustomerDao customerDao) throws SQLException {
        if(customerDao == null) {
            throw new RuntimeException("The CustomerDao object provided is null");
        }
        PreparedStatement preparedStatement = connection.prepareStatement(CustomerQueries.INSERT_CUSTOMER);

        preparedStatement.setString(1, customerDao.customer_name());
        preparedStatement.setString(2, customerDao.city_departure());
        preparedStatement.setString(3, customerDao.city_destination());

        Timestamp timestamp = Timestamp.valueOf(customerDao.datetime_of_flight());
        preparedStatement.setTimestamp(4, timestamp);

        preparedStatement.setInt(5, customerDao.booking_status());
        preparedStatement.setDouble(6, customerDao.ticket_cost());
        preparedStatement.setString(7, customerDao.email());

        int rowsInserted = preparedStatement.executeUpdate();
        if(rowsInserted > 0) {
            System.out.println("Customer inserted successfully");
        }

        return rowsInserted;
    }

    public List<CustomerDao> getAllCustomer() throws SQLException {
        List<CustomerDao> customers = null;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(CustomerQueries.GET_ALL);

        if(resultSet != null) {
            System.out.println("Data retrieved successfully");
        } else {
            throw new RuntimeException("Problem(s) retrieving the list of data");
        }

        CustomerDao customerDao;
        while (resultSet.next()) {
            customerDao = new CustomerDao(resultSet.getInt("customer_id"),
                    resultSet.getString("customer_name"),
                    resultSet.getString("city_departure"),
                    resultSet.getString("city_destination"),
                    resultSet.getTimestamp("datetime_of_flight").toLocalDateTime(),
                    resultSet.getInt("booking_status"),
                    resultSet.getDouble("ticket_cost"),
                    resultSet.getString("email"));

            customers.add(customerDao);
        }

        return customers;
    }

    public CustomerDao getCustomerById(int customer_id) throws SQLException {
        CustomerDao customerDao;

        PreparedStatement preparedStatement = connection.prepareStatement(CustomerQueries.GET_WITH_ID);
        preparedStatement.setInt(1, customer_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            customerDao = new CustomerDao(resultSet.getInt("customer_id"),
                    resultSet.getString("customer_name"),
                    resultSet.getString("city_departure"),
                    resultSet.getString("city_destination"),
                    resultSet.getTimestamp("datetime_of_flight").toLocalDateTime(),
                    resultSet.getInt("booking_status"),
                    resultSet.getDouble("ticket_cost"),
                    resultSet.getString("email"));

            System.out.println("Data retrieved successfully");
        } else {
            throw new RuntimeException("Problem(s) retrieving the list of data");
        }

        return customerDao;
    }

    public int updateCustomer(CustomerDao customerDao) throws SQLException {
        if(customerDao == null) {
            throw new RuntimeException("The CustomerDao object provided is null");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(CustomerQueries.UPDATE);

        preparedStatement.setString(1, customerDao.customer_name());
        preparedStatement.setString(2, customerDao.city_departure());
        preparedStatement.setString(3, customerDao.city_destination());

        Timestamp timestamp = Timestamp.valueOf(customerDao.datetime_of_flight());
        preparedStatement.setTimestamp(4, timestamp);

        preparedStatement.setInt(5, customerDao.booking_status());
        preparedStatement.setDouble(6, customerDao.ticket_cost());
        preparedStatement.setString(7, customerDao.email());
        preparedStatement.setInt(8, customerDao.customer_id());

        int rowsInserted = preparedStatement.executeUpdate();
        if(rowsInserted > 0) {
            System.out.println("Customer updated successfully");
        }

        return rowsInserted;
    }

    public int deleteCustomer(int customer_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CustomerQueries.DELETE_WITH_ID);
        preparedStatement.setInt(1, customer_id);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Customer deleted successfully!");
        }

        return rowsDeleted;
    }
}
