package com.smartcart.controller;

import com.smartcart.factory.ServiceFactory;
import com.smartcart.models.Product;
import com.smartcart.services.interfaces.*;

import java.util.Optional;
import java.util.Scanner;

public class CartController {
    private final Scanner scanner = new Scanner(System.in);
    private final StoreServiceInterface store = ServiceFactory.getService(StoreServiceInterface.class);
    private final CartServiceInterface cart = ServiceFactory.getService(CartServiceInterface.class);
    private final CheckoutServiceInterface checkout = ServiceFactory.getService(CheckoutServiceInterface.class);

    public void start() {
        while (true) {
            System.out.println("\n1. View Products\n2. Add Product\n3. Add to Cart\n4. View Cart\n5. Checkout\n6. View Order History\n7. Exit");
            System.out.print("Choose an option: ");
            switch (scanner.nextInt()) {
                case 1 -> store.displayProducts();
                case 2 -> store.addProductDynamically();
                case 3 -> handleAddToCart();
                case 4 -> cart.viewCart();
                case 5 -> checkout.processCheckout(cart);
                case 6 -> checkout.viewOrderHistory();
                case 7 -> exitApp();
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    private void handleAddToCart() {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        store.getProduct(productId).ifPresentOrElse(
                p -> cart.addToCart(p, quantity),
                () -> System.out.println("Product not found!")
        );
    }

    private void exitApp() {
        System.out.println("Exiting...");
        scanner.close();
        System.exit(0);
    }
}

