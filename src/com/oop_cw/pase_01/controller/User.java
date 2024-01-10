package com.oop_cw.pase_01.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String username;
    private String password;

    // Constructor with given username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // A public method for retrieving the username
    public String getUsername() {
        return username;
    }

    // A public method for retrieving the password
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // A public method for assigning into the password
    public void setPassword(String password) {
        this.password = password;
    }
}
