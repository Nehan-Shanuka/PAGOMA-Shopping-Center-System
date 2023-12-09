package com.oop_cw.pase_01;

public class Electronics extends Product {
    private String brandName;
    private int daysOfWarranty;

    //Constructor with brandName and Product's productId, productName and numOfAvailability
    public Electronics(String productId, String productName, int numOfAvailability, String brandName) {
        super(productId, productName, numOfAvailability);
        this.brandName = brandName;
    }

    // Constructor with brandName and Product's productId, productName, numOfAvailability and price
    public Electronics(String productId, String productName, int numOfAvailability, double price,
                       String brandName) {
        super(productId, productName, numOfAvailability, price);
        this.brandName = brandName;
    }

    // A public method for retrieving the value of brandName
    public String getBrandName() {
        return brandName;
    }

    // A public method for retrieving the value of daysOfWarranty
    public int getDaysOfWarranty() {
        return daysOfWarranty;
    }

    // A public method for assigning value to the daysOfWarranty
    public void setDaysOfWarranty(int daysOfWarranty) {
        this.daysOfWarranty = daysOfWarranty;
    }

    @Override
    public String toString() {
        return "Product Type : Electronic \n" +
                "Product Id : " + getProductId() + '\n' +
                "Product Name : " + getProductName() + '\n' +
                "Product Brand : " + brandName + '\n' +
                "Product Warranty Days : " + daysOfWarranty + '\n' +
                "Available Stock : " + getNumOfAvailability() + '\n' +
                "Product Price : '" + getPrice();
    }
}
