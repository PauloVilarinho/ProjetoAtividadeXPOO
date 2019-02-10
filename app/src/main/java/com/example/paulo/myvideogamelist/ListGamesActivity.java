package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.paulo.myvideogamelist.adapters.GameAdapter;
import com.example.paulo.myvideogamelist.services.DataBaseService;

public class ListGamesActivity extends AppCompatActivity {
    ImageButton buttonAdd;
    ImageButton buttonSearch;
    RecyclerView gamesRv;
    LinearLayoutManager linearLayoutManager;
    GameAdapter gameAdapter;
    App application;
    DataBaseService dataBaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_games);
        setupActionBar();
        application = (App)getApplication();
        dataBaseService = application.getDataBaseService();

        gamesRv = findViewById(R.id.gameListRv);
        linearLayoutManager = new LinearLayoutManager(this);
        gameAdapter = new GameAdapter(dataBaseService.getAllGames(),this,dataBaseService);
        gamesRv.setAdapter(gameAdapter);
        gamesRv.setLayoutManager(linearLayoutManager);




    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();

        buttonAdd = view.findViewById(R.id.action_bar_add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),GamesFormActivity.class);
                startActivity(intent);
            }
        });

        buttonSearch = view.findViewById(R.id.action_bar_search);

        buttonSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameAdapter.setGameList(dataBaseService.getAllGames());
        gameAdapter.notifyDataSetChanged();
        gamesRv.setAdapter(gameAdapter);
    }
}
