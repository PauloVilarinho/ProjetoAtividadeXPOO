package com.example.paulo.myvideogamelist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.paulo.myvideogamelist.models.Game;
import com.example.paulo.myvideogamelist.models.GameList;
import com.example.paulo.myvideogamelist.models.ListGame;
import com.example.paulo.myvideogamelist.services.AuthService;
import com.example.paulo.myvideogamelist.services.DataBaseService;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class GameDetailsActivity extends AppCompatActivity {
    long gameId;
    Box<Game> gameBox;
    Box<ListGame> listGameBox;
    Game game;
    App application;
    AuthService authService;
    DataBaseService dataBaseService;

    TextView gameTitle;
    TextView gameDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        application = (App)getApplication();
        authService = application.getAuthService();
        dataBaseService = application.getDataBaseService();

        gameBox = application.getBoxStore().boxFor(Game.class);
        listGameBox = application.getBoxStore().boxFor(ListGame.class);

        Intent intent = getIntent();
        gameId = intent.getLongExtra("gameid",(long)123213123);
        game = gameBox.get(gameId);

        gameTitle = findViewById(R.id.gameDetailTitle);
        gameDescription = findViewById(R.id.gameDetailDescription);

        gameTitle.setText(game.getTitle());
        gameDescription.setText(game.getDescription());






    }




    public void addToList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Spinner spinner = new Spinner(this,Spinner.MODE_DIALOG);
        final List<GameList> gameListList = dataBaseService.getAllCurrentUserLists();
        List<String> listTitleList = new ArrayList<String>();
        for( GameList list : gameListList){
            listTitleList.add(list.getTitulo());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listTitleList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        builder.setTitle("Choose what list to add.");
        builder.setView(spinner);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int i = spinner.getSelectedItemPosition();
                GameList gameList = gameListList.get(i);

                ListGame listGame = new ListGame(game,gameList);
                listGameBox.put(listGame);
                dialog.dismiss();

            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();



    }
}
