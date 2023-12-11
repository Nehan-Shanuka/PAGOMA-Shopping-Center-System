package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.model.Clothing;
import com.oop_cw.pase_01.model.Electronics;
import com.oop_cw.pase_01.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    // Create a Scanner object to read input from the user
    static Scanner input = new Scanner(System.in);

    public void menu(ArrayList<Product> productList) {

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
                		*) Quit the Menu               -> [0]
                		""");

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
                    break;
                case 4:
                    newClassInstance.saveIntoAFile(productList);
                    break;
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
        System.out.println("""
                
                If the Product is an ELECTRONIC enter (E/e),
                if CLOTHING enter (C/c)
                as Product Category.
                """);

        System.out.print("Enter the Product Category : ");
        String productCategory = input.nextLine();

        System.out.println();

        System.out.print("Enter the Product Id : ");
        String productId = input.nextLine();

        System.out.print("Enter the Product Name : ");
        String productName = input.nextLine();

        System.out.print("Enter the the Number the Count of Availability of the Product : ");
        int numOfAvailability = input.nextInt();
        input.nextLine();

        System.out.print("Enter the Price of the Product : ");
        String price = input.nextLine();

        // Checking the price of the product inputted or not
        // and according to situation use if-else to instantiate the product with relevant constructor
        if (productCategory.equalsIgnoreCase("E")) {

            System.out.print("Enter the Brand Name of the Product : ");
            String brandName = input.nextLine();

            System.out.print("Enter Warranty Period in Days of the Product : ");
            String daysOfWarranty = input.nextLine();

            if (price.isEmpty()) {
                newProduct = new Electronics(productId, productName, numOfAvailability,
                        brandName, daysOfWarranty);

            } else {
                double doublePrice = Double.parseDouble(price);
                newProduct = new Electronics(productId, productName, numOfAvailability,
                        doublePrice, brandName, daysOfWarranty);

            }
            productList.add(newProduct);

        } else if (productCategory.equalsIgnoreCase("C")) {

            System.out.print("Enter the Size of the Cloth : ");
            String clothSize = input.nextLine();

            System.out.print("Enter the Color of the Cloth : ");
            String clothColor = input.nextLine();

            if (price.isEmpty()) {
                newProduct = new Clothing(productId, productName, numOfAvailability,
                        clothSize, clothColor);

            } else {
                double doublePrice = Double.parseDouble(price);
                newProduct = new Clothing(productId, productName, numOfAvailability,
                        doublePrice, clothSize, clothColor);

            }
            productList.add(newProduct);
        }
        System.out.println();
    }

    @Override
    public void deleteProduct(List<Product> productList) {
        System.out.print("\nEnter the Product Id : ");
        String idOfProduct = input.nextLine();

        for (Product item : productList) {
            if (idOfProduct.equals(item.getProductId())) {
                productList.remove(item);
                break;
                /*try (BufferedReader br = new BufferedReader(new FileReader("productDetails.txt"));
                     BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"))) {

                    String line;
                    int lineCount = 0;

                    // Assigning read line to line var and checking the nullness of the line
                    while ((line = br.readLine()) != null) {
                        // If the next line of the file not null then,
                        // Incrementing the lineCount by one
                        lineCount++;

                        // Checking the line contains with relevant product details
                        if (Objects.equals(getContent(item), line)) {



                            break;
                        }

                    }

                    if (Objects.equals(getContent(item), br.readLine())) {

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("productDetails.txt", false))) {
            // The second parameter 'false' in FileWriter means overwrite the file (truncate)
            // If the file doesn't exist, a new file will be created
            for (Product item : productList) {
                bufferedWriter.write(getContent(item));
                bufferedWriter.newLine();

                System.out.println("File deleted and rewritten successfully.");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveIntoAFile(List<Product> productList) {

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
            content = "Electronic" + ", " + content + ((Electronics) item).getBrandName() + ", " +
                    ((Electronics) item).getDaysOfWarranty();
        } else if (item instanceof Clothing) {
            content = "Clothing" + ", " + content + ((Clothing) item).getColor() + ", " +
                    ((Clothing) item).getSize();
        }
        return content;
    }

    @Override
    public void printProductList(List<Product> productList) {
        // Iterating through the list of product instances
        // and printout attributes of those
        for (Product item :  productList) {
            System.out.println('\n' + item.toString());
        }
    }

    private static void loadFromTheFile(List<Product> productList) {

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
                        newProduct = new Electronics(productId, productName, availability, brandNameOrSize, warrantyDaysOrColor);
                    } else {
                        double doublePrice = Double.parseDouble(price);
                        newProduct = new Electronics(productId, productName, availability, doublePrice, brandNameOrSize, warrantyDaysOrColor);
                    }
                    productList.add(newProduct);
                } else if (Objects.equals(category, "Clothing")) {
                    if (price.isEmpty()) {
                        newProduct = new Clothing(productId, productName, availability, brandNameOrSize, warrantyDaysOrColor);
                    } else {
                        double doublePrice = Double.parseDouble(price);
                        newProduct = new Clothing(productId, productName, availability, doublePrice, brandNameOrSize, warrantyDaysOrColor);
                    }
                    productList.add(newProduct);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
