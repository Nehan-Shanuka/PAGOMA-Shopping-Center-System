package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.controller.ProductGUIController;
import com.oop_cw.pase_01.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ShoppingCartDisplay extends JFrame {
    private JTextArea textAreaProductDetails1;
    private JTextArea textAreaProductDetails2;
    private JLabel totalTxtField;
    private JLabel txtFieldPrice;
    private JTextArea textAreaQuantity;
    private JTable shoppingCartDisplay;
    private JButton btnIncrement;
    private JButton btnDecrement;
    private JLabel discountTxtField1;
    private JLabel discountTxtField2;
    private JLabel finalTotalValue;
    TblShoppingCartModel shoppingCartDisplayModel;
    int selectedRow;
    ShoppingCart shoppingCart = ShoppingCart.getInstance();
    ProductList productList = ProductList.getInstance();
    UserList userList = UserList.getInstance();
    CurrentUser currentUser = CurrentUser.getInstance();

    public ShoppingCartDisplay() {

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // North Panel
        JPanel northPanel = new JPanel(new GridLayout(1,2));
        northPanel.setPreferredSize(new Dimension(900, 50));

        JPanel northPanel1 = new JPanel(new BorderLayout());
        northPanel1.setBackground(new Color(53, 94, 59, 255));
        northPanel1.setBorder(new EmptyBorder(10, 120, 10, 120));

        JPanel northPanel2 = new JPanel(new BorderLayout());
        northPanel2.setBackground(new Color(53, 94, 59, 255));
        northPanel2.setBorder(new EmptyBorder(10, 120, 10, 120));

        JButton btnBack = new JButton("MORE SHOPPING");
        btnBack.addActionListener(new MoreShoppingBtnEventListener());
        btnBack.setBackground((new Color(255, 255, 255, 255)));
        btnBack.setForeground((new Color(0, 0, 0, 204)));

        JButton btnPlaceOrder = new JButton("PLACE THE ORDER");
        btnPlaceOrder.addActionListener(new PlaceOrderBtnEventListener());
        btnPlaceOrder.setBackground((new Color(255, 255, 255, 255)));
        btnPlaceOrder.setForeground((new Color(0, 0, 0, 200)));

        northPanel1.add(btnBack);
        northPanel2.add(btnPlaceOrder);

        northPanel.add(northPanel1);
        northPanel.add(northPanel2);

        mainContainer.add(northPanel, BorderLayout.NORTH);


        // Central Panel
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Central sub panel 1
        JPanel centerPanel1 = new JPanel(new GridLayout());
        centerPanel1.setPreferredSize(new Dimension(900, 270));

        shoppingCartDisplayModel = new TblShoppingCartModel(shoppingCart.getShoppingCartList());
        shoppingCartDisplay = new JTable(shoppingCartDisplayModel);
        shoppingCartDisplay.getSelectionModel().addListSelectionListener(new TableSelectionListener());
        shoppingCartDisplay.setRowHeight(50);

        JScrollPane scrollPane = new JScrollPane(shoppingCartDisplay);
        centerPanel1.add(scrollPane);

        centerPanel.add(centerPanel1, BorderLayout.NORTH);

        // Central sub panel 2
        JPanel centerPanel2 = new JPanel(new GridLayout(1,3));

        JPanel centerPanel2Sub1 = new JPanel(new GridLayout(1, 2));
        centerPanel2Sub1.setBackground(new Color(53, 94, 59, 255));

        JPanel centerPanel2Sub2 = new JPanel(new GridLayout(2,1));
        JPanel centerPanel2Sub3 = new JPanel(new GridLayout(2,1));
        JPanel centerPanel2Sub4 = new JPanel(new BorderLayout());
        centerPanel2Sub4.setBorder(new EmptyBorder(16, 18, 16, 18));
        centerPanel2Sub4.setBackground(new Color(53, 94, 59, 255));

        centerPanel2.add(centerPanel2Sub1);
        centerPanel2.add(centerPanel2Sub2);
        centerPanel2.add(centerPanel2Sub3);
        centerPanel2.add(centerPanel2Sub4);

        textAreaProductDetails1 = new JTextArea();
        textAreaProductDetails2 = new JTextArea();
        textAreaProductDetails1.setFont(textAreaProductDetails1.getFont().deriveFont(15f));
        textAreaProductDetails2.setFont(textAreaProductDetails1.getFont().deriveFont(15f));
        textAreaProductDetails1.setBackground(new Color(53, 94, 59, 255));
        textAreaProductDetails2.setBackground(new Color(53, 94, 59, 255));
        textAreaProductDetails1.setForeground(Color.WHITE);
        textAreaProductDetails2.setForeground(Color.WHITE);
        textAreaProductDetails1.setEditable(false);
        textAreaProductDetails2.setEditable(false);

        centerPanel2Sub1.add(textAreaProductDetails1);
        centerPanel2Sub1.add(textAreaProductDetails2);

        JPanel lblBannerPanel = new JPanel();
        lblBannerPanel.setBackground(new Color(53, 94, 59, 255));

        JLabel lblBanner = new JLabel("SET THE QUANTITY");
        lblBanner.setFont(lblBanner.getFont().deriveFont(13f));
        lblBanner.setForeground(new Color(255, 255, 255, 255));

        lblBannerPanel.add(lblBanner);

        centerPanel2Sub2.add(lblBannerPanel);

        JPanel textAreaQuantityPanel = new JPanel(new GridLayout(1,3));

        JPanel textAreaQuantityPanel1 = new JPanel(new FlowLayout());
        textAreaQuantityPanel1.setBorder(new EmptyBorder(0, 10, 0, 10));

        JPanel textAreaQuantityPanel2 = new JPanel(new FlowLayout());
        JPanel textAreaQuantityPanel3 = new JPanel(new FlowLayout());
        textAreaQuantityPanel3.setBorder(new EmptyBorder(0, 10, 0, 10));

        textAreaQuantity = new JTextArea();
        textAreaQuantity.setFont(textAreaQuantity.getFont().deriveFont(20f));
        textAreaQuantity.setColumns(4);
        textAreaQuantity.setRows(4);

        btnIncrement = new JButton("+");
        btnIncrement.addActionListener(new IncrementAndReductionBtnAction());
        btnIncrement.setBackground(new Color(53, 94, 59, 255));
        btnIncrement.setForeground(new Color(255, 255, 255, 255));

        btnDecrement = new JButton("-");
        btnDecrement.addActionListener(new IncrementAndReductionBtnAction());
        btnDecrement.setBackground(new Color(53, 94, 59, 255));
        btnDecrement.setForeground(new Color(255, 255, 255, 255));

        textAreaQuantityPanel1.add(btnDecrement);
        textAreaQuantityPanel2.add(textAreaQuantity);
        textAreaQuantityPanel3.add(btnIncrement);

        textAreaQuantityPanel.add(textAreaQuantityPanel1);
        textAreaQuantityPanel.add(textAreaQuantityPanel2);
        textAreaQuantityPanel.add(textAreaQuantityPanel3);

        centerPanel2Sub2.add(textAreaQuantityPanel);

        JPanel lblUpdatedPriceBannerPanel = new JPanel();
        lblUpdatedPriceBannerPanel.setBackground(new Color(53, 94, 59, 255));

        JLabel lblUpdatedPriceBanner = new JLabel("UPDATED PRICE (LKR)");
        lblUpdatedPriceBanner.setFont(lblBanner.getFont().deriveFont(13f));
        lblUpdatedPriceBanner.setForeground(new Color(255, 255, 255, 255));

        lblUpdatedPriceBannerPanel.add(lblUpdatedPriceBanner);
        centerPanel2Sub3.add(lblUpdatedPriceBannerPanel);

        JPanel txtFieldPricePanel = new JPanel(new BorderLayout());
        JPanel txtFieldPriceInnerPanel = new JPanel(new FlowLayout());

        txtFieldPrice = new JLabel();
        txtFieldPrice.setFont(txtFieldPrice.getFont().deriveFont(20f));
        txtFieldPrice.setHorizontalTextPosition(JLabel.CENTER);
        txtFieldPrice.setVerticalTextPosition(JLabel.CENTER);

        txtFieldPriceInnerPanel.add(txtFieldPrice);
        txtFieldPricePanel.add(txtFieldPriceInnerPanel);
        centerPanel2Sub3.add(txtFieldPricePanel);

        JButton btnUpdate = new JButton("UPDATE THE CART");
        btnUpdate.addActionListener(new UpdateTableBtnEvent());
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setForeground(Color.BLACK);

        centerPanel2Sub4.add(btnUpdate);

        centerPanel.add(centerPanel2);

        mainContainer.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridLayout(1, 3));
        southPanel.setPreferredSize(new Dimension(900,180));

        JPanel southPanel1 = new JPanel(new GridLayout(6,1));
        JPanel southPanel2 = new JPanel(new GridLayout(6,1));

        JPanel southSubPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel southSubPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel southSubPanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel southSubPanel7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southSubPanel7.setBackground(new Color(53, 94, 59, 255));

        JPanel southSubPanel9 = new JPanel(new FlowLayout());
        JPanel southSubPanel10 = new JPanel(new FlowLayout());
        JPanel southSubPanel11 = new JPanel(new FlowLayout());
        JPanel southSubPanel12 = new JPanel(new FlowLayout());

        JPanel southSubPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel southSubPanel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel southSubPanel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel southSubPanel8 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel southPanelWest = new JPanel();

        JLabel totalLabel = new JLabel("Total Price");
        totalLabel.setFont(totalLabel.getFont().deriveFont(15f));

        JLabel firstPurchaseDisLabel = new JLabel("First Purchase Discount (10%)");
        firstPurchaseDisLabel.setFont(firstPurchaseDisLabel.getFont().deriveFont(15f));

        JLabel threeItemsDisLabel = new JLabel("Three Items in Same Category Discount (20%)");
        threeItemsDisLabel.setFont(threeItemsDisLabel.getFont().deriveFont(15f));

        JLabel finalTotalLbl = new JLabel("Final Total");
        finalTotalLbl.setFont(finalTotalLbl.getFont().deriveFont(15f));
        finalTotalLbl.setForeground(Color.WHITE);

        southSubPanel1.add(totalLabel);
        southSubPanel2.add(firstPurchaseDisLabel);
        southSubPanel3.add(threeItemsDisLabel);
        southSubPanel7.add(finalTotalLbl);

        totalTxtField = new JLabel();
        totalTxtField.setText(":             " + shoppingCart.cartTotalCalculate());
        totalTxtField.setFont(totalTxtField.getFont().deriveFont(15f));

        discountTxtField1 = new JLabel();
        discountTxtField1.setText(":             " + shoppingCart.firstPurchaseDisCalculate());
        discountTxtField1.setFont(discountTxtField1.getFont().deriveFont(15f));

        discountTxtField2 = new JLabel();
        discountTxtField2.setText(":             " + shoppingCart.threeSameProductDisCalculate());
        discountTxtField2.setFont(discountTxtField2.getFont().deriveFont(15f));

        finalTotalValue = new JLabel();
        finalTotalValue.setText(":             " + shoppingCart.finalCartTotalCalculate());
        finalTotalValue.setFont(finalTotalValue.getFont().deriveFont(15f));
        southSubPanel8.setBackground(new Color(53, 94, 59, 255));
        finalTotalValue.setForeground(Color.WHITE);

        southSubPanel4.add(totalTxtField);
        southSubPanel5.add(discountTxtField1);
        southSubPanel6.add(discountTxtField2);
        southSubPanel8.add(finalTotalValue);

        southPanel1.add(southSubPanel9);
        southPanel1.add(southSubPanel1);
        southPanel1.add(southSubPanel2);
        southPanel1.add(southSubPanel3);
        southPanel1.add(southSubPanel7);
        southPanel1.add(southSubPanel10);

        southPanel2.add(southSubPanel11);
        southPanel2.add(southSubPanel4);
        southPanel2.add(southSubPanel5);
        southPanel2.add(southSubPanel6);
        southPanel2.add(southSubPanel8);
        southPanel2.add(southSubPanel12);

        southPanel.add(southPanelWest);
        southPanel.add(southPanel1);
        southPanel.add(southPanel2);

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
        ProductGUIController newProductInstance = new ProductGUIController();
    }

    public class PlaceOrderBtnEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentUser.getUserStatus()
            ) {
                userList.incrementUserPurchase(currentUser.getUser().getUsername());
                userList.saveUserList();
            }

            shoppingCart.placeTheOrder();
            dispose();
        }
    }

    public class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent evt) {
            if (!evt.getValueIsAdjusting()) {
                // Get the selected row and display data in the JTextArea
                selectedRow = shoppingCartDisplay.getSelectedRow();
//                shoppingCart.setTempProductBuyCount(shoppingCart.getProductBuyCount());
//                System.out.println("Selection : "+selectedRow);
                if (selectedRow >= 0) {
                    String rowDataFirstHalf = getRowDataFirstHalf(selectedRow);
                    textAreaProductDetails1.setText(rowDataFirstHalf);

                    String rowDataSecondHalf = getRowDataSecondHalf(selectedRow);
                    textAreaProductDetails2.setText(rowDataSecondHalf);

                    textAreaQuantity.setText("     " + shoppingCart.getProductBuyCount().get(selectedRow));

                    txtFieldPrice.setText(getProductFromTable(selectedRow));
                }
            }
        }
    }

    public String getProductFromTable(int selectedRow) {

        return String.valueOf(shoppingCart.getShoppingCartList().get(selectedRow).getPrice()
                * Integer.parseInt(textAreaQuantity.getText().substring(5)));
    }

    private String getRowDataFirstHalf(int row) {
        StringBuilder rowData = new StringBuilder();
        for (Product item: shoppingCart.getShoppingCartList()) {

            String cellString = (String) shoppingCartDisplay.getValueAt(row,0);
            if (cellString.contains(item.getProductId())) {
                rowData.append(item.getProductId()).append("\n#")
                        .append(item.getProductName());
            }
        }
        return rowData.toString();
    }

    private String getRowDataSecondHalf(int row) {
        StringBuilder rowData = new StringBuilder();
        for (Product item: shoppingCart.getShoppingCartList()) {

            String cellString = (String) shoppingCartDisplay.getValueAt(row,0);
            if (cellString.contains(item.getProductId())) {
                if (item instanceof Electronics) {
                    rowData.append("\n#").append(((Electronics) item).getBrandName());
                }
                else if (item instanceof Clothing) {
                    rowData.append("\n#").append(((Clothing) item).getSize()).append("\n#")
                            .append(((Clothing) item).getColor());
                }
                break;
            }
        }
        return rowData.toString();
    }

    public class IncrementAndReductionBtnAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedRow = shoppingCartDisplay.getSelectedRow();

            if (selectedRow >= 0) {
                try {
                    if (e.getSource().equals(btnIncrement)) {

                        if ((shoppingCart.getTempProductBuyCount().get(selectedRow)
                                - Integer.parseInt(textAreaQuantity.getText().substring(5)) -1
                                >= 0)) {

                            textAreaQuantity.setText("     " + (Integer.parseInt
                                    (textAreaQuantity.getText().substring(5)) + 1));
                        }

                    } else if (e.getSource().equals(btnDecrement)) {

                        if (Integer.parseInt(textAreaQuantity.getText().substring(5)) != 0) {

                            textAreaQuantity.setText("     " + (Integer.parseInt
                                    (textAreaQuantity.getText().substring(5)) - 1));
                        }
                    }
                    txtFieldPrice.setText(String.valueOf(getProductFromTable(selectedRow)));

                } catch (IndexOutOfBoundsException eb) {
                    eb.printStackTrace();
                }
            }

        }
    }

    public class UpdateTableBtnEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                shoppingCart.getProductBuyCount().set(selectedRow,
                        Integer.parseInt(textAreaQuantity.getText().substring(5)));

                for (Product item: productList.getProductList()) {
                    if (Objects.equals(item.getProductId(),
                            shoppingCart.getShoppingCartList().get(selectedRow).getProductId())) {

                        item.setNumOfAvailability(shoppingCart.getTempProductBuyCount().get(selectedRow) -
                                Integer.parseInt(textAreaQuantity.getText().substring(5)));
                    }
                }

                shoppingCartDisplay.setModel(new TblShoppingCartModel(shoppingCart.getShoppingCartList()));
                totalTxtField.setText(":             " + shoppingCart.cartTotalCalculate());
                discountTxtField1.setText(":             " + shoppingCart.firstPurchaseDisCalculate());
                discountTxtField2.setText(":             " + shoppingCart.threeSameProductDisCalculate());
                finalTotalValue.setText(":             " + shoppingCart.finalCartTotalCalculate());

            } catch (IndexOutOfBoundsException ignored) {

            }

        }
    }
}
