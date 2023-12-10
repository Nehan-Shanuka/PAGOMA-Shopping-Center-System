package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductDisplay extends JFrame {

    public ProductDisplay(ArrayList<Product> productList) {

        Container newContainer = getContentPane();
        newContainer.setLayout(new GridLayout());

//        JPanel panelRoot = new JPanel();
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel();

        panel1.setPreferredSize(new Dimension(1200,200));

        add(panel1);
        add(panel2);

        JLabel lblCategorySelection = new JLabel("Select Product Category");
        panel1.add(lblCategorySelection);

        String[] dropdownList = {"All", "Electronic", "Clothing"};

        JComboBox<String> dropdown = new JComboBox<>(dropdownList);
        panel1.add(dropdown);

        JButton btnShoppingCart = new JButton("Shopping Cart");
        panel1.add(btnShoppingCart);

        TblProductDisplay tblProductDisplayModel = new TblProductDisplay(productList);
        JTable tblProductDisplay = new JTable(tblProductDisplayModel);
        panel2.add(tblProductDisplay);
        panel2.add(new JScrollPane(tblProductDisplay), BorderLayout.CENTER);
    }
}
