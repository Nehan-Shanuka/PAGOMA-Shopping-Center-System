package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.view.ProductDisplay;

import javax.swing.*;
import java.awt.*;

public class ProductGUIController {

    // The controller class for open ProductDisplay GUI
    public ProductGUIController() {

        // Creating an instance of ProductDisplay extended JFrame
        ProductDisplay frame = new ProductDisplay();
        // Setting a width and height for the frame
        frame.setSize(900, 600);
        // Setting the visibility of the frame true
        frame.setVisible(true);
        // Setting frame's layout to BorderLayout
        frame.setLayout(new BorderLayout());
        // Setting the title of the frame
        frame.setTitle("Westminster Shopping Center");
        // Setting the location of the frame to center of the screen
        frame.setLocationRelativeTo(null);
        // Setting the default closing operation to dispose the GUI
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
