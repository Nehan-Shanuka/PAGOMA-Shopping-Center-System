package com.oop_cw.pase_01.model;

public abstract class Product {
    private String productId;
    private String productName;
    private int numOfAvailability;
    private double price;

    //Constructor with default price and given productId, productName, and numOfAvailability
    public Product(String productId, String productName, int numOfAvailability) {
        this.productId = productId;
        this.productName = productName;
        this.numOfAvailability = numOfAvailability;
        // Below Integer.parseInt(null) will throw a NullPointerException
        // and as an example when "System.out.println(price)" call,
        // it won't be reached since the var is in 'null'
        price = Double.NaN;
    }

    //Constructor with given productId, productName, numOfAvailability and price
    public Product(String productId, String productName, int numOfAvailability, double price) {
        this.productId = productId;
        this.productName = productName;
        this.numOfAvailability = numOfAvailability;
        this.price = price;
    }

    // A public method for retrieving the productId
    public String getProductId() {
        return productId;
    }

    // A public method for retrieving the productName
    public String getProductName() {
        return productName;
    }

    // A public method for retrieving the numOfAvailability
    public int getNumOfAvailability() {
        return numOfAvailability;
    }

    // A public method for retrieving the price
    public double getPrice() {
        return price;
    }

    // A public method for assigning into the productId
    public void setProductId(String productId) {
        this.productId = productId;
    }

    // A public method for assigning into the productName
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // A public method for assigning into the numOfAvailability
    public void setNumOfAvailability(int numOfAvailability) {
        this.numOfAvailability = numOfAvailability;
    }

    // A public method for assigning into the price
    public void setPrice(double price) {
        this.price = price;
    }
}
