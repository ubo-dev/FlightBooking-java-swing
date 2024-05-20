package flightBooking.repository.city;

import flightBooking.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepositoryImpl implements CityRepository {

    private final Connection connection;

    public CityRepositoryImpl(Connection connection) {
        this.connection = connection;
        createCityTable();
    }

    private void createCityTable() {
        String query = "create table if not exists city ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255) NOT NULL"
                + ");";

        try(PreparedStatement pStatement = connection.prepareStatement(query)) {
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<City> getAllCities() {
        String query = "select * from city";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<City> cities = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                City city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
