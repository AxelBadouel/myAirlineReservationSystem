package flights_dao;

class FlightQueries {
    public static final String INSERT_FLIGHT = "INSERT INTO flights_data (datetime_of_flight, flight_status, duration_of_flight, city_departure, city_destination, ticket_cost) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String GET_WITH_ID = "SELECT * FROM flights_data WHERE flight_id=?";
    public static final String GET_ALL = "SELECT * FROM flights_data";
    public static final String UPDATE = "UPDATE flights_data SET datetime_of_flight=?, flight_status=?, duration_of_flight=?, city_departure=?, city_destination=?, ticket_cost=? WHERE flight_id=?";
    public static final String DELETE_WITH_ID = "DELETE FROM flights_data WHERE flight_id=?";
}
