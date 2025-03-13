package model;

import java.sql.Timestamp;

public class Reservation {
    private int bookingId;
    private int passengerId;
    private int flightId;
    private Timestamp bookingDate;
    private String seatNumber;
public Reservation(){}
public Reservation(int bookingId,int passengerId,int flightId,Timestamp bookingDate,String seatNumber){
    this.bookingId=bookingId;
    this.passengerId=passengerId;
    this.flightId=flightId;
    this.bookingDate=bookingDate;
    this.seatNumber=seatNumber;
}

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}

