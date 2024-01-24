package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.controller.ShoppingCartGUIController;
import com.oop_cw.pase_01.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

public class ProductDisplay extends JFrame {
    private JPanel centerPanel;
    private JButton btnShoppingCart;
    private JTable tblProductDisplay;
    private JTextArea txtAreaProductDescription;
    private JButton btnAddToCart;
    private JComboBox<String> dropdown;
    int selectedRow;
    //    private ArrayList<Product> shoppingCartArrayList = new ArrayList<>();
//    ShoppingCartController newShoppingCartInstance = new ShoppingCartController(shoppingCartArrayList);
    ProductList productListNew = ProductList.getInstance();
    ShoppingCart shoppingCart = ShoppingCart.getInstance();

    public ProductDisplay() {

        // Initialising the container and setting a layout
        Container mainContainer = getContentPane();
//        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setLayout(new BorderLayout());

        // JPanel for contain both dropdown list and shopping cart btn
        JPanel northPanel = new JPanel(new GridLayout(1, 2));
        northPanel.setPreferredSize(new Dimension(900, 50));

        // JPanel for hold the dropdown selection and its label
        JPanel northSubPanel1 = new JPanel(new GridLayout());
        northSubPanel1.setBackground(new Color(53, 94, 59, 255));
//        northSubPanel1.setBorder(new EmptyBorder(10, 120, 10, 120));

        JPanel northPanel1SubPanel1 = new JPanel(new GridLayout());
        northPanel1SubPanel1.setBackground(new Color(53, 94, 59, 255));
        northPanel1SubPanel1.setForeground(Color.WHITE);
        northPanel1SubPanel1.setBorder(new EmptyBorder(10, 35, 10, 0));
        JPanel northPanel1SubPanel2 = new JPanel(new FlowLayout());
        northPanel1SubPanel2.setBackground(new Color(53, 94, 59, 255));
        northPanel1SubPanel2.setBorder(new EmptyBorder(7, 0, 13, 80));

        northSubPanel1.add(northPanel1SubPanel1);
        northSubPanel1.add(northPanel1SubPanel2);

        // JPanel for hold the btn for shopping cart
        JPanel northSubPanel2 = new JPanel(new BorderLayout());
        northSubPanel2.setBackground(new Color(53, 94, 59, 255));
        northSubPanel2.setBorder(new EmptyBorder(10, 120, 10, 120));

        // JLabel to describe the dropdown selection
        JLabel lblCategorySelection = new JLabel("SELECT PRODUCT CATEGORY");
        lblCategorySelection.setForeground(Color.WHITE);

        String[] dropdownList = {"ALL", "ELECTRONIC", "CLOTHING"};
        // Initialising the dropdown selection with three selections
        dropdown = new JComboBox<>(dropdownList);
        dropdown.addItemListener(new DropdownSelectionEventListener());

        // JButton for rendering the shopping cart scene
        btnShoppingCart = new JButton("SHOPPING CART");
        btnShoppingCart.addActionListener(new ShoppingCartBtnEventListener());
        btnShoppingCart.setBackground((new Color(255, 255, 255, 255)));
        btnShoppingCart.setForeground((new Color(0, 0, 0, 200)));

        northPanel1SubPanel1.add(lblCategorySelection);
        northPanel1SubPanel2.add(dropdown);
        northSubPanel2.add(btnShoppingCart);

        northPanel.add(northSubPanel1, BorderLayout.WEST);
        northPanel.add(northSubPanel2, BorderLayout.EAST);

        mainContainer.add(northPanel, BorderLayout.NORTH);


        // JPanel for hold the JTable, and it centered in between north and south panels
        centerPanel = new JPanel(new GridLayout());
        centerPanel.setPreferredSize(new Dimension(900,400));

        // Initialising table model, JTable and JScrollPane and adding them
        TblProductModel tblProductModelModel = new TblProductModel(productListNew.getProductList());
        tblProductDisplay = new JTable(tblProductModelModel);
        tblProductDisplay.setRowHeight(30);
        tblProductDisplay.getSelectionModel().addListSelectionListener(new TableSelectionListener());
//        tblProductDisplay.getColumnModel().getColumn(0).
//                setCellRenderer(new AvailabilityCellRenderer(productList));
        tblProductDisplay.setDefaultRenderer(Object.class, new AvailabilityCellRenderer());

        JScrollPane jScrollPane = new JScrollPane(tblProductDisplay);
        centerPanel.add(jScrollPane);

        mainContainer.add(centerPanel, BorderLayout.CENTER);


        // JPanel for hold the textarea, and it placed in the bottom of the container
        JPanel southPanel = new JPanel(new GridLayout());
        southPanel.setPreferredSize(new Dimension(900,180));

        JPanel southSubPanel1 = new JPanel(new BorderLayout());
        JPanel southSubPanel2 = new JPanel(new BorderLayout());
        southSubPanel2.setBackground(new Color(53, 94, 59, 255));
        southSubPanel2.setBorder(new EmptyBorder(50, 80, 50, 80));

        JPanel southSubPanel1Sub1 = new JPanel(new FlowLayout());
        southSubPanel1Sub1.setBackground(new Color(53, 94, 59, 255));

        // Initialising the JTextArea and adding it into the southPanel
        JLabel lblProductDetailBanner = new JLabel("SELECTED PRODUCT DETAIL");
        lblProductDetailBanner.setForeground(Color.WHITE);

        southSubPanel1Sub1.add(lblProductDetailBanner);
        southSubPanel1.add(southSubPanel1Sub1, BorderLayout.NORTH);
        southSubPanel1.setPreferredSize(new Dimension(450, 20));

        txtAreaProductDescription = new JTextArea();
        txtAreaProductDescription.setRows(10);
        Font fontSize = txtAreaProductDescription.getFont().deriveFont(14f);
        txtAreaProductDescription.setFont(fontSize);
        txtAreaProductDescription.setAlignmentX(Component.RIGHT_ALIGNMENT);
        txtAreaProductDescription.setEditable(false);

        btnAddToCart = new JButton("ADD TO CART");
        btnAddToCart.addActionListener(new ShoppingCartDetails());
        btnAddToCart.setFont(btnAddToCart.getFont().deriveFont(18f));
        btnAddToCart.setBackground((new Color(255, 255, 255, 255)));
        btnAddToCart.setForeground((new Color(0, 0, 0, 200)));

        southSubPanel1.add(txtAreaProductDescription);
        southSubPanel2.add(btnAddToCart);

        southPanel.add(southSubPanel1, BorderLayout.WEST);
        southPanel.add(southSubPanel2, BorderLayout.EAST);

        mainContainer.add(southPanel, BorderLayout.SOUTH);
    }

