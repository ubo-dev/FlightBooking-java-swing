package flightBooking.repository.flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import flightBooking.dto.BookFlightRequest;
import flightBooking.dto.FlightSearchRequest;
import flightBooking.model.Flight;
import flightBooking.repository.DatabaseConnection;

public class FlightRepositoryImpl implements FlightRepository {

	DatabaseConnection connection = DatabaseConnection.getInstance();
	Connection dbConnection = connection.getConnection();

	public FlightRepositoryImpl() {
		createCityTable();
		createFlightTable();
	}

	private void createCityTable() {
		String query = "create table if not exists city ("
				+ "id INT PRIMARY KEY AUTO_INCREMENT,"
				+ "name VARCHAR(255) NOT NULL"
				+ ");";

		try(PreparedStatement pStatement = dbConnection.prepareStatement(query)) {
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void createFlightTable() {
		String query = "create table if not exists flight ("
				+ "id INT PRIMARY KEY AUTO_INCREMENT,"
				+ "city_departure_id int NOT NULL,"
				+ "city_destination_id int NOT NULL,"
				+ "capacity INT NOT NULL,"
				+ "bookedSeats INT NOT NULL,"
				+ "departure_time DATE NOT NULL,"
				+ "estimated_arrival_time DATE NOT NULL,"
				+ "foreign key (city_departure_id) references city(id),"
				+ "foreign key (city_destination_id) references city(id)" +
				");";

		try(PreparedStatement pStatement = dbConnection.prepareStatement(query)) {
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Flight> searchFlights(FlightSearchRequest request) {
		String query = "select * from flight where city_departure_id == ? and city_destination_id == ? and departure_time == ?;";
		ResultSet resultSet = null;
		try(PreparedStatement pStatement = dbConnection.prepareStatement(query)) {
			pStatement.setInt(1, request.departureCityId());
			pStatement.setInt(2, request.destinationCityId());
			pStatement.setDate(3, Date.valueOf(request.flightDate()));
			resultSet = pStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Flight> flights = new ArrayList<>();
		try {
			while(true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                Flight flight = new Flight(
						resultSet.getInt("id"),
						resultSet.getInt("city_departure_id"),
						resultSet.getInt("city_destination_id"),
						resultSet.getInt("capacity"),
						resultSet.getInt("bookedSeats"),
						resultSet.getDate("departure_time").toLocalDate(),
						resultSet.getDate("estimated_arrival_time").toLocalDate()
				);
				flights.add(flight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flights;
	}

	@Override
	public void bookFlight(BookFlightRequest request) {
		String query = "update flight set bookedSeats = bookedSeats + 1 where id == ?;";
		try(PreparedStatement pStatement = dbConnection.prepareStatement(query)) {
			pStatement.setLong(1, request.flightId());
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// TODO: ticket repository
	}

}
