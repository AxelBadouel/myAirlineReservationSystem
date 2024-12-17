package flights_dao;

import java.sql.*;
import java.util.List;

public class FlightsDataManagement {
    private final Connection connection;

    public FlightsDataManagement(Connection connection) {
        this.connection = connection;
    }

    public int insertNewCustomer(FlightsDao flightsDao) throws SQLException {
        if(flightsDao == null) {
            throw new RuntimeException("The CustomerDao object provided is null");
        }
        PreparedStatement preparedStatement = connection.prepareStatement(FlightQueries.INSERT_FLIGHT);

        Timestamp timestamp = Timestamp.valueOf(flightsDao.datetime_of_flight());
        preparedStatement.setTimestamp(1, timestamp);
        preparedStatement.setBoolean(2, flightsDao.flight_status());
        preparedStatement.setInt(3, flightsDao.duration_of_flight());
        preparedStatement.setString(4, flightsDao.city_departure());
        preparedStatement.setString(5, flightsDao.city_destination());
        preparedStatement.setDouble(6, flightsDao.ticket_cost());

        int rowsInserted = preparedStatement.executeUpdate();
        if(rowsInserted > 0) {
            System.out.println("Customer inserted successfully");
        }

        return rowsInserted;
    }

    public List<FlightsDao> getAllFlights() throws SQLException {
        List<FlightsDao> flights = null;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(FlightQueries.GET_ALL);

        if(resultSet != null) {
            System.out.println("Data retrieved successfully");
        } else {
            throw new RuntimeException("Problem(s) retrieving the list of data");
        }

        FlightsDao flightsDao;
        while (resultSet.next()) {
            flightsDao = new FlightsDao(resultSet.getInt("flight_id"),
                    resultSet.getTimestamp("datetime_of_flight").toLocalDateTime(),
                    resultSet.getBoolean("flight_status"),
                    resultSet.getInt("duration_of_flight"),
                    resultSet.getString("city_departure"),
                    resultSet.getString("city_destination"),
                    resultSet.getDouble("ticket_cost"));

            flights.add(flightsDao);
        }

        return flights;
    }

    public FlightsDao getFlightById(int flight_id) throws SQLException {
        FlightsDao flightsDao;

        PreparedStatement preparedStatement = connection.prepareStatement(FlightQueries.GET_WITH_ID);
        preparedStatement.setInt(1, flight_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            flightsDao = new FlightsDao(resultSet.getInt("flight_id"),
                    resultSet.getTimestamp("datetime_of_flight").toLocalDateTime(),
                    resultSet.getBoolean("flight_status"),
                    resultSet.getInt("duration_of_flight"),
                    resultSet.getString("city_departure"),
                    resultSet.getString("city_destination"),
                    resultSet.getDouble("ticket_cost"));

            System.out.println("Data retrieved successfully");
        } else {
            throw new RuntimeException("Problem(s) retrieving the list of data");
        }

        return flightsDao;
    }

    public int updateFlight(FlightsDao flightsDao) throws SQLException {
        if(flightsDao == null) {
            throw new RuntimeException("The FlightsDao object provided is null");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(FlightQueries.UPDATE);

        Timestamp timestamp = Timestamp.valueOf(flightsDao.datetime_of_flight());
        preparedStatement.setTimestamp(1, timestamp);
        preparedStatement.setBoolean(2, flightsDao.flight_status());
        preparedStatement.setInt(3, flightsDao.duration_of_flight());
        preparedStatement.setString(4, flightsDao.city_departure());
        preparedStatement.setString(5, flightsDao.city_destination());
        preparedStatement.setDouble(6, flightsDao.ticket_cost());

        int rowsInserted = preparedStatement.executeUpdate();
        if(rowsInserted > 0) {
            System.out.println("Flight data updated successfully");
        }

        return rowsInserted;
    }

    public int deleteFlight(int flight_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FlightQueries.DELETE_WITH_ID);
        preparedStatement.setInt(1, flight_id);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Flight deleted successfully!");
        }

        return rowsDeleted;
    }
}
