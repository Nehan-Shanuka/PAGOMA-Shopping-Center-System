package com.oop_cw.pase_01;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    // Create a Scanner object to read input from the user
    static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();

        // Declaring the instance of WestminsterShoppingManager
        // to access the dynamic(non-static) methods. *Since those methods
        // are overriding from an interface those methods con not be static.
        WestminsterShoppingManager newClassInstance = new WestminsterShoppingManager();

        loadFromTheFile(productList);

        int option;

        do {
            // Displaying the menu of options
            System.out.println("""
                Menu : 	1) Add a new product           -> [1]
                		2) Remove a existing product   -> [2]
                		3) Print the list of products  -> [3]
                		4) Save into the file          -> [4]
                		-------------------------------------
                		*) Quit the Menu               -> [0]""");

            System.out.print('\n' + "Enter the option : ");
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    newClassInstance.addProduct(productList);
                    break;
                case 2:
                    newClassInstance.deleteProduct(productList);
                    break;
                case 3:
                    newClassInstance.printProductList(productList);
                case 4:
                    newClassInstance.saveIntoAFile(productList);
            }
        } while (option != 0);

    }

    // An override public method for add new products
    @Override
    public void addProduct(List<Product> productList) {
        // Declaring the instance of Product
        Product newProduct;

        // Prompting the user to enter inputs
        // and use the Scanner object to read the user's inputs in relevant data types
        System.out.print("\nEnter the Product Id : ");
        String productId = input.nextLine();

        System.out.print("Enter the Product Name : ");
        String productName = input.nextLine();

        System.out.print("Enter the the Number the Count of Availability of the Product : ");
        int numOfAvailability = input.nextInt();
        input.nextLine();

        System.out.print("Enter the Price of the Product : ");
        String price = input.nextLine();

        System.out.print("Enter the Brand Name of the Product : ");
        String brandName = input.nextLine();

        System.out.println();

        // Checking the price of the product inputted or not
        // and according to situation use if-else to instantiate the product with relevant constructor
        if (price.isEmpty()) {
            newProduct = new Electronics(productId, productName, numOfAvailability, brandName);
        } else {
            double doublePrice = Double.parseDouble(price);
            newProduct = new Electronics(productId, productName, numOfAvailability, doublePrice, brandName);
        }
        productList.add(newProduct);
    }

    @Override
    public void deleteProduct(List<Product> productList) {
        System.out.println("\nEnter the Product Id : ");
        String idOfProduct = input.nextLine();

        for (Product item : productList) {
            if (idOfProduct.equals(item.getProductId())) {
                productList.remove(item);
            }
        }
    }

    @Override
    public void printProductList(List<Product> productList) {
        // Iterating through the list of product instances
        // and printout attributes of those
//        System.out.println(productList);
        for (Product item :  productList) {
            System.out.println(item.toString());
//            System.out.println("\nProduct Id : " + item.getProductId() + "\n" +
//                    "Product Name : " + item.getProductName() + "\n" +
//                    "Available Product Units : " + item.getNumOfAvailability() + "\n" +
//                    "Unit Price : " + item.getPrice());
//
//            // Checking whether the item is an instance of Electronic or Clothing
//            // and printout relevant attributes of those instances
//            if (item instanceof Electronics) {
//                System.out.println("Product Brand Name : " + ((Electronics) item).getBrandName() + "\n" +
//                        "Days of warranty of the Product : " + ((Electronics) item).getDaysOfWarranty() + "\n");
//            } else if (item instanceof Clothing) {
//                System.out.println("Color of Cloth : " + ((Clothing) item).getColor() + "\n" +
//                        "Size of Cloth : " + ((Clothing) item).getSize() + "\n");
//            }
        }
    }

    public void saveIntoAFile(List<Product> productList) {
//        Path filePath = Path.of("productDetails.txt");

        try {
            FileWriter myWriter = new FileWriter("productDetails.txt");
            for (Product item : productList) {
                String content = getContent(item);

                String newline = System.getProperty("line.separator");

                myWriter.write(content);
                myWriter.write(newline);
            }
            myWriter.close();
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
            content = content + ((Electronics) item).getBrandName() + ", " +
                    ((Electronics) item).getDaysOfWarranty();
        } else if (item instanceof Clothing) {
            content = content + ((Clothing) item).getColor() + ", " +
                    ((Clothing) item).getSize();
        }
        return content;
    }

    private static void loadFromTheFile(List<Product> productList) {
        try {
            FileReader fileObj = new FileReader("productDetails.txt");
            Scanner fileReader = new Scanner(fileObj);

            while (fileReader.hasNextLine()) {
                String content = fileReader.nextLine();

                String[] contentArray = content.split(", ");

                String productId = contentArray[0];
                String productName = contentArray[1];
                int availability = Integer.parseInt(contentArray[2]);
                String price = contentArray[3];
                String brandName = contentArray[4];
                int warrantyDays = Integer.parseInt(contentArray[5]);

                Product newProduct;

                if (price.isEmpty()) {
                    newProduct = new Electronics(productId, productName, availability, brandName);
                } else {
                    double doublePrice = Double.parseDouble(price);
                    newProduct = new Electronics(productId, productName, availability, doublePrice, brandName);
                }
                productList.add(newProduct);
                System.out.println(productList);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
