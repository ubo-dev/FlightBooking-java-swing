package flightBooking.repository.flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import flightBooking.dto.FlightSearchRequest;
import flightBooking.model.Flight;

public class FlightRepositoryImpl implements FlightRepository {

    private final Connection dbConnection;

    public FlightRepositoryImpl(Connection connection) {
        this.dbConnection = connection;
        createFlightTable();
    }

    private void createFlightTable() {
        String query = "CREATE TABLE IF NOT EXISTS flight ("
                + "flight_id INT PRIMARY KEY AUTO_INCREMENT,"
                + "city_departure_id INT NOT NULL,"
                + "city_destination_id INT NOT NULL,"
                + "capacity INT NOT NULL,"
                + "booked_seats INT NOT NULL,"
                + "departure_time DATE NOT NULL,"
                + "estimated_arrival_time DATE NOT NULL,"
                + "FOREIGN KEY (city_departure_id) REFERENCES city(id),"
                + "FOREIGN KEY (city_destination_id) REFERENCES city(id)"
                + ");";

        try (PreparedStatement pStatement = dbConnection.prepareStatement(query)) {
            pStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }

    public List<Flight> searchFlights(FlightSearchRequest request) {
        String query = "SELECT * FROM flight WHERE city_departure_id = ? AND city_destination_id = ? AND departure_time = ?;";
        List<Flight> flights = new ArrayList<>();

        try (PreparedStatement pStatement = dbConnection.prepareStatement(query)) {
            pStatement.setInt(1, request.departureCityId());
            pStatement.setInt(2, request.destinationCityId());
            pStatement.setDate(3, Date.valueOf(request.flightDate()));

            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    Flight flight = new Flight(
                            resultSet.getInt("flight_id"),
                            resultSet.getInt("city_departure_id"),
                            resultSet.getInt("city_destination_id"),
                            resultSet.getInt("capacity"),
                            resultSet.getInt("booked_seats"),
                            resultSet.getDate("departure_time").toLocalDate(), // Changed to getDate
                            resultSet.getDate("estimated_arrival_time").toLocalDate() // Changed to getDate
                    );
                    flights.add(flight);
                }
            }
        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
        }

        return flights;
    }

}
