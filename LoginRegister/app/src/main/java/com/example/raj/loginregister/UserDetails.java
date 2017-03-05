package com.example.raj.loginregister;

import android.util.Log;

/**
 * Created by raj on 21-Oct-16.
 */
public class UserDetails {
    private int _id;
    private String name;
    private String username;
    private String password;
    private String age;

    public UserDetails()
    {

    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDetails(String name, String username, String password, String age) {
        this.name=name;
        this.username = username;
        this.password = password;
        this.age = age;
        Log.i("UserDetails","UserDetails Constructor");
        //System.out.println("in userDetails constructor");
    }
    //setters
    public void set_id(int _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(String age) {
        this.age = age;
    }

    //getters


    public String getName() {
        return name;
    }

    public int get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }
}
