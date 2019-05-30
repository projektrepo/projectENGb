package com.example.projecteng.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.projecteng.R;
import com.example.projecteng.entity.Flashcard;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class FlashcardsActivity extends AppCompatActivity {

    private FlashcardCrud crud;

    private ScrollView scrollView;
    private Button addFlashcardButton;

    public FlashcardsActivity() {
        this.crud = FlashcardCrud.getInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcards_activity);

        this.scrollView = (ScrollView) findViewById(R.id.flashcards_scrollview);
        this.addFlashcardButton = (Button) findViewById(R.id.add_flashcard_button);

        render();

        this.addFlashcardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFlashcard = new Intent(FlashcardsActivity.this, AddFlashcardActivity.class);
                startActivity(addFlashcard);
            }
        });
    }

    public void render() {
        List<Flashcard> flashcards = this.crud.getAll();
        LinearLayout layout = (LinearLayout) this.scrollView.getChildAt(0);

        for (final Flashcard flashcard : flashcards) {
            final Button button = new Button(this);

            button.setId(Integer.valueOf(flashcard.getId().toString()));
            button.setText(flashcard.getEnglish());

            button.setOnClickListener(new View.OnClickListener() {
                private String english = flashcard.getEnglish();
                private String polish = flashcard.getPolish();

                @Override
                public void onClick(View v) {
                    if (button.getText().equals(this.english)) {
                        button.setText(this.polish);
                    } else {
                        button.setText(this.english);
                    }
                }
            });

            layout.addView(button);
        }
    }
}
