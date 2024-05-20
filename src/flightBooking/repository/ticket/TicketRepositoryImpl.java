package flightBooking.repository.ticket;

import flightBooking.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {

    private final Connection connection;

    public TicketRepositoryImpl(Connection connection) {
        this.connection = connection;
        createTicketTable();
    }

    private void createTicketTable() {
        String query = "CREATE TABLE IF NOT EXISTS ticket ("
                + "ticket_id INT PRIMARY KEY AUTO_INCREMENT,"
                + "passenger_id INT NOT NULL,"
                + "flight_id INT NOT NULL,"
                + "time TIMESTAMP NOT NULL,"
                + "FOREIGN KEY (passenger_id) REFERENCES passenger_t(id),"
                + "FOREIGN KEY (flight_id) REFERENCES flight(flight_id)"
                + ");";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
    }

    @Override
    public void addTicket(long passengerId, long flightId) {
        String query = "INSERT INTO ticket (passenger_id, flight_id, time) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, passengerId);
            preparedStatement.setLong(2, flightId);
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
    }

    @Override
    public void removeTicket(long ticketId) {
        String query = "DELETE FROM ticket WHERE ticket_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, ticketId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
    }

    @Override
    public Ticket getTicket(long ticketId) {
        String query = "SELECT * FROM ticket WHERE ticket_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, ticketId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Ticket(
                            resultSet.getLong("ticket_id"),
                            resultSet.getLong("passenger_id"),
                            resultSet.getLong("flight_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        String query = "SELECT * FROM ticket";
        List<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Ticket ticket = new Ticket(
                        resultSet.getLong("ticket_id"),
                        resultSet.getLong("passenger_id"),
                        resultSet.getLong("flight_id")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByPassengerId(long passengerId) {
        String query = "SELECT * FROM ticket WHERE passenger_id = ?";
        List<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, passengerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Ticket ticket = new Ticket(
                            resultSet.getLong("ticket_id"),
                            resultSet.getLong("passenger_id"),
                            resultSet.getLong("flight_id")
                    );
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
        return tickets;
    }

}
