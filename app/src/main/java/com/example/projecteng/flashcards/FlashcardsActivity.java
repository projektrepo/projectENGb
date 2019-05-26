package com.example.projecteng.flashcards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.projecteng.R;
import com.example.projecteng.entity.Flashcard;

import java.util.List;

public class FlashcardsActivity extends AppCompatActivity {

    private FlashcardCrud crud;

    public FlashcardsActivity() {
        List<Flashcard> flashcards = this.crud.findAll();
        System.out.println(flashcards);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcards_activity);
    }
}
