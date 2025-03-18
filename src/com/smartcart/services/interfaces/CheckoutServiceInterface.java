package com.smartcart.services.interfaces;

public interface CheckoutServiceInterface {
    void processCheckout(CartServiceInterface cart);
    void viewOrderHistory();
}