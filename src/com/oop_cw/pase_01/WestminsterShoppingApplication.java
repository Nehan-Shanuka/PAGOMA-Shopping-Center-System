package com.oop_cw.pase_01;

import com.oop_cw.pase_01.controller.ProductGUIController;
import com.oop_cw.pase_01.controller.ShoppingCartGUIController;
import com.oop_cw.pase_01.controller.UserLoginController;
import com.oop_cw.pase_01.controller.WestminsterShoppingManager;
import com.oop_cw.pase_01.model.Product;

import java.util.ArrayList;

public class WestminsterShoppingApplication {

    public static void main(String[] args) {

        // Instantiating an instance of the WestminsterShoppingManger class
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Calling the menu method of the WestminsterShoppingManager instance
        shoppingManager.menu();

    }

}
