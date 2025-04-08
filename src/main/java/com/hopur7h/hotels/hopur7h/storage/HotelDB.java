package com.hopur7h.hotels.hopur7h.storage;

import com.hopur7h.hotels.hopur7h.model.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelDB {

    public void addHotel(String name, String location, List<String> amenities, String description, String imagesURL) {
        String sql = "INSERT INTO hotels(name, location, amenities, description, imagesURL) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setString(3, String.join(",", amenities));
            ps.setString(4, description);
            ps.setString(5, imagesURL);
            ps.executeUpdate();
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
                hotels.add(mapResultSetToHotel(rs));
            }

        } catch (SQLException e) {
            System.out.println("Get hotels failed: " + e.getMessage());
        }

        return hotels;
    }

    public Hotel getHotelById(int id) {
        String sql = "SELECT * FROM hotels WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToHotel(rs);
            }
        } catch (SQLException e) {
            System.out.println("Get hotel by ID failed: " + e.getMessage());
        }
        return null;
    }

    public void deleteHotel(int id) {
        String sql = "DELETE FROM hotels WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete hotel failed: " + e.getMessage());
        }
    }

    public void updateHotel(int id, String name, String location, List<String> amenities, String description, String imagesURL) {
        String sql = "UPDATE hotels SET name = ?, location = ?, amenities = ?, description = ?, imagesURL = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setString(3, String.join(",", amenities));
            ps.setString(4, description);
            ps.setString(5, imagesURL);
            ps.setInt(6, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Hotel updated." : "Hotel not found.");
        } catch (SQLException e) {
            System.out.println("Update hotel failed: " + e.getMessage());
        }
    }

    public List<Hotel> getHotelsByName(String name) {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotels WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hotels.add(mapResultSetToHotel(rs));
            }
        } catch (SQLException e) {
            System.out.println("Search hotel by name failed: " + e.getMessage());
        }

        return hotels;
    }

    public void deleteAllHotels() {
        String sql = "DELETE FROM hotels";
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Delete all hotels failed: " + e.getMessage());
        }
    }

    private Hotel mapResultSetToHotel(ResultSet rs) throws SQLException {
        Hotel hotel = new Hotel(
                rs.getString("name"),
                rs.getString("location"),
                Arrays.asList(rs.getString("amenities").split(",")),
                rs.getString("description"),
                rs.getString("imagesURL")
        );
        hotel.setId(rs.getInt("id"));
        return hotel;
    }
}
