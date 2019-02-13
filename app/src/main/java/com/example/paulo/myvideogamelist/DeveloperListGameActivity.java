package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.paulo.myvideogamelist.adapters.GameAdapter;
import com.example.paulo.myvideogamelist.models.Developer;
import com.example.paulo.myvideogamelist.services.DataBaseService;

import io.objectbox.Box;

public class DeveloperListGameActivity extends AppCompatActivity {

    TextView developerName;
    TextView developerDescription;
    RecyclerView developerGamesRV;
    App application;
    DataBaseService dataBaseService;
    long developerId;
    Developer developer;
    Box<Developer> developerBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_list_game);

        setupServices();
        setupBoxes();
        getIntentData();
        setupviews();

    }

    private void setupBoxes() {
        developerBox = application.getBoxStore().boxFor(Developer.class);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        developerId = intent.getLongExtra("developerid",(long)12312.1231);
        developer = developerBox.get(developerId);
    }

    private void setupviews() {
        developerName = findViewById(R.id.developerNameDetail);
        developerDescription =findViewById(R.id.developerDescriptionDetails);
        developerGamesRV = findViewById(R.id.allGamesRecyclerView);

        developerName.setText(developer.getName());
        developerDescription.setText(developer.getDescription());
        GameAdapter adapter = new GameAdapter(dataBaseService.getAllDeveloperGames(developer),this,dataBaseService);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        developerGamesRV.setAdapter(adapter);
        developerGamesRV.setLayoutManager(layoutManager);

    }

    private void setupServices() {
        application = (App)getApplication();
        dataBaseService = application.getDataBaseService();
    }
}
