package flightBooking.model;

import java.time.LocalDate;

public class Flight {

    private long flightId;
    private int cityDepartureId;
    private int cityDestinationId;
    private int capacity;
    private int bookedSeats;
    private LocalDate departureTime;
    private LocalDate estimatedArrivalTime;

    public Flight(long flightId, int cityDepartureId, int cityDestinationId, int capacity, int bookedSeats,
            LocalDate departureTime, LocalDate estimatedArrivalTime) {
        super();
        this.flightId = flightId;
        this.cityDepartureId = cityDepartureId;
        this.cityDestinationId = cityDestinationId;
        this.capacity = capacity;
        this.bookedSeats = bookedSeats;
        this.departureTime = departureTime;
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public int getCityDepartureId() {
        return cityDepartureId;
    }

    public void setCityDepartureId(int cityDepartureId) {
        this.cityDepartureId = cityDepartureId;
    }

    public int getCityDestinationId() {
        return cityDestinationId;
    }

    public void setCityDestinationId(int cityDestinationId) {
        this.cityDestinationId = cityDestinationId;
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

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(LocalDate estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public boolean bookTickets(int numberOfTickets) {
        if (bookedSeats + numberOfTickets <= capacity) {
            bookedSeats += numberOfTickets;
            return true;
        }
        return false;
    }

}
