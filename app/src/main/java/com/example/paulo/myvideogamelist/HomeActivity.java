package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.paulo.myvideogamelist.adapters.GameListAdapter;

public class HomeActivity extends AppCompatActivity {

    ImageButton buttonAdd;
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();

        buttonAdd = view.findViewById(R.id.action_bar_add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), com.example.paulo.myvideogamelist.ListFormActivity.class);
                startActivity(intent);
            }
        });

        rvList = findViewById(R.id.listRecyclerView);

    }
}
