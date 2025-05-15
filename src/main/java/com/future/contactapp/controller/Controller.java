package com.future.contactapp.controller;

import com.future.contactapp.model.Contact;
import com.future.contactapp.model.Model;
import com.future.contactapp.persistance.ConnectionManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Model model = new Model();
    private ObservableList<Contact> contacts;

    @FXML
    private TableView<Contact> contactTableView;

    @FXML
    private TableColumn<Contact, String> emailColumn;

    @FXML
    private TableColumn<Contact, String> firstnameColumn;

    @FXML
    private TableColumn<Contact, String> homepageColumn;

    @FXML
    private TableColumn<Contact, Integer> idColumn;

    @FXML
    private TableColumn<Contact, String> lastnameColumn;

    @FXML
    private Button closeButton;

    @FXML
    public void closeButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }
    public void loadContacts(Statement statement) {
        try {
            contacts = model.loadContacts(statement, contacts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        homepageColumn.setCellValueFactory(new PropertyValueFactory<>("homepage"));

        contactTableView.setItems(contacts);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            loadContacts(statement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
