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

    public Hotel getHotel(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                return hotel;
            }
        }
        return null;
    }

    public List<Hotel> searchHotels(String string) {
        List<Hotel> matching = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getName().contains(string) || hotel.getLocation().contains(string)) {
                matching.add(hotel);
            }
        }
        return matching;
    }

    public List<Hotel> getAllHotels() {
        return new ArrayList<>(hotels);
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void removeHotel(String name) {
        hotels.removeIf(hotel -> hotel.getName().equals(name));
    }

}
