package com.smartcart.services.impl;

import com.smartcart.annotations.Service;
import com.smartcart.services.interfaces.CartServiceInterface;
import com.smartcart.services.interfaces.CheckoutServiceInterface;
@Service
public class CheckoutServiceImpl implements CheckoutServiceInterface {
    @Override
    public void processCheckout(CartServiceInterface cart) {
        System.out.println("Processing checkout...");
        cart.clearCart();
        System.out.println("Checkout complete. Thank you for shopping!");
    }

    @Override
    public void viewOrderHistory() {
        System.out.println("Order history feature not implemented yet.");
    }
}