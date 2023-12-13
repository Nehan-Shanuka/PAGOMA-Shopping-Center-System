package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.model.Product;
import com.oop_cw.pase_01.view.ShoppingCartDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingCartGUIController {

    public ShoppingCartGUIController(ArrayList<Product> productArrayList) {

        ShoppingCartDisplay frame = new ShoppingCartDisplay(productArrayList);
        frame.setSize(900, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Westminster Shopping Center");
        frame.setLocationRelativeTo(null);
    }
}
