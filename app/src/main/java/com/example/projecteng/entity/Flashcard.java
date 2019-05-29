package com.example.projecteng.entity;

import java.util.Objects;

public class Flashcard {

    public static String TABLE_NAME = "flashcards";

    private Long id;
    private String english;
    private String polish;

    public Flashcard(Long id, String english, String polish) {
        this.id = id;
        this.english = english;
        this.polish = polish;
    }

    public Flashcard(String english, String polish) {
        this(null, english, polish);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPolish() {
        return polish;
    }

    public void setPolish(String polish) {
        this.polish = polish;
    }

    @Override
    public String toString() {
        return this.english + ": " + this.polish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return id.equals(flashcard.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
