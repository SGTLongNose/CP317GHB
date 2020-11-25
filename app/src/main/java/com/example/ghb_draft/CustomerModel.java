package com.example.ghb_draft;

public class CustomerModel {
    private int id;
    private String name, email;

    // constructors

    public CustomerModel(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public CustomerModel() {
    }
    // toString is necessary for printing the contents of a class object

    @Override
    public String toString() {
        return
                "Name: " + name +
                ", Email: " + email;
    }

    // getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
