package com.future.contactapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class EditViewController {
    @FXML
    private TextArea textArea;


    public void setFields(int id, String lastname, String firstname, String email, String homepage) {
        textArea.setText(textArea.getText() +
                " id: " + id +
                " lastname: " + lastname +
                " firstname: " + firstname);
    }

}
