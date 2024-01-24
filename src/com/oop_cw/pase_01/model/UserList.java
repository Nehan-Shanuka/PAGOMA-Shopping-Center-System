package com.oop_cw.pase_01.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {

//    private int purchaseHistory;
    private static UserList instance;
    private ArrayList<User> userList = new ArrayList<>();

    public UserList() {}

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void addUserToUserList(User user) {
        userList.add(user);
        saveUserList();
    }

    public void incrementUserPurchase(String name) {
        for (User user: userList) {
            if (user.getUsername() == name) {
                user.setPurchaseHistory(user.getPurchaseHistory()+1);
                saveUserList();
                break;
            }
        }
    }

    public void saveUserList() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new
                FileWriter("userdetails.txt", false))) {
            for (User user: userList) {
                bufferedWriter.write(user.getUsername() + ", " +
                        user.getPassword() + ", " + user.getPurchaseHistory() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadUserList() {
        try (BufferedReader bufferedObj = new BufferedReader(new
                FileReader("userdetails.txt"))) {
            Scanner bufferReader = new Scanner(bufferedObj);

            ArrayList<User> newUserList =new ArrayList<>();

            while (bufferReader.hasNextLine()) {
                String content = bufferReader.nextLine();

                String[] contentArray = content.split(", ");

                String username = contentArray[0];
                String password = contentArray[1];
                String purchaseHistory = contentArray[2];

                newUserList.add(new User(username, password, Integer.parseInt(purchaseHistory)));
            }

            userList = newUserList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No Users Registered");
            System.out.println();
        }
    }
}
