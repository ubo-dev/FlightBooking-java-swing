package flightBooking.initializer;

import flightBooking.model.GenderType;
import flightBooking.model.Passenger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockDataInitializer {

    private final Connection connection;

    public MockDataInitializer(Connection connection) {
        this.connection = connection;
        initialize();
    }

    public void initialize() {
        insertMockCityData();
        insertMockFlightData();
        insertMockPassengers(generateMockPassengers(5));
    }

    private void insertMockCityData() {
        String query = "REPLACE INTO city (name) VALUES (?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            // Insert mock data for Turkish cities
            String[] turkishCities = {"Ankara", "Istanbul", "Izmir", "Bursa", "Antalya", "Trabzon", "Mardin", "Erzurum", "Çanakkale", "Edirne", "Konya"};
            for (String city : turkishCities) {
                pstmt.setString(1, city);
                pstmt.addBatch();
            }

            // Insert mock data for foreign cities
            String[] foreignCities = {"London", "Paris", "Berlin", "Rome", "New York", "Tokyo", "Madrid", "Budapeşte", "Budva", "Marislya", "Milan"};
            for (String city : foreignCities) {
                pstmt.setString(1, city);
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            connection.commit();
            System.out.println("Mock city data inserted successfully.");
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private void insertMockFlightData() {
        String query = "REPLACE INTO flight (city_departure_id, city_destination_id, capacity, booked_seats, departure_time, estimated_arrival_time) VALUES (?, ?, ?, ?, ?, ?)";
        Random random = new Random();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            // IDs for Turkish cities
            int[] turkishCityIds = {1, 2, 3, 4, 5, 12, 13, 14, 15, 16, 17};

            // IDs for foreign cities
            int[] foreignCityIds = {6, 7, 8, 9, 10, 11, 18, 19, 20, 21, 22};

            int[] cityDepartureIds = new int[40];
            int[] cityDestinationIds = new int[40];
            int[] capacities = new int[40];
            int[] bookedSeats = new int[40];
            LocalDateTime[] departureTimes = new LocalDateTime[40];
            LocalDateTime[] estimatedArrivalTimes = new LocalDateTime[40];

            for (int i = 0; i < capacities.length; i++) {
                capacities[i] = random.nextInt(201) + 100;
                bookedSeats[i] = random.nextInt(capacities[i] + 1);
            }

            LocalDateTime currentDateTime = LocalDateTime.now().plusDays(1);
            for (int i = 0; i < departureTimes.length; i++) {
                departureTimes[i] = currentDateTime.plusHours(random.nextInt(12) + 1);
                estimatedArrivalTimes[i] = departureTimes[i].plusHours(random.nextInt(5) + 1);
            }

            // Insert mock data
            for (int i = 0; i < cityDepartureIds.length; i++) {
                if (i < 20) {
                    cityDepartureIds[i] = turkishCityIds[random.nextInt(turkishCityIds.length)];
                    cityDestinationIds[i] = turkishCityIds[random.nextInt(turkishCityIds.length)];
                } else {
                    cityDepartureIds[i] = turkishCityIds[random.nextInt(turkishCityIds.length)];
                    cityDestinationIds[i] = foreignCityIds[random.nextInt(foreignCityIds.length)];
                }

                preparedStatement.setInt(1, cityDepartureIds[i]);
                preparedStatement.setInt(2, cityDestinationIds[i]);
                preparedStatement.setInt(3, capacities[i]);
                preparedStatement.setInt(4, bookedSeats[i]);
                preparedStatement.setTimestamp(5, Timestamp.valueOf(departureTimes[i]));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(estimatedArrivalTimes[i]));

                preparedStatement.addBatch();
            }

            for (int j = cityDestinationIds.length - 1; j > 0; j--) {

                preparedStatement.setInt(1, cityDepartureIds[j]);
                preparedStatement.setInt(2, cityDestinationIds[j]);
                preparedStatement.setInt(3, capacities[j]);
                preparedStatement.setInt(4, bookedSeats[j]);
                preparedStatement.setTimestamp(5, Timestamp.valueOf(departureTimes[j]));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(estimatedArrivalTimes[j]));
                preparedStatement.addBatch();
            }

            for (int j = cityDestinationIds.length - 1; j > 0; j -= 3) {

                preparedStatement.setInt(1, cityDepartureIds[j]);
                preparedStatement.setInt(2, cityDestinationIds[j]);
                preparedStatement.setInt(3, capacities[j]);
                preparedStatement.setInt(4, bookedSeats[j]);
                preparedStatement.setTimestamp(5, Timestamp.valueOf(departureTimes[j]));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(estimatedArrivalTimes[j]));
                preparedStatement.addBatch();
            }

            for (int i = 0; i < cityDepartureIds.length; i += 3) {
                if (i < 20) {
                    cityDepartureIds[i] = turkishCityIds[random.nextInt(turkishCityIds.length)];
                    cityDestinationIds[i] = turkishCityIds[random.nextInt(turkishCityIds.length)];
                } else {
                    cityDepartureIds[i] = turkishCityIds[random.nextInt(turkishCityIds.length)];
                    cityDestinationIds[i] = foreignCityIds[random.nextInt(foreignCityIds.length)];
                }

                preparedStatement.setInt(1, cityDepartureIds[i]);
                preparedStatement.setInt(2, cityDestinationIds[i]);
                preparedStatement.setInt(3, capacities[i]);
                preparedStatement.setInt(4, bookedSeats[i]);
                preparedStatement.setTimestamp(5, Timestamp.valueOf(departureTimes[i]));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(estimatedArrivalTimes[i]));

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Mock flight data inserted successfully.");
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Passenger> generateMockPassengers(int count) {
        List<Passenger> passengers = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= count; i++) {
            long passengerId = i;
            String firstName = getRandomFirstName();
            String lastName = getRandomLastName();
            String tcNumber = generateRandomTcNumber();
            String email = firstName.toLowerCase() + lastName.toLowerCase() + "@example.com";
            String phoneNumber = generateRandomPhoneNumber();
            String password = generateRandomPassword();

            Passenger passenger = new Passenger(passengerId, firstName, lastName, tcNumber, email, phoneNumber, password);
            passengers.add(passenger);
        }

        return passengers;
    }

    private static String getRandomFirstName() {
        String[] firstNames = {"John", "Emily", "Michael", "Sophia", "David", "Emma", "Daniel", "Olivia"};
        Random random = new Random();
        return firstNames[random.nextInt(firstNames.length)];
    }

    private static String getRandomLastName() {
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};
        Random random = new Random();
        return lastNames[random.nextInt(lastNames.length)];
    }

    private static String generateRandomTcNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static String generateRandomPhoneNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        sb.append("+1"); // Assuming US phone numbers
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public void insertMockPassengers(List<Passenger> passengers) {
        String query = "INSERT INTO passenger_t (first_name, last_name, tc_number, email, phone_number, password) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Passenger passenger : passengers) {
                preparedStatement.setString(1, passenger.getFirstName());
                preparedStatement.setString(2, passenger.getLastName());
                preparedStatement.setString(3, passenger.getTcNumber());
                preparedStatement.setString(4, passenger.getEmail());
                preparedStatement.setString(5, passenger.getPhoneNumber());
                preparedStatement.setString(6, passenger.getPassword());
                preparedStatement.addBatch();
            }

            int[] rowsInserted = preparedStatement.executeBatch();
            System.out.println("Inserted " + rowsInserted.length + " passengers.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
