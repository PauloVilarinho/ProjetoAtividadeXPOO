package com.example.paulo.myvideogamelist;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.paulo.myvideogamelist.models.GameList;
import com.example.paulo.myvideogamelist.services.AuthService;

import io.objectbox.Box;

public class ListFormActivity extends AppCompatActivity {

    TextInputLayout listTitleInput;
    Box<GameList> gameListBox;
    AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_form);
        listTitleInput = findViewById(R.id.listRegisterInput);
        gameListBox = ((App)getApplication()).getBoxStore().boxFor(GameList.class);
        authService = ((App)getApplication()).getAuthService();
    }

    public void createList(View view) {
        String title = listTitleInput.getEditText().getText().toString();

        if (title.equals("")){
            Toast.makeText(this, "Empty field. please fill it.", Toast.LENGTH_SHORT).show();
        } else {
            GameList list = new GameList(title,authService.getCurrentUser());
            gameListBox.put(list);
            finish();

        }
    }
}
