package com.future.contactapp;

import com.future.contactapp.controller.Controller;
import com.future.contactapp.persistance.ConnectionManager;
import com.future.contactapp.persistance.ContactBroker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("contact-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Controller controller = new Controller();

        controller.loadContacts();

        stage.setTitle("ContactApp");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();


/*
        try {
            Connection connection = ConnectionManager.getConnection();
            ContactBroker contactBroker = ContactBroker.getInstance();

            contactBroker.createTable();
            System.out.println("table created");
            ConnectionManager.closeConnection();
            System.out.println("Closed success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
*/


    }
}
