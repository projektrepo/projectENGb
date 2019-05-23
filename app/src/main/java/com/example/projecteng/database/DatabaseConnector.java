package com.example.projecteng.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "project_eng.db";
    public static final String FLASHCARDS = "flashcards";

    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + FLASHCARDS + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "english VARCHAR(255), " +
                        "polish VARCHAR(255));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FLASHCARDS + ";");
    }
}