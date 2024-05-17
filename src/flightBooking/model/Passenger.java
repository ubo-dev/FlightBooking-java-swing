package flightBooking.model;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
	
	private long passengerId;
	private String firstName; 
	private String lastName; 
	private String tcNumber; 
	private String email; 
	private String phoneNumber; 
	private String password;
	private List<Ticket> tickets;
	
	public Passenger(long passengerId, String firstName, String lastName, String tcNumber, String email,
			String phoneNumber, String password, List<Ticket> tickets) {
		this.passengerId = passengerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tcNumber = tcNumber;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.tickets = tickets;
	}
	
	public long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(long passengerId) {
		this.passengerId = passengerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTcNumber() {
		return tcNumber;
	}

	public void setTcNumber(String tcNumber) {
		this.tcNumber = tcNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	
	
}
