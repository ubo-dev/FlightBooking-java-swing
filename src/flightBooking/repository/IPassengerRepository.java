package flightBooking.repository;

import flightBooking.dto.RegisterRequest;
import flightBooking.dto.LoginRequest;

public interface IPassengerRepository {

	int RegisterPassenger(RegisterRequest request);
	boolean LoginPassenger(LoginRequest request); 
}
