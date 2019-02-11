package com.example.paulo.myvideogamelist.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Review {

    @Id
    public long id;
    private String text;
    private int reviewNote;
    public ToOne<User> author;
    public ToOne<Game> target;

    public Review(){

    }

    public Review(String text, int reviewNote, User author, Game target) {
        this.text = text;
        this.reviewNote = reviewNote;
        this.author.setTarget(author);
        this.target.setTarget(target);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReviewNote() {
        return reviewNote;
    }

    public void setReviewNote(int reviewNote) {
        this.reviewNote = reviewNote;
    }

    public User getAuthor() {
        return author.getTarget();
    }

    public void setAuthor(User author) {
        this.author.setTarget(author);
    }

    public Game getTarget() {
        return target.getTarget();
    }

    public void setTarget(Game target) {
        this.target.setTarget(target);
    }
}
