package com.hopur7h.hotels.hopur7h.view;

import com.hopur7h.hotels.hopur7h.model.Hotel;
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

    private Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;

        hotelName.setText(hotel.getName());
        hotelDescription.setText(hotel.getDescription());

        // Default dates for demo
        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 86400000L);

        int available = hotel.getAvailableRooms(checkIn, checkOut).size();
        availableRooms.setText("Available Rooms: " + available);
    }


    @FXML
    private void handleBookNow() {
        System.out.println("Booking hotel: " + hotel.getName());
        ((Stage) hotelName.getScene().getWindow()).close();
    }
}
