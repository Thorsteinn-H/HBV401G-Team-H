package com.hopur7h.hotels.hopur7h.view;

import com.hopur7h.hotels.hopur7h.controller.BookingController;
import com.hopur7h.hotels.hopur7h.model.Booking;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import com.hopur7h.hotels.hopur7h.model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Date;

public class HotelDetailedController {

    @FXML
    private Label hotelName;
    @FXML
    private Label hotelDescription;
    @FXML
    private Label availableRooms;

    private Customer customer;


    private Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;

        hotelName.setText(hotel.getName() + " | " + hotel.getId());
        hotelDescription.setText(hotel.getDescription());

        // Default dates for demo
        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 86400000L);

        int available = hotel.getAvailableRooms(checkIn, checkOut).size();
        availableRooms.setText("Available Rooms: " + available);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @FXML
    private void handleBookNow() {
        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 86400000L); // +1 day

        Room availableRoom = hotel.getAvailableRooms(checkIn, checkOut)
                .stream().findFirst().orElse(null);

        if (availableRoom != null) {
            BookingController bookingController = new BookingController();
            Booking booking = bookingController.createNewBooking(
                    customer,
                    checkIn,
                    checkOut,
                    hotel,
                    availableRoom
            );
            System.out.println("Booking created: " + booking.getId());
        } else {
            System.out.println("No available rooms to book.");
        }

        ((Stage) hotelName.getScene().getWindow()).close();
    }

}
