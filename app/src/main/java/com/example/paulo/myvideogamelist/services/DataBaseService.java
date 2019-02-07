package com.example.paulo.myvideogamelist.services;

import com.example.paulo.myvideogamelist.models.GameList;
import com.example.paulo.myvideogamelist.models.GameList_;
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


    public DataBaseService(AuthService authService, BoxStore boxStore) {
        this.authService = authService;
        this.boxStore = boxStore;
        this.gameListBox = boxStore.boxFor(GameList.class);
    }


//    public List<GameList> getAllListByUser(User user){
//        QueryBuilder<GameList> builder = gameListBox.query();
//        builder.link(GameList_.user)
//    }
}
