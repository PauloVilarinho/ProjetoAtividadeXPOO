package com.example.paulo.myvideogamelist.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.Unique;

@Entity
public class User {

    @Id
    public long id;
    @Index
    @Unique
    private String username;
    private String password;

    public User() {}

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }                             

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean changePassword(String password, String newpassword) {
        if (this.getPassword() == password){
            this.setPassword(newpassword);
            return true;
        }
        return false;

    }




}
