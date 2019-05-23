package com.example.projecteng.crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projecteng.database.DatabaseConnector;
import com.example.projecteng.entity.Flashcard;

import java.util.ArrayList;
import java.util.List;

public class FlashcardCrud {

    private DatabaseConnector databaseConnector;

    public FlashcardCrud(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public List<Flashcard> findAll() {
        List<Flashcard> flashcards = new ArrayList<Flashcard>();
        SQLiteDatabase readDatabase = this.databaseConnector.getReadableDatabase();
        Cursor result = readDatabase.rawQuery("SELECT * FROM " + Flashcard.TABLE_NAME, null);

        result.moveToFirst();
        while (!result.isAfterLast()) {
            Long id = result.getLong(result.getColumnIndex("id"));
            String english = result.getString(result.getColumnIndex("english"));
            String polish = result.getString(result.getColumnIndex("polish"));

            flashcards.add(new Flashcard(id, english, polish));
        }

        result.close();
        return flashcards;
    }

    public Flashcard find(Long id) {
        SQLiteDatabase readDatabase = this.databaseConnector.getReadableDatabase();
        Cursor result = readDatabase.rawQuery("SELECT * FROM " + Flashcard.TABLE_NAME + " WHERE id = " + id, null);

        if (result.getCount() == 0) {
            return null;
        }

        result.moveToFirst();

        Long flashcardId = result.getLong(result.getColumnIndex("id"));
        String english = result.getString(result.getColumnIndex("english"));
        String polish = result.getString(result.getColumnIndex("polish"));

        result.close();
        return new Flashcard(flashcardId, english, polish);
    }

    public boolean create(Flashcard flashcard) {
        SQLiteDatabase writeDatabase = this.databaseConnector.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("english", flashcard.getEnglish());
        contentValues.put("polish", flashcard.getPolish());

        try {
            writeDatabase.insert(Flashcard.TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean update(Flashcard flashcard) {
        SQLiteDatabase writeDatabase = this.databaseConnector.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("english", flashcard.getEnglish());
        contentValues.put("polish", flashcard.getPolish());

        try {
            writeDatabase.update(Flashcard.TABLE_NAME, contentValues, "id = ?", new String[] { flashcard.getId().toString() });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete(Long id) {
        SQLiteDatabase writeDatabase = this.databaseConnector.getWritableDatabase();

        try {
            writeDatabase.delete(Flashcard.TABLE_NAME, "id = ?", new String[] { id.toString() });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
