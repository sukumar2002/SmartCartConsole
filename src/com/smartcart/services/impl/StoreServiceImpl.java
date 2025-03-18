package com.smartcart.services.impl;

import com.smartcart.annotations.Service;
import com.smartcart.models.Product;
import com.smartcart.services.interfaces.StoreServiceInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreServiceInterface {
    private final Map<Integer, Product> productCatalog = new ConcurrentHashMap<>();

    @Override
    public void addProductDynamically() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter Product ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Product Price (₹): ");
            double price = scanner.nextDouble();
            System.out.print("Enter Stock Quantity: ");
            int stock = scanner.nextInt();

            Product product = new Product(id, name, price, stock);
            productCatalog.put(id, product);
            System.out.println("Product Added Successfully!");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    @Override
    public void displayProducts() {
        if (productCatalog.isEmpty()) {
            System.out.println("No products available.");
        } else {
            productCatalog.values().forEach(System.out::println);
        }
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return Optional.ofNullable(productCatalog.get(id));
    }

    @Override
    public void removeProduct(int id) {
        if (productCatalog.remove(id) != null) {
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public boolean updateProduct(int id, String name, double price, int stock) {
        if (productCatalog.containsKey(id)) {
            productCatalog.put(id, new Product(id, name, price, stock));
            System.out.println("Product updated successfully.");
            return true;
        }
        System.out.println("Product not found.");
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return productCatalog.values().stream().collect(Collectors.toList());
    }
}
