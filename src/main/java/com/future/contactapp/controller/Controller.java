package com.future.contactapp.controller;

import com.future.contactapp.model.Contact;
import com.future.contactapp.model.Model;
import com.future.contactapp.persistance.ConnectionManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    private Model model = new Model();
    private ObservableList<Contact> contacts;

    public void loadContacts() {
        try {
            contacts = model.loadContacts(ConnectionManager.getConnection().createStatement(), contacts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("id"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastname"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("email"));
        homepageColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("homepage"));

        contactTableView.setItems(contacts);
    }
    @FXML
    private TableView<Contact> contactTableView;

    @FXML
    private TableColumn<Contact, String> emailColumn;

    @FXML
    private TableColumn<Contact, String> firstnameColumn;

    @FXML
    private TableColumn<Contact, String> homepageColumn;

    @FXML
    private TableColumn<Contact, String> idColumn;

    @FXML
    private TableColumn<Contact, String> lastnameColumn;

    @FXML
    private Button closeButton;

    @FXML
    public void closeButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
