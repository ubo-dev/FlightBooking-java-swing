package flightBooking.repository.passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import flightBooking.dto.LoginRequest;
import flightBooking.dto.RegisterRequest;
import flightBooking.model.Passenger;
import flightBooking.model.Ticket;
import flightBooking.repository.DatabaseConnection;

public class PassengerRepositoryImpl implements PassengerRepository {

	DatabaseConnection connection = DatabaseConnection.getInstance();
	Connection dbConnection = connection.getConnection();

	public PassengerRepositoryImpl() {
		createPassengerTable();
	}

	private void createPassengerTable() {
		String qString = "CREATE TABLE if not exists passenger (" 
				+ "id INT PRIMARY KEY AUTO_INCREMENT,"
				+ "first_name VARCHAR(255) NOT NULL," 
				+ "last_name VARCHAR(255) NOT NULL,"
				+ "email VARCHAR(255) UNIQUE NOT NULL," 
				+ "phone_number VARCHAR(20)," 
				+ "tc_number VARCHAR(11) UNIQUE,"
				+ "password VARCHAR(55)" 
				+ ");";

		try {
			PreparedStatement sPreparedStatement = dbConnection.prepareStatement(qString);
			sPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int registerPassenger(RegisterRequest request) {
		// TODO Auto-generated method stub
		String queryString = "insert into passenger(first_name, last_name, email, phone_number, tc_number, password)"
				+ "values(?,?,?,?,?,?);";

		PreparedStatement pStatement;
		int n = 0;
		try {
			pStatement = dbConnection.prepareStatement(queryString);
			pStatement.setString(1, request.firstName());
			pStatement.setString(2, request.lastName());
			pStatement.setString(3, request.email());
			pStatement.setString(4, request.phoneNumber());
			pStatement.setString(5, request.tcNumber());
			pStatement.setString(6, request.password());

			n = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n;
	}

	@Override
	public boolean loginPassenger(LoginRequest request) {
		// TODO Auto-generated method stub

		String queryString = "select * from passenger where email == ? and password == ?";
		PreparedStatement pStatement;
		ResultSet result = null;
		try {
			pStatement = dbConnection.prepareStatement(queryString);
			pStatement.setString(1, request.email());
			pStatement.setString(2, request.password());
			result = pStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result != null ? true : false;

	}

	@Override
	public Passenger getPassengerInformation(String email) {
		List<Ticket> tickets = new ArrayList<>();
		// Passenger information
		String passengerQuery = "select * from passenger where email == ?";
		PreparedStatement preparedStatement;
		ResultSet passengerResult;
		try {
			preparedStatement = dbConnection.prepareStatement(passengerQuery);
			preparedStatement.setString(1, email);
			passengerResult = preparedStatement.executeQuery();
			if (passengerResult.next()) {
				long passengerId = passengerResult.getLong("passenger_id");
				String firstName = passengerResult.getString("first_name");
				String lastName = passengerResult.getString("last_name");
				String tcNumber = passengerResult.getString("tc_number"); // Assuming tcNumber exists
				String mail = passengerResult.getString("email");
				String phoneNumber = passengerResult.getString("phone_number");
				String password = passengerResult.getString("password");
				// Ticket information
				String ticketQuery = "select * from ticket where passenger_id == ?";
				PreparedStatement ticketStatement = dbConnection.prepareStatement(ticketQuery);
				ticketStatement.setLong(1, passengerId); // Using passengerId for tickets
				ResultSet ticketResult = ticketStatement.executeQuery();
				while (ticketResult.next()) {
					long ticketId = ticketResult.getLong("ticket_id");
					long flightId = ticketResult.getLong("flight_id");
					tickets.add(new Ticket(ticketId, flightId, passengerId));
				}
				return new Passenger(passengerId, firstName, lastName, tcNumber, mail, password, phoneNumber, tickets);
			} else {
				// Handle case where passenger is not found
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
