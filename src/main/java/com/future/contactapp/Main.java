package com.future.contactapp;

import com.future.contactapp.persistance.ConnectionManager;
import com.future.contactapp.persistance.ContactBroker;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
/*
        System.out.println("Propertytest");
        Properties properties = new Properties();
        properties.put("ipv4", "192.168.20.10");
        // Speichern des eintrags
        try (OutputStream output = new FileOutputStream("config.properties")){
            properties.store(output, "Config");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            System.out.println(properties.getProperty("ipv4"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
        System.out.println("Hello Contact");


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


    }
}
