package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.model.Clothing;
import com.oop_cw.pase_01.model.Electronics;
import com.oop_cw.pase_01.model.Product;
import com.oop_cw.pase_01.model.ProductList;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TblProductModel extends AbstractTableModel {
    private String[] columnName = {"Product Id", "Name", "Category", "Price(LKR)", "Info"};
    private ArrayList<Product> productArrayList = new ArrayList<>();

    ProductList productList = ProductList.getInstance();

    public TblProductModel(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @Override
    public int getRowCount() {
        return productArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (columnIndex == 0) {
            temp = productArrayList.get(rowIndex).getProductId();
        } else if (columnIndex == 1) {
            temp = productArrayList.get(rowIndex).getProductName();
        } else if (columnIndex == 2) {
            if (productArrayList.get(rowIndex) instanceof Electronics) {
                temp = "Electronic";
            }
            else {
                temp = "Clothing";
            }
        } else if (columnIndex == 3) {
            temp = productArrayList.get(rowIndex).getPrice();
        } else if (columnIndex == 4) {
            if (productArrayList.get(rowIndex) instanceof Electronics) {
                temp = ((Electronics) productArrayList.get(rowIndex)).getBrandName() +
                        ", " + ((Electronics) productArrayList.get(rowIndex)).getDaysOfWarranty();
            } else {
                temp = ((Clothing) productArrayList.get(rowIndex)).getSize() +
                        ", "  + ((Clothing) productArrayList.get(rowIndex)).getColor();
            }
        }
        return temp;
    }

    public String getColumnName(int col) {
        return columnName[col];
    }
}
