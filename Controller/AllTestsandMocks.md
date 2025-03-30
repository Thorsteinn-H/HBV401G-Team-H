# Text figure and mock object documentation

This documentation shows an overview of the tests and mock components implemented for the hotel booking system.

# Controller

## Booking controller.java 
```
package Controller;

import Model.Booking;
import Model.Customer;
import Model.Hotel;
import Model.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class BookingController {

    private List<Booking> bookings = new ArrayList<>();

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    // creates new booking based on given details
    public Booking createNewBooking(Customer customer, Date checkIn, Date checkOut, Hotel hotel, Room roomChosen) {
        Booking newBooking = Booking.bookHotel(customer, checkIn, checkOut, hotel, roomChosen);
        bookings.add(newBooking);
        return newBooking;
    }

    // removes specified booking from the list
    public boolean removeBooking(Booking booking) {
        return bookings.remove(booking);
    }

    // searches for a booking by booking id
    public Booking getBooking(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null; // or throw an exception if not found
    }
}
 ```

## CustomerController.java 
```
package Controller;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/

import Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    // retrieves a customer based on username
    public Customer getCustomer(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null; // or throw an exception if not found
    }

    // retrieves all customers managed by this controller
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    // adds costumer to the list
    public void addCustomer(Customer customer) {
        customers.add(customer);

    }

    // removes customer from the list
    public void removeCustomer(String username) {
        customers.removeIf(customer -> customer.getUsername().equals(username));
    }


}
 ```

## HotelController.java 
```
package Controller;

import Model.Hotel;

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
```
## HotelController.java 
```
package Controller;

import Model.Hotel;

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
 ```

# Model 

## Booking.java
```
package Model;

import java.util.Date;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class Booking {


    private Integer id;
    private Customer customer;
    private Integer room;
    private Hotel hotel;
    private Date checkIn;
    private Date checkOut;
    private Integer price;
    private String status;
    private String paymentMethod;

    // builds a new booking objects based on following details
    public Booking(Integer id, Customer customer, Integer room, Hotel hotel, Date checkIn, Date checkOut, Integer price, String status, String paymentMethod) {
        this.id = id;
        this.customer = customer;
        this.room = room;
        this.hotel = hotel;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    // retrieves the room object connected with this booking
    public Room getRoom() {
        return hotel.getRoomByID(room);  // gets room from hotel by id
    }


    public Hotel getHotel() {
        return hotel;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public Integer getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // cancels this booking
    public void cancelBooking() {
        status = "cancelled";
    }

    // processes a refund based on the payment method used
    public void refund() {
        if (paymentMethod.equals("card")) {
            // refund through card
        } else if (paymentMethod.equals("cash")) {
            // refund through cash
        }
    }

    // process payment for this booking
    public void processPayment(String paymentMethod, Integer price) {
        if (paymentMethod.equals("card")) {
            // process payment through card
        } else if (paymentMethod.equals("cash")) {
            // process payment through cash
        }
    }

    // method to create and return a new booking
    public static Booking bookHotel(Customer customer, Date checkIn, Date checkOut, Hotel hotel, Room roomChosen) {
        return new Booking(1, customer, roomChosen.getId(), hotel, checkIn, checkOut, roomChosen.getPrice(), "pending", "card");
    }


}
 ```

## Customer.java
```
package Model;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class Customer {


    private String username;
    private String realName;
    private String password;
    private String email;
    private String phoneNumber;

    // constructs a new customer object with details
    public Customer(String username, String realName, String password, String email, String phoneNumber) {
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // retrieves username of the customer
    public String getUsername() {
        return username;
    }

    // retrieves real name of the customer
    public String getName() {
        return realName;
    }

    // retrieves the email address of the customer
    public String getEmail() {
        return email;
    }

    // retrieves the phone number of the customer
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // checks if given input matches the customer passwords
    public boolean verifyPassword(String input) {
        return password.equals(input);
    }

}
 ```

## Hotel.java
```
package Model;

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
```

## Room.java
```
package Model;

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
 ```


## TestCases.java
```
package Model;

import Controller.CustomerController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing: Later
 **/
public class TestCases {

    private CustomerController customerController;
    private Customer customer;


    @BeforeEach // sets up ojects before every test
    public void setUp() {
        customerController = new CustomerController();
        customer = new Customer("username", "realName", "password", "email", "phoneNumber");
    }

    @AfterEach  // reset after each test
    public void tearDown() {
        customerController = null;
        customer = null;
    }

    @Test   // test adding a costumer
    public void testAddCustomer() {
        customerController.addCustomer(customer);
        Customer testCustomer = customerController.getCustomer(customer.getUsername());
        assertEquals(customer, testCustomer);
    }

    @Test  // test removing a costumer
    public void testRemoveCustomer() {
        //add customer from fresh plate (assumes previous test works and input customerController is empty)
        customerController.addCustomer(customer);
        Customer testCustomer = customerController.getCustomer(customer.getUsername());

        //Removes based on previous getcustomer
        customerController.removeCustomer(testCustomer.getUsername());
        List<Customer> listCustomers = customerController.getAllCustomers();
        assertTrue(listCustomers.isEmpty());

    }

}
```

# Storage

## mockCustomerDB.java
```
package Storage;

import Model.Customer;

import java.util.ArrayList;
import java.util.List;


public class mockCustomerDB {

    private List<Customer> customers; // list to hold all customer objects in memory

    public mockCustomerDB() {
        this.customers = new ArrayList<>(); // initalizes the list of customers
    }

    // adds a new customer to the list
    public void insert(Customer customer) {
        customers.add(customer);
    }

    // deletes a customer from list based on username
    public void delete(String username) {
        customers.removeIf(customer -> customer.getUsername().equals(username));
    }

    // selects customer based on username gives back list of matched customers
    public List<Customer> select(String username) {
        if (username == null || username.isEmpty()) {
            return new ArrayList<>(customers);  // returns all customer if no filter is applied
        }
        List<Customer> matched = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getUsername().contains(username)) {
                matched.add(customer);  // adds customer if they match filter
            }
        }
        return matched;
    }

}
 ```



