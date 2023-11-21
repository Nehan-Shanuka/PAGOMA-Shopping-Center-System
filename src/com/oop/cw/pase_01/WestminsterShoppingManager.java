package com.oop.cw.pase_01;

import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    static Scanner input = new Scanner(System.in);
    static List<Product> productList;

    public static void main(String[] args) {
        System.out.println("Menu : " +
                "1) Add a new product           -> [1]" +
                "2) Remove a existing product   -> [2]" +
                "3) Print the list of products  -> [3]" +
                "4) Save into the file          -> [4]");

        System.out.print('\n' + "Enter the option : ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                WestminsterShoppingManager newInstance = new WestminsterShoppingManager();
                newInstance.addProduct();
        }
    }

    // An override public method for add new products
    @Override
    public void addProduct() {
        System.out.println("Enter the ");
        String productId = input.nextLine();
        String productName = input.nextLine();
        int numOfAvailability = input.nextInt();
        input.nextLine();
        double price = input.nextDouble();
        String brandName = input.nextLine();
        Product newProduct = new Electronics(productId, productName, numOfAvailability, price, brandName);
    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void printProductList(List<Product> productList) {

    }
}
