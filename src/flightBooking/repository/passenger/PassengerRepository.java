package flightBooking.repository.passenger;

import flightBooking.dto.RegisterRequest;
import flightBooking.model.Passenger;
import flightBooking.dto.LoginRequest;

public interface PassengerRepository {

	int registerPassenger(RegisterRequest request);
	boolean loginPassenger(LoginRequest request);
	Passenger getPassengerInformation(String email);
}
