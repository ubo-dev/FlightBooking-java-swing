package flightBooking.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import flightBooking.dto.LoginRequest;
import flightBooking.dto.RegisterRequest;

public class PassengerRepository implements IPassengerRepository {
	
	DatabaseConnection connection = DatabaseConnection.getInstance();
	Connection dbConnection = connection.getConnection();

	@Override
	public int RegisterPassenger(RegisterRequest request) {
		// TODO Auto-generated method stub
		String queryString = "insert into passengers(first_name, last_name, email, phone_number, tc_number, password)"
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
	public boolean LoginPassenger(LoginRequest request) {
		// TODO Auto-generated method stub
		
		String queryString = "select * from passengers where email == ? and password == ?";
		PreparedStatement pStatement;
		boolean result = false;
		try {
			pStatement = dbConnection.prepareStatement(queryString);
			pStatement.setString(1, request.email());
			pStatement.setString(2, request.password());
			result = pStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}
