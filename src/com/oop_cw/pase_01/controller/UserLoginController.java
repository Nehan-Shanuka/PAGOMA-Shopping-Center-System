package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.view.UserLogin;

import java.awt.*;

public class UserLoginController {

    public UserLoginController() {

        UserLogin frame = new UserLogin();
        frame.setSize(600, 300);
        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Westminster Shopping Center");
        frame.setLocationRelativeTo(null);
    }
}
