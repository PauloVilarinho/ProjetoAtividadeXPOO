package com.example.paulo.myvideogamelist;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.paulo.myvideogamelist.models.User;

import io.objectbox.Box;
import io.objectbox.exception.UniqueViolationException;

public class UserFormActivity extends AppCompatActivity {
    TextInputLayout usernameView;
    TextInputLayout passwordView;
    String username;
    String password;
    Box<User> userBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
        setupViews();
        setupBox();
    }

    private void setupBox() {
        userBox = ((App)getApplication()).getBoxStore().boxFor(User.class);
    }

    private void setupViews() {
        usernameView = findViewById(R.id.usernameRegisterInput);
        passwordView = findViewById(R.id.passwordRegisterInput);
    }

    public void singUp(View view) {
        username = usernameView.getEditText().getText().toString();
        password = passwordView.getEditText().getText().toString();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "One of the fields is empty. Please fill it", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            User user = new User(username,password);
            userBox.put(user);
        }catch (UniqueViolationException e){
            Toast.makeText(this, "Username already exist", Toast.LENGTH_SHORT).show();
            return;
        }

        finish();
    }
}
