package com.future.contactapp.model;

import java.text.Collator;

public class Contact implements Comparable<Contact> {
    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private String emailAdditional;
    private String homepage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAdditional() {
        return emailAdditional;
    }

    public void setEmailAdditional(String emailAdditional) {
        this.emailAdditional = emailAdditional;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
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
