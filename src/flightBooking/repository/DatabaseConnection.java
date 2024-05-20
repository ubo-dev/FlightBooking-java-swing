package flightBooking.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static DatabaseConnection instance;
	private Connection connection;
        

	private DatabaseConnection() throws SQLException {
		// Replace with your connection details
		String url = "jdbc:mysql://localhost:3306/flight_booking";
		String username = "root";
		String password = "mysqlpasswd";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver not found: " + ex.getMessage());
		}
	}

	public static DatabaseConnection getInstance() {
		try {
			if (instance == null) {
				instance = new DatabaseConnection();
			} else if (instance.connection.isClosed()) {
				// Reconnect if the existing connection is closed
				instance = new DatabaseConnection();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
