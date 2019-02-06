package com.example.paulo.myvideogamelist.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Uid;
import io.objectbox.relation.ToOne;
@Entity
public class ListGame {
    @Id
    long id;
    public ToOne<Game> game;
    public ToOne<GameList> list;

    public ListGame() {
    }

    public ListGame(Game game, GameList list) {
        this.game.setTarget(game);
        this.list.setTarget(list);
    }

    public Game getGame() {
        return game.getTarget();
    }

    public void setGame(Game game) {
        this.game.setTarget(game);
    }

    public GameList getList() {
        return list.getTarget();
    }

    public void setList(GameList list) {
        this.list.setTarget(list);
    }
}
