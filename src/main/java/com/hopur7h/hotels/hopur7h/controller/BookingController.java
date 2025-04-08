package com.hopur7h.hotels.hopur7h.controller;

import com.hopur7h.hotels.hopur7h.model.Booking;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import com.hopur7h.hotels.hopur7h.model.Room;
import com.hopur7h.hotels.hopur7h.storage.BookingDB;

import java.util.Date;
import java.util.List;

/**
 * Nafn : Þjórsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class BookingController {

    private BookingDB bookingDB = new BookingDB();

    public List<Booking> getAllBookings() {
        return bookingDB.getAllBookings();
    }

    // creates new booking and stores it in the database
    public Booking createNewBooking(Customer customer, Date checkIn, Date checkOut, Hotel hotel, Room roomChosen) {
        bookingDB.addBooking(
                customer,
                hotel,
                roomChosen.getId(),
                checkIn,
                checkOut,
                roomChosen.getPrice(),
                "pending",
                "card"
        );

        // Optionally, return the latest booking by customer/hotel/date (not perfect but okay as placeholder)
        return new Booking(1, customer, roomChosen.getId(), hotel, checkIn, checkOut, roomChosen.getPrice(), "pending", "card");
    }

    // deletes a booking by id
    public boolean removeBooking(Booking booking) {
        bookingDB.deleteBooking(booking.getId());
        return true;
    }

    // fetches a booking by id
    public Booking getBooking(int id) {
        return bookingDB.getBookingById(id);
    }
}
