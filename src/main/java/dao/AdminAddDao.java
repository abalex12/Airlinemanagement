package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseConnector;
import model.Aircraft;
import model.Airport;
import model.Flight;

public class AdminAddDao {

    public boolean addFlight(Flight flight) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "INSERT INTO flight (flight_id,departure_airport_code, departure_airport_name, departure_city, " +
                         "arrival_airport_code, arrival_airport_name, arrival_city, " +
                         "departure_date, departure_time, arrival_time, aircraft_code, price) " +
                         "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, flight.getFlight_Id());
            preparedStatement.setString(2, flight.getDepartureAirportCode());
            preparedStatement.setString(3, flight.getDepartureAirportName());
            preparedStatement.setString(4, flight.getDeparture_city());
            preparedStatement.setString(5, flight.getArrivalAirportCode());
            preparedStatement.setString(6, flight.getArrivalAirportName());
            preparedStatement.setString(7, flight.getArrival_city());
            preparedStatement.setDate(8, flight.getDepartureDate());
            preparedStatement.setString(9, flight.getDepartureTime());
            preparedStatement.setString(10, flight.getArrivalTime());
            preparedStatement.setString(11, flight.getAircraftCode());
            preparedStatement.setDouble(12, flight.getprice());
            
            int rowCount = preparedStatement.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getLastFlightId() {
        int lastFlightId = 0;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT MAX(flight_id) AS last_flight_id FROM flight ");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                lastFlightId = resultSet.getInt("last_flight_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastFlightId;
    }
    public boolean addAirport(Airport airport){
        try(Connection connection=DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                "INSERT INTO airport (airport_code, name, city, country) VALUES (?, ?, ?, ?)")){
            preparedStatement.setString(1,airport.getAirportCode());
            preparedStatement.setString(2,airport.getName());
            preparedStatement.setString(3,airport.getCity());
            preparedStatement.setString(4,airport.getCountry());
            int rowCount=preparedStatement.executeUpdate();
            return rowCount>0;
            
        }catch(SQLException e){
           e.printStackTrace();
         
            return false;
        }
        
    }
    public boolean addAircraft(Aircraft aircraft){
        try(Connection connection=DatabaseConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                "INSERT INTO aircraft (aircraft_code, model, capacity) VALUES (?, ?, ?)")){
            preparedStatement.setString(1,aircraft.getAircraftCode());
            preparedStatement.setString(2,aircraft.getModel());
            preparedStatement.setInt(3,aircraft.getCapacity());
            int rowCount=preparedStatement.executeUpdate();
            return rowCount>0;
            
        }catch(SQLException e){
           e.printStackTrace();
         
            return false;
        }

}
}