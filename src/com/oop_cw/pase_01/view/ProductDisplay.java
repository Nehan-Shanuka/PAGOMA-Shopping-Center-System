package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.controller.ShoppingCartGUIController;
import com.oop_cw.pase_01.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductDisplay extends JFrame {

    public ProductDisplay(ArrayList<Product> productList) {

        // Initialising the container and setting a layout
        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));

        // JPanel for contain both dropdown list and shopping cart btn
        JPanel northPanel = new JPanel(new GridLayout());
        mainContainer.add(northPanel, BorderLayout.NORTH);

        // JPanel for hold the dropdown selection and its label
        JPanel northSubPanel1 = new JPanel(new FlowLayout());
        northPanel.add(northSubPanel1, BorderLayout.WEST);

        // JPanel for hold the btn for shopping cart
        JPanel northSubPanel2 = new JPanel();
        northPanel.add(northSubPanel2, BorderLayout.EAST);

        // JPanel for hold the JTable, and it centered in between north and south panels
        JPanel centerPanel = new JPanel(new GridLayout());
        mainContainer.add(centerPanel);

        // JPanel for hold the textarea, and it placed in the bottom of the container
        JPanel southPanel = new JPanel(new FlowLayout());
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        // JLabel to describe the dropdown selection
        JLabel lblCategorySelection = new JLabel("Select Product Category");
        northSubPanel1.add(lblCategorySelection);

        String[] dropdownList = {"All", "Electronic", "Clothing"};

        // Initialising the dropdown selection with three selections
        JComboBox<String> dropdown = new JComboBox<>(dropdownList);
        northSubPanel1.add(dropdown);

        // JButton for rendering the shopping cart scene
        JButton btnShoppingCart = new JButton("Shopping Cart");
        northSubPanel2.add(btnShoppingCart);

        btnShoppingCart.addActionListener(new ShoppigCartBtnEventListner());

        // Initialising table model, JTable and JScrollPane and adding them
        TblProductDisplay tblProductDisplayModel = new TblProductDisplay(productList);
        JTable tblProductDisplay = new JTable(tblProductDisplayModel);
        tblProductDisplay.setRowHeight(20);

        JScrollPane jScrollPane = new JScrollPane(tblProductDisplay);
        centerPanel.add(jScrollPane);

        // Initialising the JTextArea and adding it into the southPanel
        JTextArea txtAreaProductDescription = new JTextArea();
        txtAreaProductDescription.setColumns(80);
        txtAreaProductDescription.setRows(10);
        southPanel.add(txtAreaProductDescription);
    }

    public class ShoppigCartBtnEventListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShoppingCartGUIController shoppingCartDisplay = new ShoppingCartGUIController();

            dispose();
        }
    }
}
