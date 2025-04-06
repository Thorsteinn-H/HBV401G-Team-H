package com.hopur7h.hotels.hopur7h.controller;


import com.hopur7h.hotels.hopur7h.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class HotelController {


    private List<Hotel> hotels = new ArrayList<>();

    // retrieves hotel by name
    public Hotel getHotel(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                return hotel;
            }
        }
        return null;
    }

    // searches for hotels that match a given search string
    public List<Hotel> searchHotels(String string) {
        List<Hotel> matching = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getName().contains(string) || hotel.getLocation().contains(string)) {
                matching.add(hotel);
            }
        }
        return matching;
    }

    // retrieves a list of all hotels managed by this controller
    public List<Hotel> getAllHotels() {
        return new ArrayList<>(hotels);
    }

    // adds hotel to the list
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    // removes a hotel off the list
    public void removeHotel(String name) {
        hotels.removeIf(hotel -> hotel.getName().equals(name));
    }

}
