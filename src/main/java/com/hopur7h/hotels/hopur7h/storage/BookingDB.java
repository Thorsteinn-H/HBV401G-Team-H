package com.hopur7h.hotels.hopur7h.storage;

import com.hopur7h.hotels.hopur7h.model.Booking;
import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.model.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDB {

    private final CustomerDB customerDB = new CustomerDB();
    private final HotelDB hotelDB = new HotelDB();

    public void addBooking(Customer customer, Hotel hotel, int roomId, Date checkIn, Date checkOut, int price, String status, String paymentMethod) {
        String sql = "INSERT INTO bookings(customer_id, hotel_id, room_id, check_in_date, check_out_date, price, status, payment_method) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customer.getId());
            pstmt.setInt(2, hotel.getId());
            pstmt.setInt(3, roomId);
            pstmt.setDate(4, new java.sql.Date(checkIn.getTime()));
            pstmt.setDate(5, new java.sql.Date(checkOut.getTime()));
            pstmt.setInt(6, price);
            pstmt.setString(7, status);
            pstmt.setString(8, paymentMethod);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Add booking failed: " + e.getMessage());
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = customerDB.getCustomerById(rs.getInt("customer_id"));
                Hotel hotel = hotelDB.getHotelById(rs.getInt("hotel_id"));

                bookings.add(new Booking(
                        rs.getInt("id"),
                        customer,
                        rs.getInt("room_id"),
                        hotel,
                        rs.getDate("check_in_date"),
                        rs.getDate("check_out_date"),
                        rs.getInt("price"),
                        rs.getString("status"),
                        rs.getString("payment_method")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Get bookings failed: " + e.getMessage());
        }

        return bookings;
    }

    public Booking getBookingById(int id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = customerDB.getCustomerById(rs.getInt("customer_id"));
                Hotel hotel = hotelDB.getHotelById(rs.getInt("hotel_id"));

                return new Booking(
                        rs.getInt("id"),
                        customer,
                        rs.getInt("room_id"),
                        hotel,
                        rs.getDate("check_in_date"),
                        rs.getDate("check_out_date"),
                        rs.getInt("price"),
                        rs.getString("status"),
                        rs.getString("payment_method")
                );
            }

        } catch (SQLException e) {
            System.out.println("Get booking by ID failed: " + e.getMessage());
        }

        return null;
    }

    public void deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete booking failed: " + e.getMessage());
        }
    }
}
