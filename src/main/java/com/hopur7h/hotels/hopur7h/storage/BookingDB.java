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

    public int addBooking(Customer customer, Hotel hotel, Date checkIn, Date checkOut, String status, String paymentMethod) {
        String sql = "INSERT INTO bookings(customer_id, hotel_id, check_in_date, check_out_date, status, payment_method) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customer.getId());
            ps.setInt(2, hotel.getId());
            ps.setDate(3, new java.sql.Date(checkIn.getTime()));
            ps.setDate(4, new java.sql.Date(checkOut.getTime()));
            ps.setString(5, status);
            ps.setString(6, paymentMethod);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating booking failed, no rows affected.");
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve booking ID.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Add booking failed: " + e.getMessage());
        }

        return -1;
    }


    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                bookings.add(mapResultSetToBooking(rs));
            }

        } catch (SQLException e) {
            System.out.println("Get bookings failed: " + e.getMessage());
        }

        return bookings;
    }

    public Booking getBookingById(int id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSetToBooking(rs);

        } catch (SQLException e) {
            System.out.println("Get booking by ID failed: " + e.getMessage());
        }

        return null;
    }

    public List<Booking> getBookingsByCustomerId(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customer_id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bookings.add(mapResultSetToBooking(rs));
            }

        } catch (SQLException e) {
            System.out.println("Get bookings by customer ID failed: " + e.getMessage());
        }

        return bookings;
    }

    public void updateBookingStatus(int bookingId, String newStatus) {
        String sql = "UPDATE bookings SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, bookingId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update booking status failed: " + e.getMessage());
        }
    }

    public void deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete booking failed: " + e.getMessage());
        }
    }

    public void deleteBookingsByCustomerId(int customerId) {
        String sql = "DELETE FROM bookings WHERE customer_id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete bookings by customer ID failed: " + e.getMessage());
        }
    }

    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        Customer customer = customerDB.getCustomerById(rs.getInt("customer_id"));
        Hotel hotel = hotelDB.getHotelById(rs.getInt("hotel_id"));

        return new Booking(
                rs.getInt("id"),
                customer,
                hotel,
                rs.getDate("check_in_date"),
                rs.getDate("check_out_date"),
                rs.getString("status"),
                rs.getString("payment_method")
        );
    }
}
