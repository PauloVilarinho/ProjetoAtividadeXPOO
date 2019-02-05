package com.example.paulo.myvideogamelist;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.paulo.myvideogamelist.services.AuthService;



public class LoginActivity extends AppCompatActivity {
    AuthService authService;
    TextInputLayout usernameInput;
    TextInputLayout passwordInput;
    String username ;
    String password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = new Intent(this,AuthService.class);
        startService(intent);
        authService = ((App)getApplication()).getAuthService();
        setViews();

    }

    private void setViews() {
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
    }


    public void singIn(View view) {
        username = usernameInput.getEditText().getText().toString();
        password = passwordInput.getEditText().getText().toString();

        if (authService.authenticateUser(username,password)){
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Username or password wrong. Try again.", Toast.LENGTH_SHORT).show();
        }

    }

    public void singUp(View view) {
        Intent intent = new Intent(this,UserFormActivity.class);
        startActivity(intent);

    }
}
