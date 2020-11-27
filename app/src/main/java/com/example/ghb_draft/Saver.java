package com.example.ghb_draft;

public class Saver {
    private String email;

    // constructors

    public Saver(String email) {
        this.email = "email";
    }

    public Saver() {
        this.email = "";
    }

    // toString
    @Override
    public String toString() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
