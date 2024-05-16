package flightBooking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
	
	private long flighId;
	private String cityFrom;
	private String cityTo;
	private int capacity;
	private int bookedSeats;
	private LocalDateTime departureTime;
	private LocalDateTime estimatedArrivalTime;
	
	public Flight(long flighId, String cityFrom, String cityTo, int capacity, int bookedSeats,
			LocalDateTime departureTime, LocalDateTime estimatedArrivalTime) {
		super();
		this.flighId = flighId;
		this.cityFrom = cityFrom;
		this.cityTo = cityTo;
		this.capacity = capacity;
		this.bookedSeats = bookedSeats;
		this.departureTime = departureTime;
		this.estimatedArrivalTime = estimatedArrivalTime;
	}

	public long getFlighId() {
		return flighId;
	}

	public void setFlighId(long flighId) {
		this.flighId = flighId;
	}

	public String getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}

	public String getCityTo() {
		return cityTo;
	}

	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getEstimatedArrivalTime() {
		return estimatedArrivalTime;
	}

	public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) {
		this.estimatedArrivalTime = estimatedArrivalTime;
	}

}
