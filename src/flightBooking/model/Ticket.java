package flightBooking.model;

import java.time.LocalDateTime;

public class Ticket {
	
	private long ticketId;
	private long passengerId;
	private long flightId;
	private LocalDateTime givenDate;
	
	public Ticket(long ticketId, long passengerId, long flightId) {
		this.flightId = flightId;
		this.ticketId = ticketId;
		this.passengerId = passengerId;
		givenDate = LocalDateTime.now();
	}
	
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	public long getPassengerIdd() {
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
	public LocalDateTime getGivenDate() {
		return givenDate;
	}
	public void setGivenDate(LocalDateTime givenDate) {
		this.givenDate = givenDate;
	}
	
	
}
