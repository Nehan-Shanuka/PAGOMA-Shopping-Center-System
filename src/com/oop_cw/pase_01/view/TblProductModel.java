package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.model.Clothing;
import com.oop_cw.pase_01.model.Electronics;
import com.oop_cw.pase_01.model.Product;
import com.oop_cw.pase_01.model.ProductList;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TblProductModel extends AbstractTableModel {

    private String[] columnName = {"Product Id", "Name", "Category", "Price(LKR)", "Info"};

    ProductList productList = ProductList.getInstance();

    public TblProductModel() {
    }

    @Override
    public int getRowCount() {
        return productList.getProductList().size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (columnIndex == 0) {
            temp = productList.getProductList().get(rowIndex).getProductId();
        } else if (columnIndex == 1) {
            temp = productList.getProductList().get(rowIndex).getProductName();
        } else if (columnIndex == 2) {
            if (productList.getProductList().get(rowIndex) instanceof Electronics) {
                temp = "Electronic";
            }
            else {
                temp = "Clothing";
            }
        } else if (columnIndex == 3) {
            temp = productList.getProductList().get(rowIndex).getPrice();
        } else if (columnIndex == 4) {
            if (productList.getProductList().get(rowIndex) instanceof Electronics) {
                temp = ((Electronics) productList.getProductList().get(rowIndex)).getBrandName() +
                        ", " + ((Electronics) productList.getProductList().get(rowIndex)).getDaysOfWarranty();
            } else {
                temp = ((Clothing) productList.getProductList().get(rowIndex)).getSize() +
                        ", "  + ((Clothing) productList.getProductList().get(rowIndex)).getColor();
            }
        }
        return temp;
    }

    public String getColumnName(int col) {
        return columnName[col];
    }
}
