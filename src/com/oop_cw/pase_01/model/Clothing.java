package com.oop_cw.pase_01.model;

import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String size;
    private String color;

    // Constructor with size and color and default price,
    // given productId, productName, and numOfAvailability
    public Clothing(String productId, String productName, int numOfAvailability,
                    String size, String color) {
        super(productId, productName, numOfAvailability);
        this.size = size;
        this.color = color;
    }
    // Constructor with size and color and default price,
    // given productId, productName, numOfAvailability and price
    public Clothing(String productId, String productName, int numOfAvailability, double price,
                    String size, String color) {
        super(productId, productName, numOfAvailability, price);
        this.size = size;
        this.color = color;
    }

    // A public method for retrieving the value of size
    public String getSize() {
        return size;
    }

    // A public method for retrieving the value of color
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {

        return "Product Type : Clothing \n" +
                "Product Id : " + getProductId() + '\n' +
                "Product Name : " + getProductName() + '\n' +
                "Product Size : " + size + '\n' +
                "Product Color : " + color + '\n' +
                "Available Stock : " + getNumOfAvailability() + '\n' +
                "Product Price : " + getPrice();
    }
}
