package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.paulo.myvideogamelist.models.Game;

import io.objectbox.Box;

public class GameDetailsActivity extends AppCompatActivity {
    long gameId;
    Box<Game> gameBox;
    Game game;
    App application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        gameBox = application.getBoxStore().boxFor(Game.class);

        Intent intent = getIntent();
        gameId = intent.getLongExtra("gameid",(long)123213123);
        game = gameBox.get(gameId);


    }
}
