package com.example.paulo.myvideogamelist;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.paulo.myvideogamelist.services.AuthService;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = new Intent(this,AuthService.class);
        startService(intent);

    }


    public void singIn(View view) {
    }

    public void singUp(View view) {
        Intent intent = new Intent(this,UserFormActivity.class);
        startActivity(intent);

    }
}
