package com.oop_cw.pase_01;

import com.oop_cw.pase_01.controller.ProductDisplayController;
import com.oop_cw.pase_01.controller.WestminsterShoppingManager;
import com.oop_cw.pase_01.model.Product;

import java.util.ArrayList;

public class WestminsterShoppingApplication {

    public static void main(String[] args) {

        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<Product> shoppingCartList = new ArrayList<>();

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

//        ProductDisplayController productDisplayScene = new ProductDisplayController(productList, shoppingCartList);

        shoppingManager.menu();

    }

}
