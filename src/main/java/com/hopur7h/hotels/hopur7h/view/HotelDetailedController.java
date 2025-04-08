package com.hopur7h.hotels.hopur7h.view;

import com.hopur7h.hotels.hopur7h.controller.BookingController;
import com.hopur7h.hotels.hopur7h.model.Booking;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class HotelDetailedController {

    @FXML
    private Label hotelName;
    @FXML
    private Label currentUserLabel;

    @FXML
    private Label hotelDescription;

    @FXML
    private ImageView hotelImage;

    @FXML
    private Label availableRooms;

    @FXML
    private DatePicker checkInPicker;

    @FXML
    private DatePicker checkOutPicker;

    private Customer customer;
    private Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
        hotelName.setText(hotel.getName() + " | " + hotel.getId());
        hotelDescription.setText(hotel.getDescription());

        try {
            String imagePath = "/images/" + hotel.getImagesURL();
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            hotelImage.setImage(image);
        } catch (Exception e) {
            System.out.println("Image not found for hotel: " + hotel.getImagesURL());
        }
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
        currentUserLabel.setText("Logged in as: " + customer.getUsername());
    }


    @FXML
    private void handleCheckAvailability() {
        Date checkIn = getDateFromPicker(checkInPicker);
        Date checkOut = getDateFromPicker(checkOutPicker);

        if (checkIn == null || checkOut == null || !checkOut.after(checkIn)) {
            availableRooms.setText("Invalid dates selected.");
            return;
        }

        int available = hotel.getAvailableRooms(checkIn, checkOut);
        if (available > 0) {
            availableRooms.setText("Available rooms: " + available);
        } else {
            availableRooms.setText("No rooms available for selected dates.");
        }

    }

    @FXML
    private void handleBookNow() {
        Date checkIn = getDateFromPicker(checkInPicker);
        Date checkOut = getDateFromPicker(checkOutPicker);

        if (checkIn == null || checkOut == null || !checkOut.after(checkIn)) {
            availableRooms.setText("Invalid dates selected.");
            return;
        }

        if (!hotel.isAvailable(checkIn, checkOut)) {
            availableRooms.setText("Cannot book. Some dates are unavailable.");
            return;
        }

        BookingController bookingController = new BookingController();
        Booking booking = bookingController.createNewBooking(customer, checkIn, checkOut, hotel);

        if (booking != null) {
            System.out.println("Booking created: " + booking.getId());
            availableRooms.setText("Booking successful.");
            ((Stage) hotelName.getScene().getWindow()).close();
        }
    }

    @FXML
    private void handlePrintBookings() {
        BookingController bookingController = new BookingController();
        List<Booking> bookings = bookingController.getAllBookings();

        System.out.println("All Bookings:");
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getId()
                    + ", Customer: " + booking.getCustomer().getUsername()
                    + ", Hotel: " + booking.getHotel().getName()
                    + ", Check-in: " + booking.getCheckIn()
                    + ", Check-out: " + booking.getCheckOut()
                    + ", Status: " + booking.getStatus()
                    + ", Payment: " + booking.getPaymentMethod());
        }
    }

    private Date getDateFromPicker(DatePicker picker) {
        LocalDate localDate = picker.getValue();
        if (localDate == null) return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
