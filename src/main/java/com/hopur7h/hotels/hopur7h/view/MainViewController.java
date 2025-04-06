package com.hopur7h.hotels.hopur7h.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainViewController {

    @FXML
    private TextField nameField;

    @FXML
    private void handleSubmitAction() {
        String name = nameField.getText();
        System.out.println("Submitted name: " + name);
    }
}
