package dao;
import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnector;

public class AdminSearch{
    
    // Method to retrieve flights by date
    public List<Flight> getFlightsByDate(String date) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flight WHERE departure_date = To_date(?,'YYYY-MM-DD')";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, date);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Flight flightnew = new Flight();
                flightnew.setFlight_Id(resultSet.getInt("flight_id"));
                flightnew.setDepartureAirportCode(resultSet.getString("departure_airport_code"));
                flightnew.setDeparture_city(resultSet.getString("departure_city"));
                flightnew.setArrivalAirportCode(resultSet.getString("arrival_airport_code"));
                flightnew.setArrival_city(resultSet.getString("arrival_city"));
                flightnew.setDepartureDate(resultSet.getDate("departure_date"));
                flightnew.setDepartureTime(resultSet.getString("departure_time"));
                flightnew.setArrivalTime(resultSet.getString("arrival_time"));
                flightnew.setprice(resultSet.getDouble("Price"));
                flights.add(flightnew);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    // Method to retrieve airports by name
    public List<Airport> getAirportsByCity(String city ) {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT * FROM airport WHERE city = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Airport airport = new Airport();
                airport.setAirportCode(resultSet.getString("airport_code"));
                airport.setName(resultSet.getString("name"));
                airport.setCity(resultSet.getString("city"));
                airport.setCountry(resultSet.getString("country"));
        
                airports.add(airport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airports;
    }

    // Method to retrieve aircraft by code
    public List<Aircraft> getAircraftByCode(String code) {
        List<Aircraft> aircraftList = new ArrayList<>();
        String sql = "SELECT * FROM aircraft WHERE aircraft_code = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setAircraftCode(resultSet.getString("aircraft_code"));
                aircraft.setModel(resultSet.getString("model"));
                aircraft.setCapacity(resultSet.getInt("capacity"));
                aircraftList.add(aircraft);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aircraftList;
    }

    public List<Passenger> getPassengersByFlightNumber(String flightNumber) {
        List<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT p.* FROM booking b JOIN passenger p ON b.passenger_id = p.passenger_id WHERE b.flight_id = ?";
    
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, flightNumber);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setPassengerId(resultSet.getInt("passenger_id"));
                passenger.setFirstName(resultSet.getString("first_name"));
                passenger.setGender(resultSet.getString("gender"));
                passenger.setEmail(resultSet.getString("email"));
                passenger.setPhoneNumber(resultSet.getString("phone_number"));
                passenger.setDateOfBirth(resultSet.getDate("date_of_birth"));        
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return passengers;
    }
    
}
