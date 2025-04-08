package com.hopur7h.hotels.hopur7h.view;

import com.hopur7h.hotels.hopur7h.controller.BookingController;
import com.hopur7h.hotels.hopur7h.model.Booking;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class HotelDetailedController {

    @FXML
    private Label hotelName;

    @FXML
    private Label hotelDescription;

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
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @FXML
    private void handleCheckAvailability() {
        Date checkIn = getDateFromPicker(checkInPicker);
        Date checkOut = getDateFromPicker(checkOutPicker);

        if (checkIn == null || checkOut == null || !checkOut.after(checkIn)) {
            availableRooms.setText("Invalid dates selected.");
            return;
        }

        if (hotel.isAvailable(checkIn, checkOut)) {
            availableRooms.setText("Available for selected dates.");
        } else {
            List<Date> unavailable = hotel.getUnavailableDates(checkIn, checkOut);
            availableRooms.setText("Unavailable on: " + unavailable.toString());
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

        System.out.println("=== All Bookings ===");
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
