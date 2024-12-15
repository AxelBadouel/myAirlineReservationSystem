package customer_dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public record CustomerDao(Integer customer_id,
                          String customer_name,
                          String city_departure,
                          String city_destination,
                          LocalDateTime datetime_of_flight,
                          Integer booking_status,
                          Double ticket_cost,
                          String email) implements Serializable {

    public CustomerDao {
        Objects.requireNonNull(customer_id);
        Objects.requireNonNull(customer_name);

        if(city_departure.equals(city_destination)) {
            throw new RuntimeException("City of departure and arrival cannot be the same");
        }
    }
}
