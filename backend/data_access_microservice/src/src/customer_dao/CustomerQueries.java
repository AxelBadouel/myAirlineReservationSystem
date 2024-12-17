package customer_dao;

class CustomerQueries {
    public static final String INSERT_CUSTOMER = "INSERT INTO customer_data (customer_name, city_departure, city_destination, datetime_of_flight, booking_status, ticket_cost, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_WITH_ID = "SELECT * FROM customer_data WHERE customer_id=?";
    public static final String GET_ALL = "SELECT * FROM customer_data";
    public static final String UPDATE = "UPDATE customer_data SET customer_name=?, city_departure=?, city_destination=?, datetime_of_flight=?, booking_status=?, ticket_cost=?, email=? WHERE customer_id=?";
    public static final String DELETE_WITH_ID = "DELETE FROM customer_data WHERE customer_id=?";
}
