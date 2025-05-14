package com.future.contactapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button closeButton;

    @FXML
    public void closeButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
