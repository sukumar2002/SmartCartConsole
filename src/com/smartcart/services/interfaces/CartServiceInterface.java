package com.smartcart.services.interfaces;

import com.smartcart.models.Product;

public interface CartServiceInterface {
    void addToCart(Product product, int quantity);
    void viewCart();
    void removeFromCart(int productId);
    void clearCart();  // Add this method
}
