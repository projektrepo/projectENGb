package com.example.projecteng.flashcards;;

import com.example.projecteng.entity.Flashcard;

import java.util.ArrayList;
import java.util.List;

public class FlashcardCrud {

    private static long counter = 1;
    private static final FlashcardCrud instance = new FlashcardCrud();

    private List<Flashcard> flashcards;

    private FlashcardCrud() {
        this.flashcards = new ArrayList<>();

        this.flashcards.add(new Flashcard(counter++, "sword", "miecz"));
        this.flashcards.add(new Flashcard(counter++, "word", "słowo"));
        this.flashcards.add(new Flashcard(counter++, "string", "struna"));
        this.flashcards.add(new Flashcard(counter++, "spoon", "łyżka"));
        this.flashcards.add(new Flashcard(counter++, "fork", "widelec"));
        this.flashcards.add(new Flashcard(counter++, "knife", "nóż"));
        this.flashcards.add(new Flashcard(counter++, "world", "świat"));
        this.flashcards.add(new Flashcard(counter++, "weapon", "broń"));
        this.flashcards.add(new Flashcard(counter++, "death", "śmierć"));
        this.flashcards.add(new Flashcard(counter++, "elevator", "winda"));
        this.flashcards.add(new Flashcard(counter++, "candy", "cukierek"));
    }

    public static FlashcardCrud getInstance() {
        return instance;
    }

    public List<Flashcard> getAll() {
        return this.flashcards;
    }

    public Flashcard getOne(Long id) {
        Flashcard flashcard = null;
        for (Flashcard f : this.flashcards) {
            if (f.getId().equals(id)) {
                flashcard = f;
                break;
            }
        }

        return flashcard;
    }

    public boolean create(Flashcard flashcard) {
        if (flashcard.getEnglish() == null || flashcard.getPolish() == null) {
            return false;
        }

        flashcard.setId(counter++);
        this.flashcards.add(flashcard);
        return true;
    }

    public boolean update(Long id, Flashcard flashcardUpdated) {
        if (flashcardUpdated.getEnglish() == null || flashcardUpdated.getPolish() == null) {
            return false;
        }

        Flashcard flashcard = null;
        for (Flashcard f : this.flashcards) {
            if (f.getId().equals(id)) {
                flashcard = f;
                break;
            }
        }

        if (flashcard == null) {
            return false;
        }

        flashcard.setEnglish(flashcardUpdated.getEnglish());
        flashcard.setPolish(flashcardUpdated.getPolish());

        return true;
    }

    public boolean delete(Long id) {
        Flashcard flashcard = null;
        for (Flashcard f : this.flashcards) {
            if (f.getId().equals(id)) {
                flashcard = f;
                break;
            }
        }

        return this.flashcards.remove(flashcard);
    }
}
