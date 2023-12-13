package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.controller.ShoppingCart;
import com.oop_cw.pase_01.controller.ShoppingCartGUIController;
import com.oop_cw.pase_01.model.Clothing;
import com.oop_cw.pase_01.model.Electronics;
import com.oop_cw.pase_01.model.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class ProductDisplay extends JFrame {

    private JButton btnShoppingCart;
    private JTable tblProductDisplay;
    private JTextArea txtAreaProductDescription;
    private JButton btnAddToCart;
    private ArrayList<Product> shoppingCartArrayList = new ArrayList<>();
    ShoppingCart newInstance = new ShoppingCart(shoppingCartArrayList);

    public ProductDisplay(ArrayList<Product> productList, ArrayList<Product> shoppingCartArrayList) {

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
        JPanel southPanel = new JPanel(new GridLayout());
        mainContainer.add(southPanel, BorderLayout.SOUTH);
//        southPanel.setBackground(Color.BLACK);

        JPanel southSubPanel1 = new JPanel(new FlowLayout());
//        southSubPanel1.setBackground(Color.CYAN);
        southSubPanel1.setPreferredSize(new Dimension(600,170));
        southPanel.add(southSubPanel1, BorderLayout.WEST);

        JPanel southSubPanel2 = new JPanel(new FlowLayout());
//        southSubPanel2.setBackground(Color.GREEN);
        southPanel.add(southSubPanel2, BorderLayout.WEST);

        // JLabel to describe the dropdown selection
        JLabel lblCategorySelection = new JLabel("Select Product Category");
        northSubPanel1.add(lblCategorySelection);

        String[] dropdownList = {"All", "Electronic", "Clothing"};

        // Initialising the dropdown selection with three selections
        JComboBox<String> dropdown = new JComboBox<>(dropdownList);
        northSubPanel1.add(dropdown);

        // JButton for rendering the shopping cart scene
        btnShoppingCart = new JButton("Shopping Cart");
        northSubPanel2.add(btnShoppingCart);
        System.out.println(shoppingCartArrayList);
        btnShoppingCart.addActionListener(new ShoppingCartBtnEventListener((ArrayList<Product>) newInstance.getShoppingCartList()));

        // Initialising table model, JTable and JScrollPane and adding them
        TblProductDisplay tblProductDisplayModel = new TblProductDisplay(productList);
        tblProductDisplay = new JTable(tblProductDisplayModel);
        tblProductDisplay.getSelectionModel().addListSelectionListener(new TableSelectionListener(productList));
        tblProductDisplay.setRowHeight(20);

        JScrollPane jScrollPane = new JScrollPane(tblProductDisplay);
        centerPanel.add(jScrollPane);

        // Initialising the JTextArea and adding it into the southPanel
        txtAreaProductDescription = new JTextArea("Selected Product Details");
        txtAreaProductDescription.setColumns(40);
        txtAreaProductDescription.setRows(10);
        txtAreaProductDescription.setEditable(false);
        southSubPanel1.add(txtAreaProductDescription);

        btnAddToCart = new JButton("Add to Cart");
        southSubPanel2.add(btnAddToCart);
        btnAddToCart.addActionListener(new ShoppingCartDetails(productList));
    }

    public void openShoppingCart(ArrayList<Product> shoppingCartArrayList) {
        System.out.println(newInstance.getShoppingCartList());
        ShoppingCartGUIController shoppingCartDisplay = new ShoppingCartGUIController((ArrayList<Product>) newInstance.getShoppingCartList());
    }

    public class ShoppingCartBtnEventListener implements ActionListener {
        private ArrayList<Product> productArrayList;
        public ShoppingCartBtnEventListener(ArrayList<Product> productArrayList) {
            this.productArrayList = productArrayList;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            openShoppingCart(shoppingCartArrayList);
            dispose();
        }
    }

    public class TableSelectionListener implements ListSelectionListener {
        ArrayList<Product> productArrayList;
        public TableSelectionListener(ArrayList<Product> productArrayList) {
            this.productArrayList = productArrayList;
        }

        @Override
        public void valueChanged(ListSelectionEvent evt) {
            if (!evt.getValueIsAdjusting()) {
                // Get the selected row and display data in the JTextArea
                int selectedRow = tblProductDisplay.getSelectedRow();
                if (selectedRow >= 0) {
                    String rowData = getRowData(tblProductDisplay, selectedRow);
                    txtAreaProductDescription.setText(rowData);
                }
            }
        }
        private String getRowData(JTable table, int row) {
            StringBuilder rowData = new StringBuilder("""
                    Selected Product Details
                    
                    """);
            
            for (Product item : productArrayList) {
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
    }

    public class ShoppingCartDetails implements ActionListener {
        ArrayList<Product> productArrayList;
        ArrayList<Product> shoppingCartArrayList;
        public ShoppingCartDetails(ArrayList<Product> productArrayList) {
            this.productArrayList = productArrayList;
            this.shoppingCartArrayList = new ArrayList<>();
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            int selectedRow = tblProductDisplay.getSelectedRow();

            if (selectedRow >= 0) {
                String productId = (String) tblProductDisplay.getValueAt(selectedRow, 0);
                for (Product item : productArrayList) {
                    if (Objects.equals(productId, item.getProductId())) {
                        newInstance.addProduct(item);
                        shoppingCartArrayList.add(item);
                        break;
                    }
                }
            }
        }
    }
}