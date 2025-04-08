package com.hopur7h.hotels.hopur7h.view;

import com.hopur7h.hotels.hopur7h.controller.HotelController;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import com.hopur7h.hotels.hopur7h.model.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainViewController {

    @FXML
    private ListView<String> hotelListView;
    @FXML
    private TextField searchField;

    private HotelController hotelController = new HotelController();

    private Customer placeholderCustomer;

    @FXML
    public void initialize() {
        addTemporaryHotels();
        showAllHotels();
        placeholderCustomer = new Customer(1, "demoUser", "Demo User", "pass123", "demo@placeholder.com", "1234567890");

        hotelListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double click
                int selectedIndex = hotelListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    Hotel selectedHotel = hotelController.getAllHotels().get(selectedIndex);
                    openHotelDetails(selectedHotel);
                }
            }
        });
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

    private void openHotelDetails(Hotel hotel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hopur7h/hotels/hopur7h/view/HotelDetailed.fxml"));
            Parent root = loader.load();

            HotelDetailedController controller = loader.getController();
            controller.setHotel(hotel);
            controller.setCustomer(placeholderCustomer);

            Stage stage = new Stage();
            stage.setTitle("Hotel Details");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
