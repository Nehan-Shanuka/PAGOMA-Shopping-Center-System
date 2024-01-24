package com.oop_cw.pase_01.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private int purchaseHistory;

    // Constructor with given username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        purchaseHistory = 0;
    }

    public User(String username, String password, int purchaseHistory) {
        this.username = username;
        this.password = password;
        this.purchaseHistory = purchaseHistory;
    }

    // A public method for retrieving the username
    public String getUsername() {
        return username;
    }

    // A public method for retrieving the password
    public String getPassword() {
        return password;
    }

    public int getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // A public method for assigning into the password
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPurchaseHistory(int purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }
}
