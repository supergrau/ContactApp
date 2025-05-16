package com.future.contactapp.controller;

import com.future.contactapp.Main;
import com.future.contactapp.model.Contact;
import com.future.contactapp.model.Model;
import com.future.contactapp.persistance.ConnectionManager;
import com.future.contactapp.persistance.ContactBroker;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchPoint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    private Model model = new Model();
    private ObservableList<Contact> contacts;

    @FXML
    private Button deleteButton;

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
     */
    public void loadContacts() {
        try {
            contacts = model.loadContacts(contacts);
            ConnectionManager.closeConnection();
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
        if (event.getClickCount() == 2) {
            int index = contactTableView.getSelectionModel().getSelectedIndex();
            Contact contact = contactTableView.getSelectionModel().getSelectedItem();
//            String lastName = contactTableView.getItems().get(index).getLastname();
//            System.out.println("Mouse: index = " + index + " lastName" + contact.getLastname());

            // Aufbau des Dialoges edit-view:
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/edit-view.fxml"));
//        loader.setLocation(Controller.class.getResource("../view/edit-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            }

            EditViewController editViewController = loader.getController();
            editViewController.setFields(
                    contact.getId(),
                    contact.getLastname(),
                    contact.getFirstname(),
                    contact.getEmail(),
                    contact.getHomepage());

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());

            editViewController.edit = true;
            stage.showAndWait();

            // lade Contakte neu:
//        try {
//            contacts = model.loadContacts(contacts);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
            if (editViewController.save) {
                System.out.println("Vor neu laden");
                loadContacts();
                System.out.println("contacts:");
                for (Contact c : contacts) {
                    System.out.println(c.getLastname());
                }
                System.out.println("TableView:");
                for (Contact c : contactTableView.getItems()) {

                    System.out.println(c.getLastname());
                }
            }
            //contactTableView.setItems(contacts);
            //       stage.close();
        }
    }

    @FXML
    void tableViewKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            int index = contactTableView.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            System.out.println("Key: " + contactTableView.getItems().get(index).getLastname());
        }
    }

    @FXML
    void deleteButtonClick(ActionEvent event) {
        // Aktuellen Eintrag lesen und id ermitteln
        Contact contact = contactTableView.getSelectionModel().getSelectedItem();
        if(contact == null) return;
        int id = contact.getId();

        // Datensatz löschen:
        try {
            ContactBroker.getInstance().delete(id);
            loadContacts();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//            Connection connection = ConnectionManager.getConnection();
//            Statement statement = connection.createStatement();
        loadContacts();
        // den ersten Eintrag der Liste auswählen:

    }

    @FXML
    public void insertButtonClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/edit-view.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }

        EditViewController editViewController = loader.getController();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());

        editViewController.edit = false;
        stage.showAndWait();

        if (editViewController.save) {
            System.out.println("Vor neu laden");
            loadContacts();
            System.out.println("contacts:");
            for (Contact c : contacts) {
                System.out.println(c.getLastname());
            }
            System.out.println("TableView:");
            for (Contact c : contactTableView.getItems()) {

                System.out.println(c.getLastname());
            }
        }


    }
}
