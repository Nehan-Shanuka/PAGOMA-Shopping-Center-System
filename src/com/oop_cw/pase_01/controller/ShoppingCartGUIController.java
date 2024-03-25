package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.view.ShoppingCartDisplay;
import com.oop_cw.pase_01.view.UserLoginDisplay;

import javax.swing.*;
import java.awt.*;

// The controller class for open ShoppingCart GUI
public class ShoppingCartGUIController {

    public ShoppingCartGUIController() {

        // Creating an instance of UserLoginDisplay extended JFrame
        ShoppingCartDisplay frame = new ShoppingCartDisplay();
        // Setting a width and height for the frame
        frame.setSize(900, 600);
        // Setting the visibility of the frame true
        frame.setVisible(true);
        // Setting frame's layout to BorderLayout
        frame.setLayout(new BorderLayout());
        // Setting the title of the frame
        frame.setTitle("PAGOMA Shopping Center");
        // Setting the location of the frame to center of the screen
        frame.setLocationRelativeTo(null);
        // Setting the default closing operation to dispose the GUI
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}