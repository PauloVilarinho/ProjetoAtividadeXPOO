package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.paulo.myvideogamelist.models.Game;

import io.objectbox.Box;

public class GamesFormActivity extends AppCompatActivity {

    Box<Game> gameBox;
    TextInputLayout gameTitleInput;
    TextInputLayout gameDescriptionInput;
    Button gameRegisterButton;
    App application;
    long NEW_GAME_LONG = (long) 123.123213;
    long gameid;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_form);

        gameTitleInput = findViewById(R.id.gameTitleInput);
        gameDescriptionInput = findViewById(R.id.gameDescriptionInput);
        gameRegisterButton = findViewById(R.id.registerGameButton);
        application = (App) getApplication();
        gameBox = application.getBoxStore().boxFor(Game.class);

        Intent intent = getIntent();
        gameid = intent.getLongExtra("gameid", NEW_GAME_LONG);

        if (gameid != NEW_GAME_LONG){
            game = gameBox.get(gameid);
            gameTitleInput.getEditText().setText(game.getTitle());
            gameDescriptionInput.getEditText().setText(game.getDescription());
            gameRegisterButton.setText("Edit Game");
        }


    }

    public void registerGame(View view) {

        String title = gameTitleInput.getEditText().getText().toString();
        String description = gameDescriptionInput.getEditText().getText().toString();

        if (title.equals("") || description.equals("")) {
            Toast.makeText(this, "Some field is empty, please fill it.", Toast.LENGTH_SHORT).show();
        } else {
            if (gameid != NEW_GAME_LONG) {
                game = gameBox.get(gameid);
                game.setDescription(description);
                game.setTitle(title);
                gameBox.put(game);
                finish();
            } else {
                game = new Game(title, description);
                gameBox.put(game);
                finish();
            }

        }
    }
}
