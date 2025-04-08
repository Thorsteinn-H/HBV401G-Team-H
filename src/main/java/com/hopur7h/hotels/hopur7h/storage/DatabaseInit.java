package com.hopur7h.hotels.hopur7h.storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInit {

    public static void initDatabase() {
        try (Connection conn = DatabaseConnector.connect(); Statement statement = conn.createStatement()) {

            // Create Customer table
            statement.execute("""
                        CREATE TABLE IF NOT EXISTS customers (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            email TEXT UNIQUE NOT NULL
                        );
                    """);

            // Create Hotel table
            statement.execute("""
                        CREATE TABLE IF NOT EXISTS hotels (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            location TEXT NOT NULL,
                            amenities TEXT,
                            description TEXT,
                            imagesURL TEXT
                        );
                    """);

            // Create Booking table
            statement.execute("""
                        CREATE TABLE IF NOT EXISTS bookings (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            customer_id INTEGER NOT NULL,
                            hotel_id INTEGER NOT NULL,
                            room_number TEXT NOT NULL,
                            check_in_date TEXT NOT NULL,
                            check_out_date TEXT NOT NULL,
                            FOREIGN KEY (customer_id) REFERENCES customers(id),
                            FOREIGN KEY (hotel_id) REFERENCES hotels(id)
                        );
                    """);

            System.out.println("SQLite tables created successfully.");

        } catch (SQLException e) {
            System.out.println("Database initialization failed: " + e.getMessage());
        }
    }
}
