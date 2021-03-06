package com.example.paulo.myvideogamelist.models;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Unique;
import io.objectbox.relation.ToOne;

@Entity
public class Game {
    @Id
    public long id;
    @Unique
    private String title ;
    private String description;
    private Date releaseDate;
    public ToOne<Developer> developer;

    public Game() {
    }

    public Game(String title, String description,Developer developer) {
        this.title = title;
        this.description = description;
        this.developer.setTarget(developer);
    }

    public Game(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Game(String title, String description, Date releaseDate) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Developer getDeveloper() {
        return developer.getTarget();
    }

    public void setDeveloper(Developer developer) {
        this.developer.setTarget(developer);
    }
}
