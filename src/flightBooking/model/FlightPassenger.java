package flightBooking.model;

import java.util.ArrayList;
import java.util.List;

public class FlightPassenger {
	
	private long id;
	private long passengerId;
	private long flightId;
	
	public FlightPassenger(long id, long passengerId, long flightId) {
		super();
		this.id = id;
		this.passengerId = passengerId;
		this.flightId = flightId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(long passengerId) {
		this.passengerId = passengerId;
	}
	public long getFlightId() {
		return flightId;
	}
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	
	
}
