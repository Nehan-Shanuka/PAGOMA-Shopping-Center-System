package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.controller.ProductDisplayController;
import com.oop_cw.pase_01.controller.ShoppingCartController;
import com.oop_cw.pase_01.model.Product;
import com.oop_cw.pase_01.model.ShoppingCart;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShoppingCartDisplay extends JFrame {
    private JLabel totalTxtField;
    ShoppingCart shoppingCart = ShoppingCart.getInstance();
    public ShoppingCartDisplay() {

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(1,2));

        JPanel northPanel1 = new JPanel();
        JPanel northPanel2 = new JPanel();

        JButton btnBack = new JButton("MORE SHOPPING");
        btnBack.addActionListener(new MoreShoppingBtnEventListener());

        JButton btnPlaceOrder = new JButton("PLACE THE ORDER");
        btnPlaceOrder.addActionListener(new PlaceOrderBtnEventListener());

        northPanel1.add(btnBack);
        northPanel2.add(btnPlaceOrder);

        northPanel.add(northPanel1);
        northPanel.add(northPanel2);

        mainContainer.add(northPanel, BorderLayout.NORTH);


        // Central Panel
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Central sub panel 1
        JPanel centerPanel1 = new JPanel(new GridLayout());
        centerPanel1.setPreferredSize(new Dimension(900, 280));

        TblShoppingCartModel shoppingCartDisplayModel = new TblShoppingCartModel(shoppingCart.getShoppingCartList());
        JTable shoppingCartDisplay = new JTable(shoppingCartDisplayModel);
        shoppingCartDisplay.setRowHeight(50);

        JScrollPane scrollPane = new JScrollPane(shoppingCartDisplay);
        centerPanel1.add(scrollPane);

        centerPanel.add(centerPanel1, BorderLayout.NORTH);


        // Central sub panel 2
        JPanel centerPanel2 = new JPanel(new GridLayout(1,3));

        JPanel centerPanel2Sub1 = new JPanel(new GridLayout());

        JPanel centerPanel2Sub2 = new JPanel(new GridLayout(2,1));

        JPanel centerPanel2Sub3 = new JPanel(new BorderLayout());

        JPanel centerPanel2Sub4 = new JPanel();

        centerPanel2.add(centerPanel2Sub1);
        centerPanel2.add(centerPanel2Sub2);
        centerPanel2.add(centerPanel2Sub3);
        centerPanel2.add(centerPanel2Sub4);

        TextField textAreaProductDetails = new TextField();

        centerPanel2Sub1.add(textAreaProductDetails);

        JPanel lblBannerPanel = new JPanel();
        lblBannerPanel.setPreferredSize(new Dimension(20,3));

        JPanel textAreaQuantityPanel = new JPanel(new FlowLayout());

        centerPanel2Sub2.add(lblBannerPanel);
        centerPanel2Sub2.add(textAreaQuantityPanel);

        JLabel lblBanner = new JLabel("Select the Quantity");
        JTextArea textAreaQuantity = new JTextArea("1");
        JButton btnIncrement = new JButton("+");
        JButton btnDecrement = new JButton("-");

        lblBannerPanel.add(lblBanner);
        textAreaQuantityPanel.add(btnDecrement);
        textAreaQuantityPanel.add(textAreaQuantity);
        textAreaQuantityPanel.add(btnIncrement);

        TextField txtFieldPrice = new TextField();

        centerPanel2Sub3.add(txtFieldPrice);

        JButton btnUpdate = new JButton("Update Quantity");

        centerPanel2Sub4.add(btnUpdate);

        centerPanel.add(centerPanel2);

        mainContainer.add(centerPanel, BorderLayout.CENTER);


        JPanel southPanel = new JPanel(new GridLayout(1,2));
        southPanel.setBackground(Color.RED);
        southPanel.setPreferredSize(new Dimension(900,180));

        JPanel southPanel1 = new JPanel(new GridLayout(4,1));
        JPanel southPanel2 = new JPanel(new GridLayout(4,1));

        JPanel southSubPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel southSubPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel southSubPanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel southSubPanel7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JPanel southSubPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel southSubPanel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel southSubPanel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel southSubPanel8 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        southPanel.add(southPanel1);
        southPanel.add(southPanel2);

        southPanel1.add(southSubPanel1);
        southPanel1.add(southSubPanel2);
        southPanel1.add(southSubPanel3);
        southPanel1.add(southSubPanel7);

        southPanel2.add(southSubPanel4);
        southPanel2.add(southSubPanel5);
        southPanel2.add(southSubPanel6);
        southPanel2.add(southSubPanel8);

        JLabel totalLabel = new JLabel("Total Price");

        JLabel firstPurchaseDisLabel = new JLabel("First Purchase Discount (10%)");

        JLabel threeItemsDisLabel = new JLabel("Three Items in Same Category Discount (20%)");

        JLabel finalTotalLbl = new JLabel("Final Total");
//        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
//        threeItemsDisLabel.setBorder(lineBorder);

        southSubPanel1.add(totalLabel);
        southSubPanel2.add(firstPurchaseDisLabel);
        southSubPanel3.add(threeItemsDisLabel);
        southSubPanel7.add(finalTotalLbl);

        totalTxtField = new JLabel();
        totalTxtField.setText(":  " + shoppingCart.cartTotalCalculate());

        JLabel discountTxtField1 = new JLabel();
        discountTxtField1.setText(":  " + shoppingCart.firstPurchaseDisCalculate());

        JLabel discountTxtField2 = new JLabel();
        discountTxtField2.setText(":  " + shoppingCart.threeSameProductDisCalculate());

        JLabel finalTotalValue = new JLabel();
        finalTotalValue.setText(":  " + shoppingCart.finalCartTotalCalculate());

        southSubPanel4.add(totalTxtField);
        southSubPanel5.add(discountTxtField1);
        southSubPanel6.add(discountTxtField2);
        southSubPanel8.add(finalTotalValue);

        mainContainer.add(southPanel, BorderLayout.SOUTH);
    }
    public class MoreShoppingBtnEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openProductGUI();
            dispose();
        }
    }
    public void openProductGUI() {
        ProductDisplayController newProductInstance = new ProductDisplayController();
    }

    public class PlaceOrderBtnEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shoppingCart.placeTheOrder();
            dispose();
        }
    }
}
