package com.hopur7h.hotels.hopur7h.controller;

import com.hopur7h.hotels.hopur7h.model.Booking;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import com.hopur7h.hotels.hopur7h.storage.BookingDB;

import java.util.Date;
import java.util.List;

public class BookingController {

    private BookingDB bookingDB = new BookingDB();

    public List<Booking> getAllBookings() {
        return bookingDB.getAllBookings();
    }

    // Creates new booking and stores it in the database
    public Booking createNewBooking(Customer customer, Date checkIn, Date checkOut, Hotel hotel) {
        if (!hotel.isAvailable(checkIn, checkOut)) {
            System.out.println("Booking failed: some days are fully booked.");
            return null;
        }

        hotel.bookDates(checkIn, checkOut);

        int generatedId = bookingDB.addBooking(
                customer,
                hotel,
                checkIn,
                checkOut,
                "pending",
                "card"
        );

        if (generatedId == -1) {
            System.out.println("Booking failed to persist.");
            return null;
        }

        return new Booking(generatedId, customer, hotel, checkIn, checkOut, "pending", "card");
    }

    public boolean removeBooking(Booking booking) {
        bookingDB.deleteBooking(booking.getId());
        return true;
    }

    public Booking getBooking(int id) {
        return bookingDB.getBookingById(id);
    }
}
