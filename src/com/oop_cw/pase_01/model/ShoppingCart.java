package com.oop_cw.pase_01.model;

import com.oop_cw.pase_01.controller.UserLoginController;

import java.util.ArrayList;

public class ShoppingCart {

    private static ShoppingCart instance;
    private static ArrayList<Product> shoppingCartList = new ArrayList<>();
    private static ArrayList<Integer> productBuyCount = new ArrayList<>();
    private static ArrayList<Integer> tempProductBuyCount = new ArrayList<>();
    private double cartTotal;
    private double firstPurchaseDis;
    private double threeSameProductDis;
//    private int selectedRow;

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

    public void resetShoppingCartList() {
        shoppingCartList = new ArrayList<>();
    }

    public void resetProductBuyCountList() {
        productBuyCount = new ArrayList<>();
    }

    public void resetTempProductBuyCountList() {
        tempProductBuyCount = new ArrayList<>();
    }

    public void setProductBuyCount(ArrayList<Integer> arrayList) {
        productBuyCount = arrayList;
    }

    public ArrayList<Integer> getProductBuyCount() {
        return productBuyCount;
    }

    public void addToTempProductList(int index, int count) {
        tempProductBuyCount.add(index, count);
    }

    public ArrayList<Integer> getTempProductBuyCount() {
        return tempProductBuyCount;
    }

    public void setTempProductBuyCount(ArrayList<Integer> arrayList) {
        tempProductBuyCount = arrayList;
    }

    public void incrementProductBuyCount(int itemIndex) {
        productBuyCount.set(itemIndex, productBuyCount.get(itemIndex) + 1);
    }

    public void reductionProductBuyCount(int itemIndex) {
        productBuyCount.set(itemIndex, productBuyCount.get(itemIndex) - 1);
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
        if (currentUserInstance.getUserStatus() && currentUserInstance.getUser().getPurchaseHistory() == 0) {
            return firstPurchaseDis = -(cartTotal * 10) / 100;
        } else {
            return firstPurchaseDis = -0;
        }
    }

    public double threeSameProductDisCalculate() {
        int electronicCount = 0;
        int clothingCount = 0;

        // Changed after the
        for (int i = 0; i < shoppingCartList.size(); i++) {
            if (shoppingCartList.get(i) instanceof Electronics) {
                electronicCount += productBuyCount.get(i);
            }
            else {
                clothingCount += productBuyCount.get(i);
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


        resetShoppingCartList();
        resetProductBuyCountList();
        resetTempProductBuyCountList();

        ProductList productListInstance = ProductList.getInstance();
        productListInstance.saveProductList();
        UserLoginController userLoginController = new UserLoginController();
    }
}
