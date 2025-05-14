package com.future.contactapp.persistance;

import com.future.contactapp.model.Contact;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//import de.gkjava.addr.model.Contact;

public class ContactBroker extends Broker<Contact> {
    private static ContactBroker instance;

    private ContactBroker() {
    }

    public static ContactBroker getInstance() {
        if (instance == null)
            instance = new ContactBroker();
        return instance;
    }

    protected Contact makeObject(ResultSet rs) throws SQLException {
        Contact contact = new Contact(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6)
        );
        return contact;
    }

    // Alle Adressen holen
    public List<Contact> findAll() throws Exception {
        return query("select * from Contact order by lastname, firstname");
    }

    // Eine neue Adresse speichern mit Rückgabe des generierten Schlüssels
    public int insert(Contact a) throws Exception {
        return insertAndReturnKey("insert into Contact "
                + "(lastname, firstname, email, email_additional, homepage) values ('"
                + a.getLastname() + "','" + a.getFirstname() + "','"
                + a.getEmail() + "','" + a.getEmailAdditional() + "','"
                + a.getHomepage() + "')");
    }

    // Eine Adresse ändern
    public void update(Contact a) throws Exception {
        update("update Contact set " + "lastname = '" + a.getLastname()
                + "', firstname = '" + a.getFirstname() + "', email = '"
                + a.getEmail() + "', email_additional = '"
                + a.getEmailAdditional() + "', homepage = '" + a.getHomepage()
                + "' where id = " + a.getId());
    }

    // Eine Adresse löschen
    public void delete(int id) throws Exception {
        update("delete from Contact where id = " + id);
    }

    // Tabelle erstellen
    public void createTable() throws Exception {
        update("create table if not exists Contact ("
                + "id integer not null primary key, "
                + "lastname varchar(40), " + "firstname varchar(40), "
                + "email varchar(40), " + "email_additional varchar(40), "
                + "homepage varchar(60))");
    }
}
