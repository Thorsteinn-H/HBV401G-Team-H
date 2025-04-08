package com.hopur7h.hotels.hopur7h.storage;

import com.hopur7h.hotels.hopur7h.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {
    /**
     * public CustomerDB() {
     * createTableIfNotExists();
     * }
     * <p>
     * private void createTableIfNotExists() {
     * String sql = "CREATE TABLE IF NOT EXISTS customers (" +
     * "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
     * "username TEXT UNIQUE NOT NULL, " +
     * "realName TEXT NOT NULL, " +
     * "password TEXT NOT NULL, " +
     * "email TEXT UNIQUE NOT NULL, " +
     * "phoneNumber TEXT)";
     * try (Connection conn = DatabaseConnector.connect();
     * Statement stmt = conn.createStatement()) {
     * stmt.execute(sql);
     * } catch (SQLException e) {
     * System.out.println("Failed to create table: " + e.getMessage());
     * }
     * }
     **/

    public void addCustomer(String username, String realName, String password, String email, String phoneNumber) {
        String sql = "INSERT INTO customers(username, realName, password, email, phoneNumber) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, realName);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5, phoneNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add failed: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            System.out.println("Read failed: " + e.getMessage());
        }
        return customers;
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSetToCustomer(rs);
        } catch (SQLException e) {
            System.out.println("Get by ID failed: " + e.getMessage());
        }
        return null;
    }

    public Customer getCustomerByUsername(String username) {
        String sql = "SELECT * FROM customers WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSetToCustomer(rs);
        } catch (SQLException e) {
            System.out.println("Get by username failed: " + e.getMessage());
        }
        return null;
    }

    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSetToCustomer(rs);
        } catch (SQLException e) {
            System.out.println("Get by email failed: " + e.getMessage());
        }
        return null;
    }

    public void deleteCustomerById(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Customer deleted." : "Customer not found.");
        } catch (SQLException e) {
            System.out.println("Delete by ID failed: " + e.getMessage());
        }
    }

    public void deleteCustomerByUsername(String username) {
        String sql = "DELETE FROM customers WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Customer deleted." : "Customer not found.");
        } catch (SQLException e) {
            System.out.println("Delete by username failed: " + e.getMessage());
        }
    }

    public void updateCustomerById(int id, String realName, String password, String email, String phoneNumber) {
        String sql = "UPDATE customers SET realName = ?, password = ?, email = ?, phoneNumber = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, realName);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phoneNumber);
            ps.setInt(5, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Customer updated." : "Customer not found.");
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("realName"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("phoneNumber")
        );
    }
}
