package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.model.*;

import java.util.Objects;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    // Create a Scanner object to read input from the user
    static Scanner input = new Scanner(System.in);
    static ProductList productList = ProductList.getInstance();
    static UserList userList = UserList.getInstance();

    public void menu() {

        // Declaring the instance of WestminsterShoppingManager
        // to access the dynamic(non-static) methods. *Since those methods
        // are overriding from an interface that can not be static.
        WestminsterShoppingManager newClassInstance = new WestminsterShoppingManager();

        loadFromTheFile();
        userList.loadUserList();
        UserLoginController userLoginControllerNew = new UserLoginController();
//        ProductDisplayController productDisplayController = new ProductDisplayController();

        int option;

        do {
            // Displaying the menu of options
            System.out.println("""
                Menu : 	1) Add a new product           -> [1]
                		2) Remove a existing product   -> [2]
                		3) Print the list of products  -> [3]
                		4) Save into the file          -> [4]
                		5) Open GUI                    -> [5]
                		6) User Register               -> [6]
                		-------------------------------------
                		*) Quit the Menu               -> [0]
                		""");

            System.out.print("Enter the option : ");
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    newClassInstance.addProduct();
                    break;
                case 2:
                    newClassInstance.deleteProduct();
                    break;
                case 3:
                    newClassInstance.printProductList();
                    break;
                case 4:
                    newClassInstance.saveIntoTheFile();
                    break;
                case 5:
                    UserLoginController userLoginController = new UserLoginController();
                    break;
                case 6:
                    userRegister();
                    break;
            }
        } while (option != 0);

    }

    // An override public method for add new products
    @Override
    public void addProduct() {
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
            productList.addToProductList(newProduct);

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
            productList.addToProductList(newProduct);
        }
        System.out.println();
    }

    @Override
    public void deleteProduct() {
        System.out.print("\nEnter the Product Id : ");
        String idOfProduct = input.nextLine();

        for (Product item: productList.getProductList()) {
            if (Objects.equals(item.getProductId(), idOfProduct)) {
                productList.removeFormProductList(item);
                break;
            }
        }
        productList.saveProductList();
    }

    @Override
    public void saveIntoTheFile() {
        productList.saveProductList();
    }

    @Override
    public void printProductList() {
        // Iterating through the list of product instances
        // and printout attributes of those
        for (Product item :  productList.getProductList()) {
            System.out.println('\n' + item.toString());
        }
    }

    private static void loadFromTheFile() {
        productList.loadProductList();
    }

    public void userRegister() {
        Scanner input = new Scanner(System.in);

        System.out.print("Username : ");
        String username = input.next();
        System.out.println("Password : ");
        String password = input.next();

        User newUser = new User(username, password);

        UserList userList = UserList.getInstance();

        userList.addUserToUserList(newUser);
        userList.saveUserList();
    }
}
