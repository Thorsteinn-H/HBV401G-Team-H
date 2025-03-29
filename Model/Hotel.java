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

    public Hotel(String name, String location, List<String> amenities, String description, List<Room> rooms, String imagesURL) {
        this.name = name;
        this.location = location;
        this.amenities = amenities;
        this.description = description;
        this.rooms = rooms;
        this.imagesURL = imagesURL;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAmenities() {
        return String.join(", ", amenities);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public String getDescription() {
        return description;
    }

    public Booking bookHotel(Customer customer, Date checkIn, Date checkOut, Room roomChosen) {
        return new Booking(customer, this, checkIn, checkOut, roomChosen);
    }

    public String getImagesURL() {
        return imagesURL;
    }

    public void cancelBooking(Booking booking) {
        rooms.add(booking.getRoomChosen());
    }

    public List<Room> getAvailableRooms(Date checkIn, Date checkOut) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.checkAvailability(checkIn) && room.checkAvailability(checkOut)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomByID(int id) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }


}