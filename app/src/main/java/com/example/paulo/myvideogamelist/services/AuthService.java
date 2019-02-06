package com.example.paulo.myvideogamelist.services;


import android.app.Activity;
import android.bluetooth.BluetoothClass;
import android.content.Context;

import android.content.SharedPreferences;


import com.example.paulo.myvideogamelist.App;
import com.example.paulo.myvideogamelist.models.User;
import com.example.paulo.myvideogamelist.models.User_;

import java.security.Provider;

import io.objectbox.Box;


public class AuthService {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Box<User> userBox;
    User currentUser;
    long userId;
    public static final String USER_KEY = "user key";

    public AuthService(){

    }

    public AuthService(Box<User> userBox, SharedPreferences pref) {
        this.userBox = userBox;
        this.pref = pref;
        editor = this.pref.edit();
    }

    public User getCurrentUser() {
        if (isLoggedIn()) {
            return userBox.get(userId);
        }
        return null;
    }
    public void logOut(){
        currentUser = null;
        userId = Long.parseLong(null);
        editor.remove(USER_KEY);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return !(currentUser == null);
    }


    public boolean authenticateUser(String username,String password){
        try {
            currentUser = userBox.query().equal(User_.username, username).equal(User_.password, password).build().findUnique();
            userId = currentUser.id;
            editor.putLong(USER_KEY,userId);
            return true;
        } catch (Exception e){
            return false;
        }
    }



}
