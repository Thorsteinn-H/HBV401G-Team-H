package com.hopur7h.hotels.hopur7h.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class Hotel {


    private String name;
    private String location;
    private List<String> amenities;
    private String description;
    private List<Room> rooms;
    private String imagesURL;

    //constructs a new hotel object with following details
    public Hotel(String name, String location, List<String> amenities, String description, List<Room> rooms, String imagesURL) {
        this.name = name;
        this.location = location;
        this.amenities = amenities;
        this.description = description;
        this.rooms = rooms;
        this.imagesURL = imagesURL;
    }

    // retrieves the name of the hotel
    public String getName() {
        return name;
    }

    // retrieves the location of the hotel
    public String getLocation() {
        return location;
    }

    // retrieves a string to describe hotel amenities
    public String getAmenities() {
        return String.join(", ", amenities);
    }

    // retrieves a list of rooms in the hotel
    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    // retrieves a description of hotel
    public String getDescription() {
        return description;
    }

    // books a room in hotel, for customer for specific dates
    public Booking bookHotel(Customer customer, Date checkIn, Date checkOut, Room roomChosen) {
        return new Booking(1, customer, roomChosen.getId(), this, checkIn, checkOut, roomChosen.getPrice(), "pending", "card");
    }

    public String getImagesURL() {   // retrieves the url to images of the hotel
        return imagesURL;
    }

    // gets list of available rooms for chosen dates
    public List<Room> getAvailableRooms(Date checkIn, Date checkOut) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.checkAvailability(checkIn) && room.checkAvailability(checkOut)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // retrieves a room by ID
    public Room getRoomByID(int id) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }


}
