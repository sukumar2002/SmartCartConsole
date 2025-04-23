package com.smartcart.services.impl;

import com.smartcart.annotations.Service;
import com.smartcart.models.Product;
import com.smartcart.services.interfaces.CartServiceInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service  // Ensure this annotation is present
public class CartServiceImpl implements CartServiceInterface {
    private final Map<Integer, Integer> cart = new ConcurrentHashMap<>();

    @Override
    public void addToCart(Product product, int quantity) {
        cart.merge(product.getId(), quantity, Integer::sum);
        System.out.println(quantity + " unit(s) of " + product.getName() + " added to cart.");
    }

    @Override
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            cart.forEach((productId, quantity) ->
                    System.out.println("Product ID: " + productId + ", Quantity: " + quantity));
        }
    }

    @Override
    public void clearCart() {
        cart.clear();
        System.out.println("Cart has been cleared.");
    }

    @Override
    public void removeFromCart(int productId) {
        if (cart.remove(productId) != null) {
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }
}
