package com.example.ghb_draft;

public class Accounts {
    private String email, type;
    private Float balance;
    private Integer studentNumber;

    public Accounts(String email, Float balance, String type, Integer studentNumber){
        this.email = email;
        this.balance = balance;
        this.type = type;
        this.studentNumber = studentNumber;
    }
    public Accounts() {
        this.email = "";
        this.balance = null;
        this.type = "";
        this.studentNumber = null;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", studentNumber=" + studentNumber +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }
}
