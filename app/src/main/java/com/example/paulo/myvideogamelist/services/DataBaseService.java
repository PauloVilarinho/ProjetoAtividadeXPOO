package com.example.paulo.myvideogamelist.services;

import com.example.paulo.myvideogamelist.models.Game;
import com.example.paulo.myvideogamelist.models.GameList;
import com.example.paulo.myvideogamelist.models.GameList_;
import com.example.paulo.myvideogamelist.models.ListGame;
import com.example.paulo.myvideogamelist.models.ListGame_;
import com.example.paulo.myvideogamelist.models.User;
import com.example.paulo.myvideogamelist.models.User_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class DataBaseService {

    AuthService authService;
    BoxStore boxStore;
    Box<GameList> gameListBox;
    Box<User> userBox;
    Box<ListGame> listGameBox;
    Box<Game> gameBox;


    public DataBaseService(AuthService authService, BoxStore boxStore) {
        this.authService = authService;
        this.boxStore = boxStore;
        this.gameListBox = boxStore.boxFor(GameList.class);
        this.userBox = boxStore.boxFor(User.class);
        this.listGameBox = boxStore.boxFor(ListGame.class);
        this.gameBox = boxStore.boxFor(Game.class);
    }


    public List<GameList> getAllListByUser(User user){
        return gameListBox.query().equal(GameList_.userId,user.id).build().find();
    }

    public  List<GameList> getAllCurrentUserLists (){
        return getAllListByUser(authService.getCurrentUser());
    }

    public void deleteGameList (GameList gameList){

        List<ListGame> listGamesRelation = listGameBox.query().equal(ListGame_.listId,gameList.getId()).build().find();
        listGameBox.remove(listGamesRelation);
        gameListBox.remove(gameList);

    }

    public List<Game> getAllGames (){
       return gameBox.query().build().find();
    }
}
