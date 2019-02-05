package com.example.paulo.myvideogamelist;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.paulo.myvideogamelist.models.MyObjectBox;
import com.example.paulo.myvideogamelist.models.User;
import com.example.paulo.myvideogamelist.services.AuthService;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class App extends Application {

    public SharedPreferences pref;
    public AuthService authService;

    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(this);
        }
        pref = getApplicationContext().getSharedPreferences("mypref",Context.MODE_PRIVATE);
        authService = new AuthService(boxStore.boxFor(User.class),pref);

        Log.d("App", "Using ObjectBox " + BoxStore.getVersion() + " (" + BoxStore.getVersionNative() + ")");
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

    public AuthService getAuthService() {
        return authService;
    }
}
