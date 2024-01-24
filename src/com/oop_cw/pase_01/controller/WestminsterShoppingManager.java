package com.oop_cw.pase_01.controller;

import com.oop_cw.pase_01.model.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    // Creating a Scanner object to read input from the user
    static Scanner input = new Scanner(System.in);
    // Creating an instance of the Singleton ProductList
    static ProductList productList = ProductList.getInstance();
    // Creating an instance of the Singleton UserList
    static UserList userList = UserList.getInstance();

    public void menu() {

        // Declaring the instance of WestminsterShoppingManager
        // to access the dynamic(non-static) methods. *Since those methods
        // are overriding from an interface that can not be static.
        WestminsterShoppingManager newClassInstance = new WestminsterShoppingManager();

//        UserLoginController userLoginController = new UserLoginController();

        // Calling the methods that should get saved data back to the system
        loadFromTheFile();
        userList.loadUserList();

        int option;

        do {
            // Displaying the menu of options
            System.out.println("""
                Menu : 	1) Add a new product           -> [1]
                		2) Remove a existing product   -> [2]
                		3) Print the list of products  -> [3]
                		4) Save into the file          -> [4]
                		5) User Register               -> [5]
                		6) Open GUI                    -> [6]
                		-------------------------------------
                		*) Quit the Menu               -> [0]
                		""");

            // Try-catch block to catch InputMismatchErrors
            try {
                // Asking the user to input the option
                System.out.print("Enter the option : ");
                option = input.nextInt();

                input.nextLine();

                switch (option) {
//                    To exit the loop and program
//                    adding after the submission
                    case 0:
                        break;
                    case 1:
                        newClassInstance.addProduct();
                        break;
                    case 2:
                        newClassInstance.deleteProduct();
                        break;
                    case 3:
                        // To handle error that the list of products is empty
                        // Added after the submission
                        if (!productList.getProductList().isEmpty()) {
                            sortProductList();
                            printProductList();
                        } else {
                            System.out.println("""
                                    
                                    NOTIFICATION ********************************
                                    
                                    NO registered products in the system
                                    
                                    *********************************************
                                    """);
                        }
                        break;
                    case 4:
                        newClassInstance.saveIntoTheFile();
                        // Modified after the submission
                        System.out.println("""
                                
                                NOTIFICATION *******************************
                                
                                Successfully Saved the changes into the File
                                
                                ********************************************
                                """);
                        break;
                    case 5:
                        userRegister();
                        break;
                    case 6:
                        // Modified after the submission
                        System.out.println("""
                                
                                NOTIFICATION *******************************
                                
                                Opening the User GUI
                                
                                ********************************************
                                """);
                        UserLoginController userLoginController = new UserLoginController();
                        break;
                    default:
                        // Modified after the submission
                        System.out.println("""

                                WARNING *************************************

                                Invalid Option!
                                You can try with a valid option.
                                
                                *********************************************
                                """);
                        break;
                }
            } catch (InputMismatchException e) {
                // Modified after the submission
                System.out.println("""

                        WARNING *************************************

                        Entered Option can not be readable.
                        Try Again with a valid option!
                        
                        *********************************************
                        """);
                input.nextLine();
                option = 99;
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

        if (productCategory.toUpperCase().equals("E") || productCategory.toUpperCase().equals("C")) {
            // Generating the product ID
            String productId = getString(productCategory);

            System.out.println();

//        System.out.print("Enter the Product Id : ");
//        String productId = input.nextLine();
            try {
                System.out.print("Enter the Product Name : ");
                String productName = input.nextLine();

                System.out.print("Enter the the Number the Count of Availability of the Product : ");
                int numOfAvailability = input.nextInt();
                input.nextLine();

                System.out.print("Enter the Price of the Product : ");
                double price = input.nextDouble();
                input.nextLine();

                // Checking the price of the product inputted or not
                // and according to situation use if-else to instantiate the product with relevant constructor
                if (productCategory.equalsIgnoreCase("E")) {

                    System.out.print("Enter the Brand Name of the Product : ");
                    String brandName = input.nextLine();

                    System.out.print("Enter Warranty Period in Days of the Product : ");
                    String daysOfWarranty = input.nextLine();

                    // Checking whether the price is not filled or not
                    // instantiating the newProduct
                    if (Double.isNaN(price)) {
                        newProduct = new Electronics(productId, productName, numOfAvailability,
                                brandName, daysOfWarranty);

                    } else {
                        newProduct = new Electronics(productId, productName, numOfAvailability,
                                price, brandName, daysOfWarranty);

                    }
                    // Adding the newProduct into Singleton Product List
                    productList.addToProductList(newProduct);
                    // Saving to the file
                    saveIntoTheFile();

                    // Checking and instantiating for Clothing as the above
                } else if (productCategory.equalsIgnoreCase("C")) {

                    System.out.print("Enter the Size of the Cloth : ");
                    String clothSize = input.nextLine();

                    System.out.print("Enter the Color of the Cloth : ");
                    String clothColor = input.nextLine();

                    if (Double.isNaN(price)) {
                        newProduct = new Clothing(productId, productName, numOfAvailability,
                                clothSize, clothColor);

                    } else {
                        newProduct = new Clothing(productId, productName, numOfAvailability,
                                price, clothSize, clothColor);

                    }
                    productList.addToProductList(newProduct);
                    saveIntoTheFile();
                }
                //Catch to catch InputMismatchError
            } catch (InputMismatchException e) {
                // Changed for better display to warning message
                System.out.println("""
                        
                        WARNING *************************************

                        Adding the product is Failed!
                        Inputs can not be understandable.
                        
                        *********************************************""");
                // Changed for to catch the junk input and avoid repeating it with next input
                input.nextLine();
            }
        }
        System.out.println();
    }

    // The method to generate the productId
    private static String getString(String productCategory) {
        String productId = null;

        for (int i = productList.getProductList().size()-1; i >= 0; i--) {
            if (productCategory.toUpperCase().equals("E")) {
                if (productList.getProductList().get(i).getProductId().substring(0, 1).equals("E")) {
                    int lastNum = Integer.parseInt(
                            productList.getProductList().get(i).getProductId().substring(1));
                    lastNum++;
                    String numId;
                    if (lastNum > 9) {
                        numId = "0" + lastNum;
                    } else {
                        numId = "00" + lastNum;
                    }
                    productId = "E" + numId;
                    break;
                }
            } else if (productCategory.toUpperCase().equals("C")) {
                if (productList.getProductList().get(i).getProductId().substring(0, 1).equals("C")) {
                    int lastNum = Integer.parseInt(
                            productList.getProductList().get(i).getProductId().substring(1));
                    lastNum++;
                    String numId;
                    if (lastNum > 9) {
                        numId = "0" + lastNum;
                    } else {
                        numId = "00" + lastNum;
                    }
                    productId = "C" + numId;
                    break;
                }
            }
        }

        // This is for setting product id to 001 because of the absence of
        // a pre-recorded product in the same category
        // Added to the submitted one later
        if (productId == null) {
            if (productCategory.equalsIgnoreCase("E")) {
                productId = "E001";
            } else if (productCategory.equalsIgnoreCase("C")) {
                productId = "C001";
            }
        }

        return productId;
    }

    @Override
    public void deleteProduct() {
        System.out.print("\nEnter the Product Id Want to Deleted: ");
        String idOfProduct = input.nextLine();

        int availabilityOption = 0;

        for (Product item: productList.getProductList()) {
            availabilityOption++;
            if (Objects.equals(item.getProductId(), idOfProduct.toUpperCase())) {
                productList.removeFormProductList(item);
                System.out.println("\nSuccessfully Deleted the Product!\n");
                productList.saveProductList();
                break;
            }
        }
        // Changed after submission to catch the no items in the list and for better look
        if (availabilityOption == productList.getProductList().size()-1 || availabilityOption==0) {
            System.out.println("""
                    
                    NOTIFICATION ********************************
                    
                    There is No Product in Product ID : """ + idOfProduct + """
                    
                    
                    *********************************************
                    
                    """);
        }
    }

    @Override
    public void saveIntoTheFile() {
        productList.saveProductList();
    }

    @Override
    public void printProductList() {
        // Iterating through the list of product instances
        // and printout attributes of those
        System.out.println("\n=============================================");
        for (Product item :  productList.getProductList()) {
            System.out.println('\n' + item.toString());
        }
        System.out.println("\n=============================================\n");
    }

    private static void loadFromTheFile() {
        productList.loadProductList();
    }

    public void userRegister() {
        Scanner input = new Scanner(System.in);

        boolean condition;
        String username;
        int availabilityCount = 0;

        do {
            System.out.print("\nUsername : ");
            username = input.next();

            condition = true;

            for (User user: userList.getUserList()) {
                availabilityCount++;
                if (Objects.equals(username.toLowerCase(), user.getUsername())) {
                    System.out.println("""
                            
                            NOTIFICATION ********************************
                            
                            The Username is Exists.
                            Try with Another Username!
                            
                            *********************************************
                            """);
                    condition = false;
                    break;
                }
            }

            // Changed after the submission to work properly even when the user list is empty
            if (condition) {
                System.out.print("Password : ");
                String password = input.next();

                username = username.toLowerCase();
                password = password.toLowerCase();

                User newUser = new User(username, password);

                userList.addUserToUserList(newUser);
                userList.saveUserList();

                System.out.println("""
                            
                            NOTIFICATION ********************************
                            
                            The User Registration is Successful!
                            
                            *********************************************
                            """);

                condition = false;
                break;
            }
        } while (condition);
    }

    private void sortProductList() {
        ArrayList<Product> electronicsList = new ArrayList<>();
        ArrayList<Product> sortedElectronicList = new ArrayList<>();
        ArrayList<Product> clothingList = new ArrayList<>();
        ArrayList<Product> sortedClothingList = new ArrayList<>();

        for (Product item: productList.getProductList()) {
            String productId = item.getProductId();

            if (productId.startsWith("E")) {
                electronicsList.add(item);

            } else if (productId.startsWith("C")) {
                clothingList.add(item);
            }
        }

        // Changed after the submission to validate the empty arraylist
        if (!electronicsList.isEmpty()) {
            sortedElectronicList = MergeSort(electronicsList, 0, electronicsList.size()-1);
        }
        if (!clothingList.isEmpty()) {
            sortedClothingList = MergeSort(clothingList, 0, clothingList.size()-1);
        }


        ArrayList<Product> sortedAllList = new ArrayList<>();
        sortedAllList.addAll(sortedElectronicList);
        sortedAllList.addAll(sortedClothingList);

        productList.setProductList(sortedAllList);
        saveIntoTheFile();
    }

    public static ArrayList<Product> MergeSort(ArrayList<Product> array_list, int start, int end) {
        //to store and to return the ticket information according to the price
        ArrayList<Product> sorted_product_list;

        if (start < end) {
            int mid = (start + end) / 2;
            ArrayList<Product> ticket_list_left = MergeSort(array_list, start, mid);
            ArrayList<Product> ticket_list_right = MergeSort(array_list, mid + 1, end);
            sorted_product_list = merge(ticket_list_left, ticket_list_right);
        } else {
            sorted_product_list = new ArrayList<>();
            sorted_product_list.add(0, array_list.get(start));
        }
        return sorted_product_list;
    }

    public static ArrayList<Product> merge(ArrayList<Product> product_list_1, ArrayList<Product> product_list_2) {
        ArrayList<Product> merged = new ArrayList<>();
        int idx_1 = 0, idx_2 = 0, idx_merged = 0;

        while (idx_1 < product_list_1.size() && idx_2 < product_list_2.size()) {

            if (Integer.parseInt(product_list_1.get(idx_1).getProductId().substring(1)) <=
                    Integer.parseInt(product_list_2.get(idx_2).getProductId().substring(1))) {
                merged.add(idx_merged, product_list_1.get(idx_1));
                idx_1++;
            } else {
                merged.add(idx_merged, (product_list_2.get(idx_2)));
                idx_2++;
            }
            idx_merged++;

        }
        while (idx_1 < product_list_1.size()) {
            merged.add(idx_merged, product_list_1.get(idx_1));
            idx_1++;
            idx_merged++;
        }
        while (idx_2 < product_list_2.size()) {
            merged.add(idx_merged, product_list_2.get(idx_2));
            idx_2++;
            idx_merged++;
        }
        return merged;
    }
}
