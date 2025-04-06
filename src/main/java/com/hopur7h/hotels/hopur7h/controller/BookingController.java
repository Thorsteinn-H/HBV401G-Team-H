package com.hopur7h.hotels.hopur7h.controller;

import com.hopur7h.hotels.hopur7h.model.Booking;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import com.hopur7h.hotels.hopur7h.model.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class BookingController {

    private List<Booking> bookings = new ArrayList<>();

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    // creates new booking based on given details
    public Booking createNewBooking(Customer customer, Date checkIn, Date checkOut, Hotel hotel, Room roomChosen) {
        Booking newBooking = Booking.bookHotel(customer, checkIn, checkOut, hotel, roomChosen);
        bookings.add(newBooking);
        return newBooking;
    }

    // removes specified booking from the list
    public boolean removeBooking(Booking booking) {
        return bookings.remove(booking);
    }

    // searches for a booking by booking id
    public Booking getBooking(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null; // or throw an exception if not found
    }
}


