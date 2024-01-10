package com.oop_cw.pase_01.model;

import com.oop_cw.pase_01.controller.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {

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
    }

    public void saveUserList() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new
                FileWriter("userdetails.txt", false))) {
            for (User user: userList) {
                bufferedWriter.write(user.getUsername() +", "+ user.getPassword());
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

                newUserList.add(new User(username, password));
            }

            userList = newUserList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }
}
