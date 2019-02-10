package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.example.paulo.myvideogamelist.adapters.GameListAdapter;

public class HomeActivity extends AppCompatActivity {

    ImageButton buttonAdd;
    ImageButton buttonSearch;
    RecyclerView rvList;
    App app;
    LinearLayoutManager linearLayoutManager;
    GameListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        app = (App)getApplication();

        setupActionBar();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        rvList = findViewById(R.id.listRecyclerView);
        linearLayoutManager =  new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        adapter = new GameListAdapter(app.getDataBaseService(),this,app.getAuthService().getCurrentUser());
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(linearLayoutManager);
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
                Intent intent = new Intent(v.getContext(), ListFormActivity.class);
                startActivity(intent);
            }
        });

        buttonSearch = view.findViewById(R.id.action_bar_search);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListGamesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setLists(app.getDataBaseService().getAllCurrentUserLists());
        adapter.notifyDataSetChanged();
        rvList.setAdapter(adapter);

    }
}
