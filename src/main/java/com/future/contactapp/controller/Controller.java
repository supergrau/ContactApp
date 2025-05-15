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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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

    /**
     * Contacs in das TableView laden
     *
     * @param statement mit der bestehenden Verbindung zur Datenbank
     */
    public void loadContacts(Statement statement) {
        try {
            contacts = model.loadContacts(contacts);
            statement.close();
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

    @FXML
    void contactTableViewClicked(MouseEvent event) {
        int index = contactTableView.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        System.out.println("Mouse: " + contactTableView.getItems().get(index).getLastname());
    }

    @FXML
    void tableViewKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.SPACE) {
            int index = contactTableView.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            System.out.println("Key: " + contactTableView.getItems().get(index).getLastname());
        }
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
