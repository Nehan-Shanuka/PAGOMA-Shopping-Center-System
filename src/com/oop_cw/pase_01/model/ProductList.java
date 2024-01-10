package com.oop_cw.pase_01.model;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ProductList {

    private static ProductList instance;

    private ArrayList<Product> productList = new ArrayList<>();

    public ProductList() {}

    public static ProductList getInstance() {
        if (instance == null) {
            instance = new ProductList();
        }
        return instance;
    }
    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void addToProductList(Product product) {
        productList.add(product);
    }

    public void removeFormProductList(Product product) {
        productList.remove(product);
    }

    public void saveProductList() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("productDetails.txt"))) {
            for (Product item : productList) {
                String content = getContent(item);

                String newline = System.getProperty("line.separator");

                bufferedWriter.write(content);
                bufferedWriter.write(newline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getContent(Product item) {

        String content = item.getProductId() + ", " +
                item.getProductName() + ", " +
                item.getNumOfAvailability() + ", " +
                item.getPrice() + ", ";

        if (item instanceof Electronics) {
            content = "Electronic" + ", " + content + ((Electronics) item).getBrandName() + ", " +
                    ((Electronics) item).getDaysOfWarranty();
        } else if (item instanceof Clothing) {
            content = "Clothing" + ", " + content + ((Clothing) item).getColor() + ", " +
                    ((Clothing) item).getSize();
        }
        return content;
    }

    public void loadProductList() {
        try {
            FileReader fileObj = new FileReader("productDetails.txt");
            Scanner fileReader = new Scanner(fileObj);

            while (fileReader.hasNextLine()) {
                String content = fileReader.nextLine();

                String[] contentArray = content.split(", ");

                String category = contentArray[0];
                String productId = contentArray[1];
                String productName = contentArray[2];
                int availability = Integer.parseInt(contentArray[3]);
                String price = contentArray[4];
                String brandNameOrSize = contentArray[5];
                String warrantyDaysOrColor = contentArray[6];

                Product newProduct;

                if (Objects.equals(category, "Electronic")) {
                    if (price.isEmpty()) {
                        newProduct = new Electronics(productId, productName,
                                availability, brandNameOrSize, warrantyDaysOrColor);
                    } else {
                        double doublePrice = Double.parseDouble(price);
                        newProduct = new Electronics(productId, productName,
                                availability, doublePrice, brandNameOrSize, warrantyDaysOrColor);
                    }
                    addToProductList(newProduct);
                } else if (Objects.equals(category, "Clothing")) {
                    if (price.isEmpty()) {
                        newProduct = new Clothing(productId, productName,
                                availability, brandNameOrSize, warrantyDaysOrColor);
                    } else {
                        double doublePrice = Double.parseDouble(price);
                        newProduct = new Clothing(productId, productName,
                                availability, doublePrice, brandNameOrSize, warrantyDaysOrColor);
                    }
                    addToProductList(newProduct);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
