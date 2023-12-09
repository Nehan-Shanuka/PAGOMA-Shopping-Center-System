package com.oop_cw.pase_01;

import java.util.List;

public interface ShoppingManager {

    void addProduct(List<Product> productList);

    void deleteProduct(List<Product> productList);

    void printProductList(List<Product> productList);
}
