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

    public Room getRoom(Integer roomID) {
        return hotel.getRoomByID(roomID);
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

    public void cancelBooking() {
        status = "cancelled";
    }

    public void refund() {
        if(paymentMethod.equals("card")) {
            // refund through card
        } else if(paymentMethod.equals("cash")) {
            // refund through cash
        }
    }

    public void processPayment(String paymentMethod, Integer price) {
        if(paymentMethod.equals("card")) {
            // process payment through card
        } else if(paymentMethod.equals("cash")) {
            // process payment through cash
        }
    }

    public static Booking bookHotel(Customer customer, Date checkIn, Date checkOut, Room roomChosen) {
        return new Booking(1, customer, roomChosen.getId(), roomChosen.getHotel(), checkIn, checkOut, roomChosen.getPrice(), "pending", "card");
    }
}
