package com.future.contactapp.controller;

import com.future.contactapp.model.Contact;
import com.future.contactapp.persistance.ContactBroker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditViewController implements Initializable {

    public boolean save = false;
    public boolean edit = true;
    @FXML
    private Button cancelButton;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label firstnameLabel;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private Label homePageLabel;

    @FXML
    private TextField homepageTextField;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTextField;

    @FXML
    private Label lastnameLabel;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private Button saveButton;


    @FXML
    void buttonCancelClick(ActionEvent event) {
        // Hier das Fenster schließen
        ((Stage) (saveButton.getScene().getWindow())).close();
    }

    @FXML
    void buttonSaveClick(ActionEvent event) {
        System.out.println("Enter");

        Alert yes_or_no = new Alert(Alert.AlertType.CONFIRMATION);
        yes_or_no.setContentText("Wirklich speichern?");
        yes_or_no.setTitle("ContactApp");
        Optional<ButtonType> result = yes_or_no.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            Contact contact = new Contact(
                    Integer.parseInt(idTextField.getText()),
                    lastnameTextField.getText(),
                    firstnameTextField.getText(),
                    emailTextField.getText(),
                    "-",
                    homepageTextField.getText()
            );

            try {
                if (edit)
                    ContactBroker.getInstance().update(contact);
                else
                    ContactBroker.getInstance().insert(contact);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            // Hier das Fenster schließen
            save = true;
            ((Stage) (saveButton.getScene().getWindow())).close();


        }

    }

    public void setFields(int id, String lastname, String firstname, String email, String homepage) {
        idTextField.setText(Integer.toString(id));
        lastnameTextField.setText(lastname);
        firstnameTextField.setText(firstname);
        emailTextField.setText(email);
        homepageTextField.setText(homepage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idLabel.setMnemonicParsing(true);
        lastnameLabel.setMnemonicParsing(true);
        firstnameLabel.setMnemonicParsing(true);
        emailLabel.setMnemonicParsing(true);
        homePageLabel.setMnemonicParsing(true);

        idLabel.setLabelFor(idTextField);
        lastnameLabel.setLabelFor(lastnameTextField);
        firstnameLabel.setLabelFor(firstnameTextField);
        emailLabel.setLabelFor(emailTextField);
        homePageLabel.setLabelFor(homepageTextField);


    }


}
