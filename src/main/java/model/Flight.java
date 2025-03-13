package model;

import java.sql.Date;

public class Flight {
    private int flight_Id;
    private String departureAirportCode;
    private String departureAirportName;
    private String departure_city;
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrival_city;
    private Date departureDate;
    private String departureTime;
    private String arrivalTime;
    private String aircraftCode;
    private double price;


    public Flight(){}        

    public Flight(int flight_Id,String departureAirportCode,String departureAirportName,String departure_city,String arrivalAirportCode,String arrivalAirportName,String arrival_city,String departureTime,Date departuredate,String arrivalTime,String aircraftCode,double price){
    
        this.flight_Id=flight_Id;
        this.departureAirportCode=departureAirportCode;
        this.departureAirportName=departureAirportName;
        this.departure_city=departure_city;
        this.arrivalAirportCode=arrivalAirportCode;
        this.arrivalAirportName=arrivalAirportName;
        this.arrival_city=arrival_city;
        this.departureTime=departureTime;
        this.departureDate=departuredate;
        this.arrivalTime=arrivalTime;
        this.aircraftCode=aircraftCode;
        this.price=price;
    }

    // Getters and Settertime

    public int getFlight_Id() {
        return flight_Id;
    }

    public void setFlight_Id(int flight_Id) {
        this.flight_Id = flight_Id;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public Date getDepartureDate(){
        return departureDate;
    }
    public void setDepartureDate(Date departuredate){
        this.departureDate=departuredate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }
    public double getprice(){
        return price;
      }
    public void setprice(double price){
        this.price=price;
      }
    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }
    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }
    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }
    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city= arrival_city;
    }
}

