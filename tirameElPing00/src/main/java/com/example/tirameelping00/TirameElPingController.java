package com.example.tirameelping00;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TirameElPingController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}