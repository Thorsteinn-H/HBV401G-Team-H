package com.hopur7h.hotels.hopur7h.model;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class Customer {


    private String username;
    private String realName;
    private String password;
    private String email;
    private String phoneNumber;

    // constructs a new customer object with details
    public Customer(String username, String realName, String password, String email, String phoneNumber) {
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // retrieves username of the customer
    public String getUsername() {
        return username;
    }

    // retrieves real name of the customer
    public String getName() {
        return realName;
    }

    // retrieves the email address of the customer
    public String getEmail() {
        return email;
    }

    // retrieves the phone number of the customer
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // checks if given input matches the customer passwords
    public boolean verifyPassword(String input) {
        return password.equals(input);
    }

}