    // public method for JComboBox which implements ItemListener
    // This updates the JTable according to JComboBox selection
    public class DropdownSelectionEventListener implements ItemListener {
        // The abstract method for ItemListener
        @Override
        public void itemStateChanged(ItemEvent e) {
            // Check whether the JComboBox selection change or not
            if ((e.getStateChange()) == ItemEvent.SELECTED) {
                // If change the selection of the JComboBox get the index of the selection
                int selection = dropdown.getSelectedIndex();
                switch (selection) {
                    // Update the JTable according to selection
                    case 0:
                        updateTable(productListNew.getProductList());
                        break;
                    case 1:
                        // Initialise new arraylist to hold Electronic Products
                        ArrayList<Product> electronicProductList = new ArrayList<>();
                        // Add arraylist to the filtered Electronic Products
                        for (Product item: productListNew.getProductList()) {
                            if (item instanceof Electronics) {
                                electronicProductList.add(item);
                            }
                        }
                        // Calling the method to update the JTable
                        updateTable(electronicProductList);
                        break;
                    case 2:
                        // Initialise new arraylist to hold Electronic Products
                        ArrayList<Product> clothingProductList = new ArrayList<>();
                        // Add arraylist to the filtered Electronic Products
                        for (Product item: productListNew.getProductList()) {
                            if (item instanceof Clothing) {
                                clothingProductList.add(item);
                            }
                        }
                        // Calling the method to update the JTable
                        updateTable(clothingProductList);
                        break;
                }
            }
        }
    }

    // A public method for updating the table
    public void updateTable(ArrayList<Product> newList) {
        // Initialising a new table model
        TblProductModel tblProductModelModel = new TblProductModel(newList);
        // Setting new table model for the JTable
        tblProductDisplay.setModel(tblProductModelModel);
    }

