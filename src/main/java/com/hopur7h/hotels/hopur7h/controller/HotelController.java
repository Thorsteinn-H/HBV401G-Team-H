package com.hopur7h.hotels.hopur7h.controller;

import com.hopur7h.hotels.hopur7h.model.Hotel;
import com.hopur7h.hotels.hopur7h.storage.HotelDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Nafn : Þjórsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class HotelController {

    private HotelDB hotelDB = new HotelDB();

    // retrieves hotel by name
    public Hotel getHotel(String name) {
        List<Hotel> allHotels = hotelDB.getAllHotels();
        for (Hotel hotel : allHotels) {
            if (hotel.getName().equalsIgnoreCase(name)) {
                return hotel;
            }
        }
        return null;
    }

    // searches for hotels that match a given search string
    public List<Hotel> searchHotels(String string) {
        List<Hotel> matching = new ArrayList<>();
        String query = string.toLowerCase();

        for (Hotel hotel : hotelDB.getAllHotels()) {
            if (hotel.getName().toLowerCase().contains(query) ||
                    hotel.getLocation().toLowerCase().contains(query)) {
                matching.add(hotel);
            }
        }
        return matching;
    }

    // retrieves a list of all hotels managed by this controller
    public List<Hotel> getAllHotels() {
        return hotelDB.getAllHotels();
    }

    // adds hotel to the database
    public void addHotel(Hotel hotel) {
        hotelDB.addHotel(
                hotel.getName(),
                hotel.getLocation(),
                List.of(hotel.getAmenities().split(", ")),
                hotel.getDescription(),
                hotel.getImagesURL()
        );
    }

    // removes a hotel from the database by name
    public void removeHotel(String name) {
        Hotel hotel = getHotel(name);
        if (hotel != null) {
            hotelDB.deleteHotel(hotel.getId());
        }
    }
}
