module com.hopur7h.hotels.hopur7h {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hopur7h.hotels.hopur7h to javafx.fxml;
    opens com.hopur7h.hotels.hopur7h.view to javafx.fxml;

    exports com.hopur7h.hotels.hopur7h;
    exports com.hopur7h.hotels.hopur7h.view;
}
