package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulo.myvideogamelist.models.GameList;
import com.example.paulo.myvideogamelist.services.AuthService;

import io.objectbox.Box;

public class ListFormActivity extends AppCompatActivity {

    TextInputLayout listTitleInput;
    Box<GameList> gameListBox;
    AuthService authService;
    GameList list;
    Button createListButton;
    long listId;
    final long NEW_LIST_NUMBER  = (long) 123213.3123123123129213012398 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_form);
        listTitleInput = findViewById(R.id.listRegisterInput);
        gameListBox = ((App)getApplication()).getBoxStore().boxFor(GameList.class);
        authService = ((App)getApplication()).getAuthService();

        Intent intent = getIntent();
        listId = intent.getLongExtra("listid",NEW_LIST_NUMBER);

        if (listId != NEW_LIST_NUMBER){
            list  = gameListBox.get(listId);
            listTitleInput.getEditText().setText(list.getTitulo(), TextView.BufferType.EDITABLE);
            createListButton = findViewById(R.id.createListButton);
            createListButton.setText(R.string.edit);
        }
    }

    public void createList(View view) {
        String title = listTitleInput.getEditText().getText().toString();

        if (title.equals("")){
            Toast.makeText(this, "Empty field. please fill it.", Toast.LENGTH_SHORT).show();
        } else {
            if (listId != NEW_LIST_NUMBER){
                list.setTitulo(title);
                gameListBox.put(list);
                finish();
            } else {
                GameList list = new GameList(title, authService.getCurrentUser());
                gameListBox.put(list);
                finish();
            }

        }
    }
}
