package com.example.ghb_draft;

public class UserModel {
    private String fullName, userEmail, userPassword, userPhone;

    // constructors

    public UserModel(String fullName, String userPhone, String userEmail, String userPassword) {
        this.fullName = fullName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
    }

    public UserModel() {

    }

    // toString


    @Override
    public String toString() {
        return "UserModel{" +
                "fullName='" + fullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }

    // getters and setters


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}