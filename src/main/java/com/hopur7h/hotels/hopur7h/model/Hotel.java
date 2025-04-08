package com.hopur7h.hotels.hopur7h.model;

import com.hopur7h.hotels.hopur7h.storage.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    private String imagesURL;
    private int id;

    private Map<Date, Integer> roomAvailability = new HashMap<>();
    private final int MAX_ROOMS_PER_DAY = 5;

    public Hotel(String name, String location, List<String> amenities, String description, String imagesURL) {
        this.name = name;
        this.location = location;
        this.amenities = amenities;
        this.description = description;
        this.imagesURL = imagesURL;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public String getAmenities() {
        return String.join(", ", amenities);
    }

    public String getDescription() {
        return description;
    }

    public String getImagesURL() {
        return imagesURL;
    }


    public boolean isAvailable(Date checkIn, Date checkOut) {
        try (Connection conn = DatabaseConnector.connect()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkIn);
            while (!calendar.getTime().after(checkOut)) {
                Date date = truncateTime(calendar.getTime());
                String sql = "SELECT COUNT(*) FROM bookings WHERE hotel_id = ? AND ? BETWEEN check_in_date AND check_out_date";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ps.setDate(2, new java.sql.Date(date.getTime()));
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next() && rs.getInt(1) >= MAX_ROOMS_PER_DAY) {
                            return false;
                        }
                    }
                }
                calendar.add(Calendar.DATE, 1);
            }
        } catch (SQLException e) {
            System.out.println("Availability check failed: " + e.getMessage());
        }
        return true;
    }


    public List<Date> getUnavailableDates(Date checkIn, Date checkOut) {
        List<Date> unavailable = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);
        while (!calendar.getTime().after(checkOut)) {
            Date date = truncateTime(calendar.getTime());
            if (roomAvailability.getOrDefault(date, 0) >= MAX_ROOMS_PER_DAY) {
                unavailable.add(date);
            }
            calendar.add(Calendar.DATE, 1);
        }
        return unavailable;
    }

    public void bookDates(Date checkIn, Date checkOut) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);
        while (!calendar.getTime().after(checkOut)) {
            Date date = truncateTime(calendar.getTime());
            int booked = roomAvailability.getOrDefault(date, 0);
            roomAvailability.put(date, booked + 1);
            calendar.add(Calendar.DATE, 1);
        }
    }

    private Date truncateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // Simplified booking logic
    public Booking bookHotel(Customer customer, Date checkIn, Date checkOut) {
        if (!isAvailable(checkIn, checkOut)) {
            throw new IllegalStateException("Some dates are fully booked.");
        }
        bookDates(checkIn, checkOut);
        return new Booking(1, customer, -1, this, checkIn, checkOut, "pending", "card");
    }
}
