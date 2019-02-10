package com.example.paulo.myvideogamelist;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_form);

        gameTitleInput = findViewById(R.id.gameTitleInput);
        gameDescriptionInput = findViewById(R.id.gameDescriptionInput);
        gameRegisterButton = findViewById(R.id.registerGameButton);
        application = (App)getApplication();
        gameBox = application.getBoxStore().boxFor(Game.class);

    }

    public void registerGame(View view) {

        String title = gameTitleInput.getEditText().getText().toString();
        String description = gameDescriptionInput.getEditText().getText().toString();

        if (title.equals("") || description.equals("")){
            Toast.makeText(this, "Some field is empty, please fill it.", Toast.LENGTH_SHORT).show();
        } else {
            Game game = new Game(title,description);
            gameBox.put(game);
            finish();
        }

    }
}
