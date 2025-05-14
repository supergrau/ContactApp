package com.future.contactapp.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.Collator;

public class Contact implements Comparable<Contact> {
    private SimpleIntegerProperty id;
    private SimpleStringProperty lastname;
    private SimpleStringProperty firstname;
    private SimpleStringProperty email;
    private SimpleStringProperty emailAdditional;
    private SimpleStringProperty homepage;

    public Contact(int id, String lastname, String firstname, String email, String emailAdditional, String homepage ) {
        this.id = new SimpleIntegerProperty(id);
        this.lastname = new SimpleStringProperty(lastname);
        this.firstname = new SimpleStringProperty(firstname);
        this.email = new SimpleStringProperty(email);
        this.emailAdditional = new SimpleStringProperty(emailAdditional);
        this.homepage = new SimpleStringProperty(homepage);
    }

    public int getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(SimpleStringProperty lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(SimpleStringProperty firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public String getEmailAdditional() {
        return emailAdditional.get();
    }

    public void setEmailAdditional(SimpleStringProperty emailAdditional) {
        this.emailAdditional = emailAdditional;
    }

    public String getHomepage() {
        return homepage.get();
    }

    public void setHomepage(SimpleStringProperty homepage) {
        this.homepage = homepage;
    }

    @Override
    public int compareTo(Contact contact) {
        Collator collator = Collator.getInstance();
        String s1 = lastname + ", " + firstname;
        String s2 = contact.getLastname() + ", " + contact.getFirstname();
        return collator.compare(s1, s2);
    }
}
