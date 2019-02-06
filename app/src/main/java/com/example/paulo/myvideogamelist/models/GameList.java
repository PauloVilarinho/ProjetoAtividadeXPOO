package com.example.paulo.myvideogamelist.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Uid;
import io.objectbox.relation.ToOne;

@Entity
@Uid(1906744599212008513L)
public class GameList {
    @Id
    long id;
    private String titulo;
    public ToOne<User> user;

    public GameList() {

    }

    public GameList(String titulo, User user) {
        this.titulo = titulo;
        this.user.setTarget(user);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public User getUser() {
        return user.getTarget();
    }

    public void setUser(User user) {
        this.user.setTarget(user);
    }
}
