package com.example.projecteng.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatabaseConnector {

    public static DatabaseConnector databaseConnector = null;

    public static String DATABASE_HOST = "127.0.0.1:3306";
    public static String DATABASE_USER = "english_learning";
    public static String DATABASE_PASS = "english_learning_pass";
    public static String DATABASE_NAME = "english_learning";

    private String url;

    private DatabaseConnector() {
        this.url = "jdbc:mysql://" + DATABASE_HOST + "/" + DATABASE_NAME;
    }

    public static synchronized DatabaseConnector getInstance() {
        if (databaseConnector == null) {
            databaseConnector = new DatabaseConnector();
        }

        return databaseConnector;
    }

    public List<Map<String, String>> select(String table, String id) {
        String query = "SELECT * FROM " + table;

        if (id != null) {
            query += " WHERE id = " + id;
        }

        List<Map<String, String>> results = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(this.url, DATABASE_USER, DATABASE_PASS);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ResultSetMetaData meta = resultSet.getMetaData();
                Map<String, String> row = new HashMap<>();

                for (int i = 0; i < meta.getColumnCount(); i++) {
                    row.put(meta.getColumnName(i + 1), resultSet.getObject(i + 1).toString());
                }
                results.add(row);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return results;
    }

    public boolean insert(String table, Set<String> values) {
        String query = "INSERT INTO " + table + " VALUES (";

        for (String value : values) {
            query += value + ", ";
        }

        query = query.substring(0, query.lastIndexOf(",") + 1);
        query += ")";

        try {
            Connection connection = DriverManager.getConnection(this.url, DATABASE_USER, DATABASE_PASS);
            Statement statement = connection.createStatement();

            return statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(String table, Map<String, String> values, String id) {
        String query = "UPDATE " + table + " SET ";

        for (String key : values.keySet()) {
            query += key + " = " + values.get(key) + ", ";
        }

        query = query.substring(0, query.lastIndexOf(",") + 1);

        if (id != null) {
            query = " WHERE id = " + id;
        }

        try {
            Connection connection = DriverManager.getConnection(this.url, DATABASE_USER, DATABASE_PASS);
            Statement statement = connection.createStatement();

            return statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String table, String id) {
        String query = "DELETE FROM " + table;

        if (id != null) {
            query += " WHERE id = " + id;
        }

        try {
            Connection connection = DriverManager.getConnection(this.url, DATABASE_USER, DATABASE_PASS);
            Statement statement = connection.createStatement();

            return statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
