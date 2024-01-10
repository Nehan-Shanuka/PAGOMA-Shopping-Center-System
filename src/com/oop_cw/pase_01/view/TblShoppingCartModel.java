package com.oop_cw.pase_01.view;

import com.oop_cw.pase_01.model.Clothing;
import com.oop_cw.pase_01.model.Electronics;
import com.oop_cw.pase_01.model.Product;
import com.oop_cw.pase_01.model.ShoppingCart;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TblShoppingCartModel extends AbstractTableModel {
    private String[] columnNames = {"Product", "Quantity", "Price(LKR)"};
    private ArrayList<Product> productArrayList;
    ShoppingCart shoppingCart = ShoppingCart.getInstance();

    public TblShoppingCartModel(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @Override
    public int getRowCount() {
        return shoppingCart.getShoppingCartList().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StringBuilder temp = new StringBuilder();
        if (columnIndex == 0) {
            if (shoppingCart.getShoppingCartList().get(rowIndex) instanceof Electronics) {
                temp.append(shoppingCart.getShoppingCartList().get(rowIndex).getProductId()).append("<br>")
                        .append(shoppingCart.getShoppingCartList().get(rowIndex).getProductName()).append("<br>")
                        .append(((Electronics) shoppingCart.getShoppingCartList().get(rowIndex)).getBrandName())
                        .append(", ").append(
                                ((Electronics) shoppingCart.getShoppingCartList().get(rowIndex)).getDaysOfWarranty());
            } else {
                temp.append(shoppingCart.getShoppingCartList().get(rowIndex).getProductId()).append("<br>")
                        .append(shoppingCart.getShoppingCartList().get(rowIndex).getProductName()).append("<br>")
                        .append(((Clothing) shoppingCart.getShoppingCartList().get(rowIndex)).getSize())
                        .append(", ").append(
                                ((Clothing) shoppingCart.getShoppingCartList().get(rowIndex)).getColor());
            }
        } else if (columnIndex == 1) {
            temp.append(shoppingCart.getProductBuyCount().get(rowIndex));
        } else if (columnIndex == 2) {
            temp.append(shoppingCart.getShoppingCartList().get(rowIndex).getPrice()*
                    shoppingCart.getProductBuyCount().get(rowIndex));
        }
        return "<html>" + temp + "<html>";
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
}
