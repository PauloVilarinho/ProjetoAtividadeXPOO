package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paulo.myvideogamelist.models.Developer;
import com.example.paulo.myvideogamelist.models.Game;
import com.example.paulo.myvideogamelist.services.DataBaseService;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class GamesFormActivity extends AppCompatActivity {

    Box<Game> gameBox;
    TextInputLayout gameTitleInput;
    TextInputLayout gameDescriptionInput;
    Spinner gameDeveloperSpinner;
    Button gameRegisterButton;
    App application;
    DataBaseService dataBaseService;
    long NEW_GAME_LONG = (long) 123.123213;
    long gameid;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_form);

        setupServices();
        setupViews();
        setupBoxes();

        Intent intent = getIntent();
        gameid = intent.getLongExtra("gameid", NEW_GAME_LONG);

        if (gameid != NEW_GAME_LONG){
            game = gameBox.get(gameid);
            gameTitleInput.getEditText().setText(game.getTitle());
            gameDescriptionInput.getEditText().setText(game.getDescription());
            gameRegisterButton.setText("Edit Game");
        }


    }

    private void setupServices() {
        application = (App) getApplication();
        dataBaseService = application.getDataBaseService();
    }

    private void setupBoxes() {
        gameBox = application.getBoxStore().boxFor(Game.class);
    }

    private void setupViews() {
        gameTitleInput = findViewById(R.id.gameTitleInput);
        gameDescriptionInput = findViewById(R.id.gameDescriptionInput);
        gameRegisterButton = findViewById(R.id.registerGameButton);
        gameDeveloperSpinner = findViewById(R.id.developerSpinner);

        List<Developer> developerList = dataBaseService.getAllDevelopers();

        ArrayAdapter<Developer> adapter = new ArrayAdapter<Developer>(this,android.R.layout.simple_spinner_item,developerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gameDeveloperSpinner.setAdapter(adapter);



    }

    public void registerGame(View view) {

        String title = gameTitleInput.getEditText().getText().toString();
        String description = gameDescriptionInput.getEditText().getText().toString();
        Developer developer = (Developer)gameDeveloperSpinner.getSelectedItem();

        if (title.equals("") || description.equals("")) {
            Toast.makeText(this, "Some field is empty, please fill it.", Toast.LENGTH_SHORT).show();
        } else {
            if (gameid != NEW_GAME_LONG) {
                game = gameBox.get(gameid);
                game.setDescription(description);
                game.setTitle(title);
                game.setDeveloper(developer);
                gameBox.put(game);
                finish();
            } else {
                game = new Game(title, description,developer);
                gameBox.put(game);
                finish();
            }

        }
    }
}
