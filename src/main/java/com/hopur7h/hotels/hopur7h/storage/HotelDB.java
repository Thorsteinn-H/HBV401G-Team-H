package com.hopur7h.hotels.hopur7h.storage;

import com.hopur7h.hotels.hopur7h.model.Hotel;
import com.hopur7h.hotels.hopur7h.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelDB {

    public void addHotel(String name, String location, List<String> amenities, String description, String imagesURL) {
        String sql = "INSERT INTO hotels(name, location, amenities, description, imagesURL) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.setString(3, String.join(",", amenities));
            pstmt.setString(4, description);
            pstmt.setString(5, imagesURL);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add hotel failed: " + e.getMessage());
        }
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotels";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getString("name"),
                        rs.getString("location"),
                        Arrays.asList(rs.getString("amenities").split(",")),
                        rs.getString("description"),
                        new ArrayList<Room>(),
                        rs.getString("imagesURL")
                );
                hotel.setId(rs.getInt("id"));
                hotels.add(hotel);
            }

        } catch (SQLException e) {
            System.out.println("Get hotels failed: " + e.getMessage());
        }

        return hotels;
    }

    public Hotel getHotelById(int id) {
        String sql = "SELECT * FROM hotels WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getString("name"),
                        rs.getString("location"),
                        Arrays.asList(rs.getString("amenities").split(",")),
                        rs.getString("description"),
                        new ArrayList<Room>(),
                        rs.getString("imagesURL")
                );
                hotel.setId(rs.getInt("id"));
                return hotel;
            }
        } catch (SQLException e) {
            System.out.println("Get hotel by ID failed: " + e.getMessage());
        }
        return null;
    }

    public void deleteHotel(int id) {
        String sql = "DELETE FROM hotels WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete hotel failed: " + e.getMessage());
        }
    }

    public Hotel getHotelWithRoomsById(int id) {
        String sql = "SELECT * FROM hotels WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getString("name"),
                        rs.getString("location"),
                        Arrays.asList(rs.getString("amenities").split(",")),
                        rs.getString("description"),
                        new ArrayList<Room>(), // TODO: fetch actual rooms
                        rs.getString("imagesURL")
                );
                hotel.setId(rs.getInt("id"));
                return hotel;
            }
        } catch (SQLException e) {
            System.out.println("Get hotel with rooms failed: " + e.getMessage());
        }
        return null;
    }
}
