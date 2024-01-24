package com.oop_cw.pase_01.model;

public class CurrentUser {
    private static boolean userStatus;
    private static CurrentUser instance;
    private static User user;
    UserList userList = UserList.getInstance();

    public CurrentUser(String userName, String password, int purchaseHistory) {
        userStatus = true;
        user = new User(userName, password, purchaseHistory);
    }

    public CurrentUser() {}

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
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

    public void incrementPurchaseHistory() {
//        user.setPurchaseHistory() += 1;
    }
}
