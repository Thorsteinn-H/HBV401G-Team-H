package Model;

import java.util.Date;
import java.util.List;
/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class Room {



public class Room {
    private Integer id;
    private List<Date> available;
    private Integer price;
    private Integer bedCount;
    private String imagesURL;

    public Room(Integer id, List<Date> available, Integer price, Integer bedCount, String imagesURL) {
        this.id = id;
        this.available = available;
        this.price = price;
        this.bedCount = bedCount;
        this.imagesURL = imagesURL;
    }

    public boolean checkAvailability(Date date) {
        return available.contains(date);
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getBedCount() {
        return bedCount;
    }

    public String getImagesURL() {
        return imagesURL;
    }

}
