package com.oop.cw.pase_01;

import java.util.List;

public interface ShoppingManager {

    void addProduct();

    void deleteProduct(Product product);

    void printProductList(List<Product> productList);
}
