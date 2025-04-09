package com.hopur7h.hotels.hopur7h.view;

import com.hopur7h.hotels.hopur7h.controller.CustomerController;
import com.hopur7h.hotels.hopur7h.controller.HotelController;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    @FXML
    private Label currentUserLabel;

    private HotelController hotelController = new HotelController();
    private CustomerController customerController = new CustomerController();

    private Customer placeholderCustomer;
    private List<Hotel> displayedHotels = new ArrayList<>();

    @FXML
    public void initialize() {
        addTemporaryHotels();
        showAllHotels();
        Customer existing = customerController.getCustomer("demoUser");
        if (existing == null) {
            Customer demo = new Customer(1, "demoUser", "Demo User", "pass123", "demo@placeholder.com", "1234567890");
            customerController.addCustomer(demo);
            placeholderCustomer = customerController.getCustomer("demoUser");
        } else {
            placeholderCustomer = existing;
        }
        currentUserLabel.setText("Logged in as: " + placeholderCustomer.getUsername());

        hotelListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int selectedIndex = hotelListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < displayedHotels.size()) {
                    Hotel selectedHotel = displayedHotels.get(selectedIndex);
                    openHotelDetails(selectedHotel);
                }
            }
        });
    }


    private void addTemporaryHotels() {
        List<String> amenities = List.of("Free WiFi", "Breakfast included", "Hot tub");

        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + 86400000L);


        hotelController.addHotel(new Hotel("Reykjavik Inn", "Reykjavik", amenities, "Comfortable city stay.", "hotel.jpeg"));
        hotelController.addHotel(new Hotel("Northern Lights Lodge", "Akureyri", amenities, "Watch the aurora.", "hotel.jpeg"));
        hotelController.addHotel(new Hotel("Glacier Stay", "Hofn", amenities, "Stay near Vatnajökull glacier.", "hotel.jpeg"));

    }


    private void showAllHotels() {
        displayedHotels = hotelController.getAllHotels();
        hotelListView.getItems().clear();
        for (Hotel hotel : displayedHotels) {
            hotelListView.getItems().add(formatHotelInfo(hotel));
        }
    }

    @FXML
    private void onSearchClick() {
        String query = searchField.getText().trim();
        displayedHotels = hotelController.searchHotels(query);
        hotelListView.getItems().clear();
        for (Hotel hotel : displayedHotels) {
            hotelListView.getItems().add(formatHotelInfo(hotel));
        }
    }


    private String formatHotelInfo(Hotel hotel) {
        return hotel.getName() + " - " + hotel.getLocation() + " | " + hotel.getAmenities();
    }


    private void openHotelDetails(Hotel hotel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hopur7h/hotels/hopur7h/view/HotelDetailed.fxml"));
            Parent root = loader.load();

            HotelDetailedController controller = loader.getController();
            controller.setHotel(hotel);
            controller.setCustomer(placeholderCustomer);

            Stage stage = new Stage();
            stage.setTitle(hotel.getName());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