    public class ShoppingCartBtnEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openShoppingCart();
            dispose();
        }
    }

    public void openShoppingCart() {
        ShoppingCartGUIController shoppingCartDisplay = new ShoppingCartGUIController();
    }

    public class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent evt) {
            if (!evt.getValueIsAdjusting()) {
                // Get the selected row and display data in the JTextArea
                selectedRow = tblProductDisplay.getSelectedRow();

                if (selectedRow >= 0) {
                    String rowData = getRowData(tblProductDisplay, selectedRow);
                    txtAreaProductDescription.setText(rowData);
                }
            }
        }
    }
    private String getRowData(JTable table, int row) {
        StringBuilder rowData = new StringBuilder("""
                    
                    """);
        for (Product item : productListNew.getProductList()) {
            if (item.getProductId() == table.getValueAt(row, 0)) {
                if (item instanceof Electronics) {
                    rowData.append("        ").append("Product Id : ").append(item.getProductId()).append("\n")
                            .append("        ").append("Category : Electronic").append("\n")
                            .append("        ").append("Name : ").append(item.getProductName()).append("\n")
                            .append("        ").append("Brand : ").append(((Electronics) item).getBrandName()).append("\n")
                            .append("        ").append("Warranty Period : ").append(((Electronics) item).getDaysOfWarranty())
                            .append("\n")
                            .append("        ").append("Items Available : ").append(item.getNumOfAvailability())
                            .append("\n");
                }
                else if (item instanceof Clothing) {
                    rowData.append("        ").append("Product Id : ").append(item.getProductId()).append("\n")
                            .append("        ").append("Category : Clothing").append("\n")
                            .append("        ").append("Name : ").append(item.getProductName()).append("\n")
                            .append("        ").append("Size : ").append(((Clothing) item).getSize()).append("\n")
                            .append("        ").append("Color : ").append(((Clothing) item).getColor())
                            .append("\n")
                            .append("        ").append("Items Available : ").append(item.getNumOfAvailability())
                            .append("\n");
                }
                break;
            }
        }
        return rowData.toString();
    }

    public class ShoppingCartDetails implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (selectedRow >= 0) {
                String productId = (String) tblProductDisplay.getValueAt(selectedRow, 0);
                for (Product item : productListNew.getProductList()) {

                    if (Objects.equals(productId, item.getProductId())) {
                        item.setNumOfAvailability(item.getNumOfAvailability()-1);
                        txtAreaProductDescription.setText(getRowData(tblProductDisplay,selectedRow));

                        if (!shoppingCart.getShoppingCartList().isEmpty()) {

                            for (int i = 0; i < shoppingCart.getShoppingCartList().size(); i++) {

                                if (shoppingCart.getShoppingCartList().get(i) == item) {
                                    shoppingCart.incrementProductBuyCount(i);
//                                    shoppingCart.addToTempProductList(i, item.getNumOfAvailability()+1);
                                    break;

                                } else if (i == shoppingCart.getShoppingCartList().size()-1) {
                                    shoppingCart.addToCart(item);
                                    shoppingCart.getProductBuyCount().add(1);
                                    shoppingCart.getTempProductBuyCount().add(item.getNumOfAvailability()+1);
                                    break;
                                }
                            }
                        } else {
                            shoppingCart.addToCart(item);
                            shoppingCart.getProductBuyCount().add(1);
                            shoppingCart.addToTempProductList(0, item.getNumOfAvailability()+1);
                        }
                        break;
                    }
                }
            }
        }
    }

    // Custom cell renderer to highlight rows with reduced availability in red
    private class AvailabilityCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(
                    tblProductDisplay, value, isSelected, hasFocus, row, column);

            String productId = (String) table.getModel().getValueAt(row, 0);
            int availability = 0;

            for (Product item: productListNew.getProductList()) {
                if (Objects.equals(productId, item.getProductId())) {
                    availability = item.getNumOfAvailability();
                }
            }

            // Check if availability is less than 3 and set foreground color to red
            if (availability < 3) {
                component.setBackground(Color.RED);
                component.setForeground(Color.WHITE);
            } else {
                component.setBackground(table.getBackground());
                component.setForeground(table.getForeground());
            }

            return component;
        }
    }
}