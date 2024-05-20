package flightBooking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FlightSearchRequest(int departureCityId, int destinationCityId, LocalDate flightDate, int numberOfTickets) {

}
