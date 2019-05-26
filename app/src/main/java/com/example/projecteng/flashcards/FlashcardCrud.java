package com.example.projecteng.flashcards;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.projecteng.entity.Flashcard;

import java.util.ArrayList;
import java.util.List;

public class FlashcardCrud {

    private DatabaseHelper databaseHelper;

    public FlashcardCrud(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Flashcard> findAll() {
        SQLiteDatabase readable = this.databaseHelper.getReadableDatabase();

        Cursor result = readable.rawQuery("SELECT * FROM " + Flashcard.TABLE_NAME, null, null);
        result.moveToFirst();

        List<Flashcard> flashcards = new ArrayList<>();
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
        SQLiteDatabase readable = this.databaseHelper.getReadableDatabase();

        Cursor result = readable.rawQuery("SELECT * FROM " + Flashcard.TABLE_NAME + " WHERE id = ?", new String[] { id.toString()}, null );
        result.moveToFirst();

        if (result.getCount() < 1) {
            return null;
        }

        String english = result.getString(result.getColumnIndex("english"));
        String polish = result.getString(result.getColumnIndex("polish"));

        result.close();
        return new Flashcard(id, english, polish);
    }

    public boolean create(Flashcard flashcard) {
        SQLiteDatabase writable = this.databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("english", flashcard.getEnglish());
        values.put("polish", flashcard.getPolish());

        try {
            writable.insert(Flashcard.TABLE_NAME, null, values);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean update(Flashcard flashcard) {
        SQLiteDatabase writable = this.databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("english", flashcard.getEnglish());
        values.put("polish", flashcard.getPolish());

        try {
            writable.update(Flashcard.TABLE_NAME, values, "id = ?", new String[] { flashcard.getId().toString() });
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean delete(Long id) {
        SQLiteDatabase writable = this.databaseHelper.getWritableDatabase();

        try {
            writable.delete(Flashcard.TABLE_NAME, "id = ?", new String[] { id.toString() });
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
}
