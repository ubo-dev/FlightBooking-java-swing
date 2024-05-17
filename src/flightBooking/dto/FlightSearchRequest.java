package flightBooking.dto;

import java.time.LocalDate;

public record FlightSearchRequest(int departureCityId, int destinationCityId, LocalDate flightDate, int numberOfTickets) {

}
