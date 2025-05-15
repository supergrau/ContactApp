package com.future.contactapp;

import com.future.contactapp.model.Contact;
import com.future.contactapp.persistance.ContactBroker;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("view/contact-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //Controller controller = new Controller();

        // controller.loadContacts();

        stage.setTitle("ContactApp");
        stage.setScene(scene);

        /**
         * Test des Datenabrufs mit Hilfe der Klasse ContactBroker
         * Die List kann einer ObservableList mit Casting zugewiesen werden
         */
        try {
            System.out.println("vor Datenabruftest");
            ObservableList<Contact> olist = FXCollections.observableList(ContactBroker.getInstance().findAll());
            for (Contact c: olist) {
                System.out.println(c.getId() + " - " + c.getLastname());
            }
            System.out.println("nach Datenabruftest");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
