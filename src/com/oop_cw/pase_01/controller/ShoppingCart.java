package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.model.Product;

import java.util.List;

public class ShoppingCart {
    private List<Product> shoppingCartList;

    // A public method to add products to the list
    public void addProduct(Product product) {
        shoppingCartList.add(product);
    }

    // A public method to remove the products from the list
    public void removeProduct(Product product) {
        shoppingCartList.remove(product);
    }

    // A public method to calculate the sum of the prices of ordered products
    public double sumOfCart() {
        // Initialising the sumOfCart var to hold the sum
        double sumOfCart = 0;

        // Iterating through the shoppingCartList and add the price of each product to the sumOfCart var
        for (Product product : shoppingCartList) {
            sumOfCart += product.getPrice();
        }
        // Returning the sum
        return sumOfCart;
    }
}
