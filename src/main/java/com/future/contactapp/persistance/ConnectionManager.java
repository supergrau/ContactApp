package com.future.contactapp.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Manages the connection, opens and closes it again
 */
public class ConnectionManager {
    private static final String FILE = "db.properties";
    private static Connection connection;

    private ConnectionManager() {
    }

    /**
     * Establishes a connection to the database
     *
     * @return connection to the database
     * @throws IOException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, SQLException {
        if (connection == null) {
            Properties prop = loadDbParam();
            connection = DriverManager.getConnection(prop.getProperty("url"),
                    prop.getProperty("user", ""),
                    prop.getProperty("password", ""));
        }
        return connection;
    }

    /**
     * closes the connection
     */
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
        }
    }

    /**
     * read file db.properties
     * @return properties (url only)
     * @throws IOException
     */
    private static Properties loadDbParam() throws IOException {
//        try (InputStream in = ConnectionManager.class.getResourceAsStream(FILE)) {
//        try (InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("com/future/contactapp/persistance/db.properties");) {
        try (InputStream in = new FileInputStream(FILE)) {
            Properties prop = new Properties();
            prop.load(in);
            return prop;
        }
    }
}
