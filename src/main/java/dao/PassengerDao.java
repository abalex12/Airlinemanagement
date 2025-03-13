package dao;
import model.Passenger;
import util.*;
import java.sql.*;

public class PassengerDao {
    public void addPassenger(Passenger passenger) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO passenger (passengerid, name, gender,email, phonenumber, dateofbirth) VALUES (? ,? ,?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
                        
            statement.setInt(1, passenger.getPassengerId());
            statement.setString(2, passenger.getName());
            statement.setString(3, passenger.getGender());
            statement.setString(4, passenger.getEmail());
            statement.setString(5, passenger.getPhoneNumber());
            statement.setDate(6,passenger.getDateOfBirth());

            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
       
    }

    public int getLastPassengerId() {
        int lastPassengerId = 0;
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT MAX(passengerid) as maxpassengerId FROM passenger");
            if (resultSet.next()) {
                lastPassengerId = resultSet.getInt("maxpassengerId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastPassengerId;
    }
}
