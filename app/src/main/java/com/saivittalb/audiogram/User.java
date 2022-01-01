package com.saivittalb.audiogram;

public class User {

    private String fullname;
    private String phoneNumber;
    private String location;
    private String email;
    private String lastExamination;

    public String getLastExamination() {
        return lastExamination;
    }

    public void setLastExamination(String lastExamination) {
        this.lastExamination = lastExamination;
    }


    public User(){

    }

    public User(String fullname, String phoneNumber, String location, String email, String lastExamination) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.email = email;
        this.lastExamination = "No research";
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
