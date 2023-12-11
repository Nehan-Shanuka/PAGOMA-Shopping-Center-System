package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingCartDisplay extends JFrame {

    public ShoppingCartDisplay() {

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new GridLayout(2,1));

        JPanel northPanel = new JPanel(new GridLayout());
        mainContainer.add(northPanel);

        JPanel southPanel = new JPanel();
        mainContainer.add(southPanel);

        JTable tblProductDisplay = new JTable();
        tblProductDisplay.setRowHeight(40);

        JScrollPane scrollPane = new JScrollPane(tblProductDisplay);
        northPanel.add(scrollPane);
    }
}
