package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.model.Clothing;
import com.oop_cw.pase_01.model.Electronics;
import com.oop_cw.pase_01.model.Product;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class TblShoppingCartDisplay extends AbstractTableModel {
    private String[] columnNames = {"Product", "Quantity", "Price(LKR)"};
    private ArrayList<Product> productArrayList;

    public TblShoppingCartDisplay(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @Override
    public int getRowCount() {
        return productArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StringBuilder temp = new StringBuilder();
        if (columnIndex == 0) {
            if (productArrayList.get(rowIndex) instanceof Electronics) {
                temp.append(productArrayList.get(rowIndex).getProductId()).append("<br>")
                        .append(productArrayList.get(rowIndex).getProductName()).append("<br>")
                        .append(((Electronics) productArrayList.get(rowIndex)).getBrandName())
                        .append(", ").append(((Electronics) productArrayList.get(rowIndex)).getDaysOfWarranty());
            } else {
                temp.append(productArrayList.get(rowIndex).getProductId()).append("<br>")
                        .append(productArrayList.get(rowIndex).getProductName()).append("<br>")
                        .append(((Clothing) productArrayList.get(rowIndex)).getSize())
                        .append(", ").append(((Clothing) productArrayList.get(rowIndex)).getColor());
            }
        } else if (columnIndex == 1) {
            temp.append(productArrayList.get(rowIndex).getNumOfAvailability());
        } else if (columnIndex == 2) {
            temp.append(productArrayList.get(rowIndex).getPrice());
        }
        return "<html>" + temp + "<html>";
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
}
