package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.model.Clothing;
import com.oop_cw.pase_01.model.Electronics;
import com.oop_cw.pase_01.model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TblProductDisplay extends AbstractTableModel {

    private String[] columnName = {"Product Id", "Name", "Category", "Price(LKR)", "Info"};

    private List<Product> productList;

    public TblProductDisplay(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getRowCount() {
        return productList.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (columnIndex == 0) {
            temp = productList.get(rowIndex).getProductId();
        } else if (columnIndex == 1) {
            temp = productList.get(rowIndex).getProductName();
        } else if (columnIndex == 2) {
            if (productList.get(rowIndex) instanceof Electronics) {
                temp = "Electronic";
            }
            else {
                temp = "Clothing";
            }
        } else if (columnIndex == 3) {
            temp = productList.get(rowIndex).getPrice();
        } else if (columnIndex == 4) {
            if (productList.get(rowIndex) instanceof Electronics) {
                temp = ((Electronics) productList.get(rowIndex)).getBrandName() +
                        ", " + ((Electronics) productList.get(rowIndex)).getDaysOfWarranty();
            } else {
                temp = ((Clothing) productList.get(rowIndex)).getSize() +
                        ", "  + ((Clothing) productList.get(rowIndex)).getColor();
            }
        }
        return temp;
    }

    public String getColumnName(int col) {
        return columnName[col];
    }
}
