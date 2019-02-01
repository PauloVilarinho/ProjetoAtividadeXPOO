package com.example.paulo.myvideogamelist.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.example.paulo.myvideogamelist.App;
import com.example.paulo.myvideogamelist.models.User;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class AuthService extends Service {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Box<User> userBox;
    public static final String USER_KEY = "user key";
    public AuthService() {

    }

    public void authenticateUser(String username,String password){

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        userBox = ((App)getApplication()).getBoxStore().boxFor(User.class);
        pref = getApplicationContext().getSharedPreferences("mypref",Context.MODE_PRIVATE);
        editor = pref.edit();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
