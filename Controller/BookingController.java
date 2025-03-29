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

    public Booking createNewBooking(Customer customer, Date checkIn, Date checkOut, Hotel hotel, Room roomChosen) {
        Booking newBooking = Booking.bookHotel(customer, checkIn, checkOut, hotel, roomChosen);
        bookings.add(newBooking);
        return newBooking;
    }


    public boolean removeBooking(Booking booking) {
        return bookings.remove(booking);
    }

    public Booking getBooking(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null; // or throw an exception if not found
    }
}


