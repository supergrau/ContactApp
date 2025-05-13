package com.future.contactapp.persistance;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Broker<T> {
    // Diese Methode muss von Subklassen implementieren werden
    protected abstract T makeObject(ResultSet rs) throws SQLException;

    // Select
    protected List<T> query(String sql) throws IOException, SQLException {
        try (Statement stmt = ConnectionManager.getConnection()
                .createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            List<T> result = new ArrayList<T>();
            while (rs.next()) {
                result.add(makeObject(rs));
            }

            return result;
        }
    }

    // Update, Insert oder Delete
    protected int update(String sql) throws IOException, SQLException {
        try (Statement stmt = ConnectionManager.getConnection()
                .createStatement()) {

            int count = stmt.executeUpdate(sql);
            return count;
        }
    }

    // Insert mit Rückgabe des automatisch erzeugten Schlüssels
    protected int insertAndReturnKey(String sql) throws IOException,
            SQLException {

        try (Statement stmt = ConnectionManager.getConnection()
                .createStatement()) {

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return id;
        }
    }

}
