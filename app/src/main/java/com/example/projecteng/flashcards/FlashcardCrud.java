package com.example.projecteng.flashcards;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.projecteng.database.DatabaseConnector;
import com.example.projecteng.entity.Flashcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlashcardCrud {

    private DatabaseConnector databaseConnector;

    public FlashcardCrud() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    public List<Flashcard> findAll() {
        List<Flashcard> flashcards = new ArrayList<>();
        List<Map<String, String>> flashcardRows = this.databaseConnector.select(Flashcard.TABLE_NAME, null);

        try {
            for (Map<String, String> row : flashcardRows) {
                Long id = Long.parseLong(row.get("id"));
                String english = row.get("englsh");
                String polish = row.get("polish");

                flashcards.add(new Flashcard(id, english, polish));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return flashcards;
    }

    public Flashcard find(Long id) {
        List<Map<String, String>> rows = this.databaseConnector.select(Flashcard.TABLE_NAME, id.toString());

        if (rows.size() == 0) {
            return null;
        }

        String english = rows.get(0).get("english");
        String polish = rows.get(0).get("polish");

        return new Flashcard(id, english, polish);
    }

    public boolean create(Flashcard flashcard) {
        List<String> values = new ArrayList<>();

        values.add("null");
        values.add("'" + flashcard.getEnglish() + "'");
        values.add("'" + flashcard.getPolish() + "'");

        return this.databaseConnector.insert(Flashcard.TABLE_NAME, values);
    }

    public boolean update(Flashcard flashcard) {
        Map<String, String> values = new HashMap<>();

        values.put("english", flashcard.getEnglish());
        values.put("polish", flashcard.getPolish());

        return this.databaseConnector.update(Flashcard.TABLE_NAME, values, flashcard.getId().toString());
    }

    public boolean delete(Long id) {
        return this.databaseConnector.delete(Flashcard.TABLE_NAME, id.toString());
    }
}
