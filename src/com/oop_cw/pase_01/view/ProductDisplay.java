package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.controller.ShoppingCartController;
import com.oop_cw.pase_01.controller.ShoppingCartGUIController;
import com.oop_cw.pase_01.model.*;

import javax.swing.*;
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
        JPanel northPanel = new JPanel(new GridLayout());

        // JPanel for hold the dropdown selection and its label
        JPanel northSubPanel1 = new JPanel(new FlowLayout());
        // JPanel for hold the btn for shopping cart
        JPanel northSubPanel2 = new JPanel();

        // JLabel to describe the dropdown selection
        JLabel lblCategorySelection = new JLabel("Select Product Category");

        String[] dropdownList = {"All", "Electronic", "Clothing"};
        // Initialising the dropdown selection with three selections
        dropdown = new JComboBox<>(dropdownList);
        dropdown.addItemListener(new DropdownSelectionEventListener());

        // JButton for rendering the shopping cart scene
        btnShoppingCart = new JButton("Shopping Cart");
        btnShoppingCart.addActionListener(new ShoppingCartBtnEventListener());

        northSubPanel1.add(lblCategorySelection);
        northSubPanel1.add(dropdown);
        northSubPanel2.add(btnShoppingCart);

        northPanel.add(northSubPanel1, BorderLayout.WEST);
        northPanel.add(northSubPanel2, BorderLayout.EAST);

        mainContainer.add(northPanel, BorderLayout.NORTH);



        // JPanel for hold the JTable, and it centered in between north and south panels
        centerPanel = new JPanel(new GridLayout());
        centerPanel.setPreferredSize(new Dimension(900,500));

        // Initialising table model, JTable and JScrollPane and adding them
        TblProductModel tblProductModelModel = new TblProductModel();
        tblProductDisplay = new JTable(tblProductModelModel);
        tblProductDisplay.setRowHeight(23);
        tblProductDisplay.getSelectionModel().addListSelectionListener(new TableSelectionListener());
//        tblProductDisplay.getColumnModel().getColumn(0).
//                setCellRenderer(new AvailabilityCellRenderer(productList));
        tblProductDisplay.setDefaultRenderer(Object.class, new AvailabilityCellRenderer());

        JScrollPane jScrollPane = new JScrollPane(tblProductDisplay);
        centerPanel.add(jScrollPane);

        mainContainer.add(centerPanel, BorderLayout.CENTER);


        // JPanel for hold the textarea, and it placed in the bottom of the container
        JPanel southPanel = new JPanel(new GridLayout());

        JPanel southSubPanel1 = new JPanel(new GridLayout());
        JPanel southSubPanel2 = new JPanel(new FlowLayout());

        // Initialising the JTextArea and adding it into the southPanel
        txtAreaProductDescription = new JTextArea("Selected Product Details");
        txtAreaProductDescription.setRows(10);
        Font fontSize = txtAreaProductDescription.getFont().deriveFont(14f);
        txtAreaProductDescription.setFont(fontSize);
        txtAreaProductDescription.setAlignmentX(Component.RIGHT_ALIGNMENT);
        txtAreaProductDescription.setEditable(false);

        btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.addActionListener(new ShoppingCartDetails());

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
        TblProductModel tblProductModelModel = new TblProductModel();
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
                    Selected Product Details
                    
                    """);
        for (Product item : productListNew.getProductList()) {
            if (item.getProductId() == table.getValueAt(row, 0)) {
                if (item instanceof Electronics) {
                    rowData.append("Product Id : ").append(item.getProductId()).append("\n")
                            .append("Category : Electronic").append("\n")
                            .append("Name : ").append(item.getProductName()).append("\n")
                            .append("Brand : ").append(((Electronics) item).getBrandName()).append("\n")
                            .append("Warranty Period : ").append(((Electronics) item).getDaysOfWarranty())
                            .append("\n")
                            .append("Items Available : ").append(item.getNumOfAvailability())
                            .append("\n");
                }
                else if (item instanceof Clothing) {
                    rowData.append("Product Id : ").append(item.getProductId()).append("\n")
                            .append("Category : Clothing").append("\n")
                            .append("Name : ").append(item.getProductName()).append("\n")
                            .append("Size : ").append(((Clothing) item).getSize()).append("\n")
                            .append("Color : ").append(((Clothing) item).getColor())
                            .append("\n")
                            .append("Items Available : ").append(item.getNumOfAvailability())
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
                                    break;

                                } else if (i == shoppingCart.getShoppingCartList().size()-1) {
                                    shoppingCart.addToCart(item);
                                    shoppingCart.getProductBuyCount().add(1);
                                    break;
                                }
                            }
                        }else {
                            shoppingCart.addToCart(item);
                            shoppingCart.getProductBuyCount().add(1);
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
            Component component = super.getTableCellRendererComponent(tblProductDisplay, value, isSelected, hasFocus, row, column);

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