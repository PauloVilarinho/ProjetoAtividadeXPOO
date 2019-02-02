package com.example.paulo.myvideogamelist.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.example.paulo.myvideogamelist.App;
import com.example.paulo.myvideogamelist.models.User;
import com.example.paulo.myvideogamelist.models.User_;

import io.objectbox.Box;


public class AuthService extends Service {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Box<User> userBox;
    User currentUser;
    public static final String USER_KEY = "user key";

    public long getCurrentUserId() {
            return pref.getLong(USER_KEY,(long) 0.23123);
    }
    public void logOut(){
        currentUser = null;
        editor.remove(USER_KEY);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return !(currentUser == null);
    }


    public AuthService() {

    }

    public boolean authenticateUser(String username,String password){
        try {
            currentUser = userBox.query().equal(User_.username, username).equal(User_.password, password).build().findUnique();
            editor.putLong(USER_KEY,currentUser.id);
            return true;
        } catch (Exception e){
            return false;
        }
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
