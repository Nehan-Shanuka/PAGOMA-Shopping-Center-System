package com.oop_cw.pase_01.model;

public class CurrentUser {
    private boolean userStatus;
    private static CurrentUser instance;

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatusTrue() {
        userStatus = true;
    }

    public void setUserStatusFalse() {
        userStatus = false;
    }
}
