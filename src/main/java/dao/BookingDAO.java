package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Reservation;
import util.*;

public class BookingDAO {
    public boolean addReservation(Reservation reservation) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO reservation( bookingId ,passengerid, flightid, bookingdate, seatnumber) VALUES (?, ?, ?, CURRENT_TIMESTAMP,?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, reservation.getBookingId());
            statement.setInt(2, reservation.getPassengerId());
            statement.setInt(3, reservation.getFlightId());
            statement.setString(4, reservation.getSeatNumber());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted>0;
        
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getLastBookingId() {
        int lastBookingId = 0;
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT MAX(bookingId) as maxbookingId FROM reservation");
            if (resultSet.next()) {
                lastBookingId = resultSet.getInt("maxbookingId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastBookingId;
    }
}
