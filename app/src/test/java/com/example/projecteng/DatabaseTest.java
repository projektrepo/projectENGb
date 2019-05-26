package com.example.projecteng;

import com.example.projecteng.database.DatabaseConnector;
import com.example.projecteng.entity.Flashcard;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseTest {

    private DatabaseConnector databaseConnector;

    public DatabaseTest() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    @Test
    public void selectTest() {
        try {
            List<Map<String, String>> resluts = this.databaseConnector.select(Flashcard.TABLE_NAME, null);

            for (Map<String, String> row : resluts) {
                Flashcard flashcard = new Flashcard(Long.parseLong(row.get("id")), row.get("english"), row.get("polish"));
                System.out.println(flashcard);
            }

            List<Map<String, String>> oneRow = this.databaseConnector.select(Flashcard.TABLE_NAME, "3");
            Map<String, String> row = oneRow.get(0);

            Flashcard flashcard = new Flashcard(Long.parseLong(row.get("id")), row.get("english"), row.get("polish"));
            System.out.println("\n" + flashcard);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void insertTest() {
        try {
            List<String> values = new ArrayList<>();

            values.add("NULL");
            values.add("'weapon'");
            values.add("'broń'");

            boolean queryResult = this.databaseConnector.insert(Flashcard.TABLE_NAME, values);
            Assert.assertTrue(queryResult);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void updateTest() {
        try {
            Map<String, String> values = new HashMap<>();

            values.put("english", "'house'");
            values.put("polish", "'dom (budynek)'");

            boolean queryResult = this.databaseConnector.update(Flashcard.TABLE_NAME, values, "8");
            Assert.assertTrue(queryResult);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void deleteTest() {
        try {
            boolean queryResult = this.databaseConnector.delete(Flashcard.TABLE_NAME, "15");
            Assert.assertTrue(queryResult);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
