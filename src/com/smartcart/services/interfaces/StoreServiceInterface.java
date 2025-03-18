package com.smartcart.services.interfaces;

import com.smartcart.models.Product;
import java.util.List;
import java.util.Optional;

public interface StoreServiceInterface {
    void displayProducts();
    void addProductDynamically();
    Optional<Product> getProduct(int id);
    void removeProduct(int id);
    boolean updateProduct(int id, String name, double price, int stock);
    List<Product> getAllProducts();
}
