package com.hopur7h.hotels.hopur7h.view;

import com.hopur7h.hotels.hopur7h.controller.HotelController;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import com.hopur7h.hotels.hopur7h.model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainViewController {

    @FXML
    private ListView<String> hotelListView;

    @FXML
    private TextField searchField;

    private HotelController hotelController = new HotelController();

    @FXML
    public void initialize() {
        addTemporaryHotels();
        showAllHotels();
    }

    private void addTemporaryHotels() {
        List<String> amenities = List.of("Free WiFi", "Breakfast included", "Hot tub");

        List<Date> availableDates = new ArrayList<>();
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + 86400000L);
        availableDates.add(today);
        availableDates.add(tomorrow);

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, availableDates, 120, 1, "room1.jpg"));
        rooms.add(new Room(2, availableDates, 200, 2, "room2.jpg"));

        hotelController.addHotel(new Hotel("Reykjavik Inn", "Reykjavik", amenities, "Comfortable city stay.", rooms, "img1.jpg"));
        hotelController.addHotel(new Hotel("Northern Lights Lodge", "Akureyri", amenities, "Watch the aurora.", rooms, "img2.jpg"));
        hotelController.addHotel(new Hotel("Glacier Stay", "Hofn", amenities, "Stay near Vatnajökull glacier.", rooms, "img3.jpg"));
    }

    private void showAllHotels() {
        hotelListView.getItems().clear();
        for (Hotel hotel : hotelController.getAllHotels()) {
            hotelListView.getItems().add(formatHotelInfo(hotel));
        }
    }

    private String formatHotelInfo(Hotel hotel) {
        return hotel.getName() + " - " + hotel.getLocation() + " | " + hotel.getAmenities();
    }

    @FXML
    private void onSearchClick() {
        String query = searchField.getText().trim();
        hotelListView.getItems().clear();

        List<Hotel> results = hotelController.searchHotels(query);
        for (Hotel hotel : results) {
            hotelListView.getItems().add(formatHotelInfo(hotel));
        }
    }
}
