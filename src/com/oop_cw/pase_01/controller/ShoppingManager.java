package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.model.Product;

import java.util.List;

public interface ShoppingManager {

    void addProduct();

    void deleteProduct();

    void printProductList();

    void saveIntoTheFile();
}
