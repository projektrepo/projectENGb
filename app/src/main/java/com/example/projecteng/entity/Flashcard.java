package com.example.projecteng.entity;

public class Flashcard {

    private Long id;
    private String english;
    private String polish;

    public Flashcard(Long id, String english, String polish) {
        this.id = id;
        this.english = english;
        this.polish = polish;
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
}
