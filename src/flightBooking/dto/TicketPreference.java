package flightBooking.dto;

import flightBooking.model.PassengerType;
import flightBooking.model.TicketType;

public record TicketPreference(TicketType type, int numberOfTickets) {

}
