package dao;

import util.DatabaseConnector;
import model.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchDao {
    // Method to search for flights
    public List<Flight> usersearchFlights(String source, String destination, String Date) {
        List<Flight> matchingFlights = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM flight WHERE departure_city = ? AND arrival_city = ? AND departureDate = STR_TO_DATE(?, '%Y-%m-%d')";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, source);
                preparedStatement.setString(2, destination);
                preparedStatement.setString(3, Date);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Create Flight objects from the result set
                        Flight flight = new Flight();
                        flight.setFlight_Id(resultSet.getInt("flight_id"));
                        flight.setDepartureAirportCode(resultSet.getString("departureairportcode"));
                        flight.setDeparture_city(resultSet.getString("departure_city"));
                        flight.setArrivalAirportCode(resultSet.getString("arrivalairportcode"));
                        flight.setArrival_city(resultSet.getString("arrival_city"));
                        flight.setDepartureDate(resultSet.getDate("departuredate"));
                        flight.setDepartureTime(resultSet.getString("departuretime"));
                        flight.setArrivalTime(resultSet.getString("arrivaltime"));
                        flight.setprice(resultSet.getDouble("Price"));
                    
                        // Add the flight to the list of matching flights
                        matchingFlights.add(flight);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingFlights;
    }
    
        // Method to get flight details by flight number
        public Flight getFlightByFlightNumber(String flightNumber) {
           Flight flightnew=null;
            try (Connection connection = DatabaseConnector.getConnection()) {
                String sql = "SELECT * FROM flights WHERE flight_number = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, flightNumber);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            flightnew = new Flight();
                            flightnew.setFlight_Id(resultSet.getInt("flight_id"));
                            flightnew.setDepartureAirportCode(resultSet.getString("departure_airport_code"));
                            flightnew.setDeparture_city(resultSet.getString("departure_city"));
                            flightnew.setArrivalAirportCode(resultSet.getString("arrival_airport_code"));
                            flightnew.setArrival_city(resultSet.getString("arrival_city"));
                            flightnew.setDepartureDate(resultSet.getDate("departure_date"));
                            flightnew.setDepartureTime(resultSet.getString("departure_time"));
                            flightnew.setArrivalTime(resultSet.getString("arrival_time"));
                            flightnew.setprice(resultSet.getDouble("Price"));
                    
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return flightnew;
        }
    }
    

