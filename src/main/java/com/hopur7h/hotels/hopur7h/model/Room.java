package com.hopur7h.hotels.hopur7h.model;


import java.util.Date;
import java.util.List;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class Room {
    private Integer id;
    private List<Date> available;
    private Integer price;
    private Integer bedCount;
    private String imagesURL;

    // constructs a room object with following details
    public Room(Integer id, List<Date> available, Integer price, Integer bedCount, String imagesURL) {
        this.id = id;
        this.available = available;
        this.price = price;
        this.bedCount = bedCount;
        this.imagesURL = imagesURL;
    }

    // retrieves the id of the room
    public Integer getId() {
        return id;
    }

    // checks to see if room is available on a specific date
    public boolean checkAvailability(Date date) {
        return available.contains(date);
    }

    // retrieves the price of the room
    public Integer getPrice() {
        return price;
    }

    // retrieves number of beds in the room
    public Integer getBedCount() {
        return bedCount;
    }

    // retrieves the url for the images of the room
    public String getImagesURL() {
        return imagesURL;
    }

}
