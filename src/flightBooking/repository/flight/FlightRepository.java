package flightBooking.repository.flight;

import java.util.List;

import flightBooking.dto.BookFlightRequest;
import flightBooking.dto.FlightSearchRequest;
import flightBooking.model.Flight;

public interface FlightRepository {
	
	List<Flight> searchFlights(FlightSearchRequest request);

}
