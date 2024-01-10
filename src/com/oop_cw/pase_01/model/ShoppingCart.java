package com.oop_cw.pase_01.model;

import com.oop_cw.pase_01.controller.UserLoginController;
import com.oop_cw.pase_01.controller.WestminsterShoppingManager;
import com.oop_cw.pase_01.view.UserLogin;

import java.util.ArrayList;

public class ShoppingCart {

    private static ShoppingCart instance;
    private ArrayList<Product> shoppingCartList = new ArrayList<>();
    private ArrayList<Integer> productBuyCount = new ArrayList<>();
    private double cartTotal;
    private double firstPurchaseDis;
    private double threeSameProductDis;

    public ShoppingCart() {}

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addToCart(Product product) {
        shoppingCartList.add(product);
    }

    public ArrayList<Product> getShoppingCartList() {
        return shoppingCartList;
    }

    public ArrayList<Integer> getProductBuyCount() {
        return productBuyCount;
    }

    public void incrementProductBuyCount(int itemIndex) {
        productBuyCount.set(itemIndex, productBuyCount.get(itemIndex)+1);
    }

    public double cartTotalCalculate() {
        cartTotal = 0;
        for (int i = 0; i < shoppingCartList.size(); i++) {
            cartTotal += shoppingCartList.get(i).getPrice() * productBuyCount.get(i);
        }
        return cartTotal;
    }

    public double firstPurchaseDisCalculate() {
        CurrentUser currentUserInstance = CurrentUser.getInstance();
        if (currentUserInstance.getUserStatus()) {
            return firstPurchaseDis = -(cartTotal * 10) / 100;
        } else {
            return firstPurchaseDis = -0;
        }
    }

    public double threeSameProductDisCalculate() {
        int electronicCount = 0;
        int clothingCount = 0;

        for (Product item: shoppingCartList) {
            if (item instanceof Electronics) {
                electronicCount += 1;
            }
            else {
                clothingCount += 1;
            }
        }
        if (electronicCount >= 3 || clothingCount >= 3) {
            return threeSameProductDis = -(cartTotal * 20) / 100;
        } else {
            return threeSameProductDis = 0;
        }
    }
    public double finalCartTotalCalculate() {
        return cartTotal + (firstPurchaseDis + threeSameProductDis);
    }
    public void placeTheOrder() {
        ProductList productListInstance = ProductList.getInstance();
        productListInstance.saveProductList();
        UserLoginController userLoginController = new UserLoginController();
    }
}
