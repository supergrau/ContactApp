package com.future.contactapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {

    /**
     * Lädt die Kontakte aus der Datenbank
     *
     * @param statement
     * @param contacts
     * @return ObservableList von Typ Contact
     */
    public ObservableList<Contact> loadContacts(Statement statement, ObservableList<Contact> contacts) throws SQLException {
        contacts = FXCollections.observableArrayList();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM contact");

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String lastname = resultSet.getString(2);
            String firstname = resultSet.getString(3);
            String email = resultSet.getString(4);
            String emailAdditional = resultSet.getString(5);
            String homepage = resultSet.getString(6);
            System.out.println(id + " " + "lastname");
            contacts.add(new Contact(id, lastname, firstname, email, emailAdditional, homepage));
        }
        contacts.add(new Contact(111,"Müller", "Martin", "mueller@gmail.com", "-","https://mueller.de"));
        contacts.add(new Contact(112,"Lehmann", "Lutz", "lehmann@gmail.com", "-","https://lehmann.de"));
        return contacts;
    }
}
