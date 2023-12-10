package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.model.Product;

import java.util.List;

public interface ShoppingManager {

    void addProduct(List<Product> productList);

    void deleteProduct(List<Product> productList);

    void printProductList(List<Product> productList);

    void saveIntoAFile(List<Product> productList);
}
