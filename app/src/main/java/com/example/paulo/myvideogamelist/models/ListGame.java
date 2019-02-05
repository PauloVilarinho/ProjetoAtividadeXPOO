package com.example.paulo.myvideogamelist.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;
@Entity
public class ListGame {
    @Id
    long id;
    public ToOne<Game> game;
    public ToOne<List> list;

    public ListGame() {
    }

    public ListGame(Game game, List list) {
        this.game.setTarget(game);
        this.list.setTarget(list);
    }

    public Game getGame() {
        return game.getTarget();
    }

    public void setGame(Game game) {
        this.game.setTarget(game);
    }

    public List getList() {
        return list.getTarget();
    }

    public void setList(List list) {
        this.list.setTarget(list);
    }
}
