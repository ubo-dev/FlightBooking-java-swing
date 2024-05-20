package flightBooking.repository.ticket;

import flightBooking.model.Ticket;

import java.util.List;

public interface TicketRepository {

    void addTicket(long passengerId, long flightId);
    void removeTicket(long ticketId);
    Ticket getTicket(long ticketId);
    List<Ticket> getAllTickets();
    List<Ticket> getTicketsByPassengerId(long passengerId);
}
