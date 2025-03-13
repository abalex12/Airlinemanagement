package model;

import java.sql.Date;

public class Passenger {
    private int passengerId;
    private String Name;
    private String Gender;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
public Passenger(){}
public Passenger(int passengerId,String Name,String Gender,String email,String phoneNumber,Date dateofBirth){
    this.passengerId=passengerId;
    this.Name=Name;
    this.Gender=Gender;
    this.email=email;
    this.phoneNumber=phoneNumber;
    this.dateOfBirth=dateofBirth;
}
    // Getters and Setters
    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return Name;
    }

    public void setFirstName(String Name) {
        this.Name = Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
