package com.hopur7h.hotels.hopur7h.model;

public class Customer {

    private int id;
    private String username;
    private String realName;
    private String password;
    private String email;
    private String phoneNumber;

    public Customer(int id, String username, String realName, String password, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return realName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean verifyPassword(String input) {
        return password.equals(input);
    }
}
