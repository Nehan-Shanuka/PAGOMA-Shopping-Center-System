package com.oop_cw.pase_01;

import com.oop_cw.pase_01.controller.WestminsterShoppingManager;
import com.oop_cw.pase_01.model.Electronics;
import com.oop_cw.pase_01.model.Product;
import com.oop_cw.pase_01.view.ProductDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WestminsterShoppingApplication {

    public static void main(String[] args) {

        ArrayList<Product> productList = new ArrayList<>();

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        ProductDisplay frame = new ProductDisplay(productList);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Westminster Shopping Center");
        frame.setLocationRelativeTo(null);

        shoppingManager.menu(productList);

    }

}
