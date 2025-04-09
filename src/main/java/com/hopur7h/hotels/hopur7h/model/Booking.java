package com.hopur7h.hotels.hopur7h.model;

import java.util.Date;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class Booking {


    private Integer id;
    private Customer customer;
    private Hotel hotel;
    private Date checkIn;
    private Date checkOut;
    private String status;
    private String paymentMethod;

    public Booking(Integer id, Customer customer, Hotel hotel, Date checkIn, Date checkOut, String status, String paymentMethod) {
        this.id = id;
        this.customer = customer;
        this.hotel = hotel;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }


    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // cancels this booking
    public void cancelBooking() {
        status = "cancelled";
    }

    /**
     * // processes a refund based on the payment method used
     * public void refund() {
     * if (paymentMethod.equals("card")) {
     * // refund through card
     * } else if (paymentMethod.equals("cash")) {
     * // refund through cash
     * }
     * }
     * <p>
     * // process payment for this booking
     * public void processPayment(String paymentMethod, Integer price) {
     * if (paymentMethod.equals("card")) {
     * // process payment through card
     * } else if (paymentMethod.equals("cash")) {
     * // process payment through cash
     * }
     * }
     **/

    // method to create and return a new booking
    public static Booking bookHotel(Customer customer, Date checkIn, Date checkOut, Hotel hotel) {
        return new Booking(1, customer, hotel, checkIn, checkOut, "pending", "card");
    }


}
