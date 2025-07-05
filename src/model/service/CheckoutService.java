package model.service;

import model.cart.Cart;
import model.cart.CartItem;
import model.cart.ShippingItem;
import model.product.ExpirableProduct;
import model.product.Product;
import model.product.Shippable;
import model.user.Customer;

import java.time.LocalDate;
import java.util.List;

public class CheckoutService {

    public static void checkout(Customer customer, Cart cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalStateException(" Cart is empty. Cannot checkout.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer must not be null.");
        }

        List<CartItem> items = cart.getItems();


        for (CartItem item : items) {
            Product product = item.getProduct();
            int qty = item.getQuantity();

            if (!product.isAvailable(qty)) {
                throw new IllegalStateException(" Product out of stock: " + product.getName());
            }

            if (product instanceof ExpirableProduct expirable && expirable.isExpired()) {
                throw new IllegalStateException(" Product expired: " + product.getName());
            }
        }


        List<ShippingItem> shippables = cart.getShippableItems();

        // 3. Calculate total
        double subtotal = cart.getSubtotal();
        double shippingFees = ShippingService.calculateShippingFees(shippables);
        double total = subtotal + shippingFees;

        if (!customer.canAfford(total)) {
            throw new IllegalStateException(
                    "Insufficient balance. Required: %.2f, Available: %.2f"
                            .formatted(total, customer.getBalance())
            );
        }

        // 4. Process payment and update stock
        customer.deduct(total);
        for (CartItem item : items) {
            item.getProduct().adjustQuantity(-item.getQuantity());
        }


        ShippingService.shipItems(shippables);

        // 5. Print receipt
        System.out.println("\n** Checkout receipt **");

        for (CartItem item : cart.getItems()) {
            String label = item.getQuantity() + "x " + item.getProduct().getName();
            System.out.printf("%-15s %6.0f%n", label, item.getTotalPrice());
        }

        System.out.println("----------------------");
        System.out.printf("%-15s %6.0f%n", "Subtotal", subtotal);
        System.out.printf("%-15s %6.0f%n", "Shipping", shippingFees);
        System.out.printf("%-15s %6.0f%n", "Amount", total);




    }
}
