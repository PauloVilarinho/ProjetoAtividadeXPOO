package com.example.paulo.myvideogamelist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.paulo.myvideogamelist.adapters.GameListAdapter;
import com.example.paulo.myvideogamelist.adapters.ReviewAdapter;
import com.example.paulo.myvideogamelist.models.Game;
import com.example.paulo.myvideogamelist.models.GameList;
import com.example.paulo.myvideogamelist.models.ListGame;
import com.example.paulo.myvideogamelist.models.Review;
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
    RecyclerView reviewRV;
    ReviewAdapter adapter;

    TextView gameTitle;
    TextView gameDescription;
    TextView gameAverageScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        setupServices();
        setupBoxes();
        getIntentData();
        setupTextViews();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        reviewRV = findViewById(R.id.reviewRv);
        reviewRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReviewAdapter(dataBaseService.getAllReviewToGame(game),this,authService,dataBaseService);
        reviewRV.setAdapter(adapter);
    }

    private void setupServices() {
        application = (App)getApplication();
        authService = application.getAuthService();
        dataBaseService = application.getDataBaseService();
    }

    private void setupBoxes() {
        gameBox = application.getBoxStore().boxFor(Game.class);
        listGameBox = application.getBoxStore().boxFor(ListGame.class);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        gameId = intent.getLongExtra("gameid",(long)123213123);
        game = gameBox.get(gameId);
    }

    private void setupTextViews() {
        gameTitle = findViewById(R.id.gameDetailTitle);
        gameDescription = findViewById(R.id.gameDetailDescription);
        gameAverageScore = findViewById(R.id.gameDetailAverageScore);


        gameTitle.setText(game.getTitle());
        gameDescription.setText(game.getDescription());
        float averageScore = getAverageScore();

        gameAverageScore.setText(Float.toString(averageScore));

    }

    private float getAverageScore() {
        List<Review> reviews = dataBaseService.getAllReviewToGame(game);
        float averageScore = 0;
        for (Review review : reviews){
            averageScore += review.getReviewNote();
        }
        averageScore /= reviews.size();
        return averageScore;
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

    public void deleteGame(View view) {
        createDeleteDialog();
    }

    private void createDeleteDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("You are going to delete all data from that game. Are you sure?");
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBaseService.deleteGame(game);
                dialog.dismiss();
                finish();
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void editGame(View view) {

        Intent intent = new Intent(this,GamesFormActivity.class);
        intent.putExtra("gameid",game.id);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        game = gameBox.get(gameId);
        gameTitle.setText(game.getTitle());
        gameDescription.setText(game.getDescription());

        adapter.setReviewList(dataBaseService.getAllReviewToGame(game));
        adapter.notifyDataSetChanged();
        reviewRV.setAdapter(adapter);

        float averageScore = getAverageScore();

        gameAverageScore.setText(Float.toString(averageScore));



    }

    public void createReview(View view) {
        Intent intent = new Intent(this,ReviewFormActivity.class);
        intent.putExtra("gameid",gameId);
        startActivity(intent);
    }
}
