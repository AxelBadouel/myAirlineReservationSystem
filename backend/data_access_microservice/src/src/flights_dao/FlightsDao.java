package flights_dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public record FlightsDao(Integer flight_id,
                         LocalDateTime datetime_of_flight,
                         Boolean flight_status,
                         Integer duration_of_flight,
                         String city_departure,
                         String city_destination,
                         Double ticket_cost) implements Serializable {
    public FlightsDao {
        Objects.requireNonNull(flight_id);

        if(city_departure.equals(city_destination)) {
            throw new RuntimeException("City of departure and arrival cannot be the same");
        }
    }
}
