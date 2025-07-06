import model.product.*;
import model.cart.*;
import model.service.*;
import model.user.Customer;
import  java.time.LocalDate;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        // 1.  Successful checkout with 1 product
        try {
            System.out.println("Test 1:  Successful checkout");
            Cheese cheese = new Cheese("Kiri",100,150,LocalDate.of(2025, 7, 17),1);
            Customer customer = new Customer("Khalaf", "khalaf@mail.com", 1000.0);
            Cart cart = new Cart();
            cart.add(cheese, 2);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 1 passed\n");
        } catch (Exception e) {
            System.out.println("Test 1 failed: " + e.getMessage() + "\n");
        }

        // 2.  Quantity exceeds stock
        try {
            System.out.println("Test 2:  Quantity exceeds stock");
            Cheese cheese = new Cheese("Kiri",100,150,LocalDate.of(2025, 7, 17),1);
            Customer customer = new Customer("Khalaf", "khalaf@mail.com", 1000.0);
            Cart cart = new Cart();
            cart.add(cheese, 160);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 2 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 2 passed: " + e.getMessage() + "\n");
        }

        // 3.  Product is expired
        try {
            System.out.println("Test 3:  Expired product");
            Cheese cheese = new Cheese("Kiri",100,150,LocalDate.of(2023, 7, 17),1);
            Customer customer = new Customer("Khalaf", "khalaf@mail.com", 1000.0);
            Cart cart = new Cart();
            cart.add(cheese, 2);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 3 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 3 passed: " + e.getMessage() + "\n");
        }

        // 4.  Insufficient balance
        try {
            System.out.println("Test 4:  Insufficient balance");
            TV tv = new TV("Toshipa TV", 3000.0, 3, 10.0);
            Customer customer = new Customer("Khalaf", "khalaf@mail.com", 100.0);
            Cart cart = new Cart();
            cart.add(tv, 1);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 4 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 4 passed: " + e.getMessage() + "\n");
        }

        // 5.  Empty cart
        try {
            System.out.println("Test 5:  Empty cart");
            Customer customer = new Customer("Khalaf", "khalaf@mail.com", 500.0);
            Cart cart = new Cart();
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 5 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 5 passed: " + e.getMessage() + "\n");
        }

        // 6.  Only shippable items
        try {
            System.out.println("Test 6:  Only shippable items");
            Cheese cheese = new Cheese("Cheese", 80.0, 5, LocalDate.of(2025, 10, 1),1);
            TV tv = new TV("Toshipa TV", 3000.0, 3, 10.0);
            Customer customer = new Customer("Anas", "anas@mail.com", 100000.0);
            Cart cart= new Cart();
            cart.add(cheese, 2);
            cart.add(tv, 1);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 6 passed\n");
        } catch (Exception e) {
            System.out.println("Test 6 failed: " + e.getMessage() + "\n");
        }

        // 7. Only non-shippable items
        try {
            System.out.println("Test 7:  Only non-shippable items");
            MobileScratchCard card = new MobileScratchCard("Etisalat Card", 20.0, 10);
            Customer customer = new Customer("Anas", "anas@mail.com", 500.0);
            Cart cart= new Cart();
            cart.add(card, 3);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 7 passed\n");
        } catch (Exception e) {
            System.out.println("Test 7 failed: " + e.getMessage() + "\n");
        }

        // 8. All types of items — success
        try {
            System.out.println("Test 8:  Mixed types of items");
            Cheese cheese = new Cheese("Kiri", 100.0, 5, LocalDate.of(2025, 9, 1),1);         // shippable + expirable
            Biscuit biscuits = new Biscuit("Chato Biscuit", 150.0, 3, LocalDate.of(2025, 9, 1),0.5);     // shippable + expirable
            TV tv = new TV("Samsung TV", 3000.0, 2, 10.0);         // shippable
            MobileScratchCard card = new MobileScratchCard("Vodafone Card", 30.0, 10);         // non-shippable

            Customer customer = new Customer("Khalaf", "Khalaf@mail.com", 10000.0);
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(tv, 1);
            cart.add(card, 5);

            CheckoutService.checkout(customer, cart);
            System.out.println(cart);
            System.out.println("Test 8 passed\n");
        } catch (Exception e) {
            System.out.println("Test 8 failed: " + e.getMessage() + "\n");
        }

        // Test 9: Add same product twice (should merge quantity)
        try {
            System.out.println("Test 9: Add same product twice");
            Cheese cheese = new Cheese("Kiri", 100.0, 5, LocalDate.of(2025, 9, 1),1);         // shippable + expirable
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(cheese, 1); // should merge to 3
            Customer customer = new Customer("Test", "test@mail.com", 500.0);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 9 passed\n");
        } catch (Exception e) {
            System.out.println("Test 9 failed: " + e.getMessage() + "\n");
        }

        // Test 10: Add product with quantity = 0
        try {
            System.out.println("Test 10: Add product with quantity 0");
            Cheese cheese = new Cheese("Kiri", 100.0, 5, LocalDate.of(2025, 9, 1),1);         // shippable + expirable
            Cart cart = new Cart();
            cart.add(cheese, 0); // invalid
            System.out.println("Test 10 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 10 passed: " + e.getMessage() + "\n");
        }

        // Test 11: Add product with negative quantity
        try {
            System.out.println("Test 11: Add product with negative quantity");
            Cheese cheese = new Cheese("Kiri", 100.0, 5, LocalDate.of(2025, 9, 1),1);         // shippable + expirable
            Cart cart = new Cart();
            cart.add(cheese, -2); // invalid
            System.out.println("Test 11 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 11 passed: " + e.getMessage() + "\n");
        }

        // Test 12: Product expires today
        try {
            System.out.println("Test 12:  Product expires today (should fail)");

            Cheese cheese = new Cheese("Kiri", 100.0, 5, LocalDate.now(), 1.0); // expires today

            Cart cart = new Cart();
            cart.add(cheese, 1);

            Customer customer = new Customer("Test", "test@mail.com", 200.0);
            CheckoutService.checkout(customer, cart);

            System.out.println("Test 12 failed: Should have rejected expired product\n");

        } catch (Exception e) {
            System.out.println("Test 12 passed: " + e.getMessage() + "\n");
        }

        // Test 13: Exact balance match
        try {
            System.out.println("Test 13: Exact balance match");
            Cheese cheese = new Cheese("Kiri", 100.0, 5, LocalDate.of(2025, 9, 1),1);         // shippable + expirable
            Cart cart = new Cart();
            cart.add(cheese, 1);
            double shipping = ShippingService.calculateShippingFees(cart.getShippableItems());
            double total = 100.0 + shipping;
            Customer customer = new Customer("Test", "test@mail.com", total);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 13 passed\n");
        } catch (Exception e) {
            System.out.println("Test 13 failed: " + e.getMessage() + "\n");
        }

        // Test 14: Null product
        try {
            System.out.println("Test 14: Add null product to cart");
            Cart cart = new Cart();
            cart.add(null, 1); // should fail
            System.out.println("Test 14 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 14 passed: " + e.getMessage() + "\n");
        }

        // Test 15: Cart clear + checkout
        try {
            System.out.println("Test 15: Clear cart before checkout");
            Cheese cheese = new Cheese("Kiri", 100.0, 5, LocalDate.of(2025, 9, 1),1);         // shippable + expirable
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.clear(); // now cart is empty
            Customer customer = new Customer("Test", "test@mail.com", 500.0);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 15 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 15 passed: " + e.getMessage() + "\n");
        }

        // Test 16: Free product (price = 0.0)
        try {
            System.out.println("Test 16: Free product (0.0 EGP)");
            Cheese cheese = new Cheese("Kiri", 0, 5, LocalDate.of(2025, 9, 1),1);         // shippable + expirable
            Cart cart = new Cart();
            cart.add(cheese, 2);
            Customer customer = new Customer("Test", "test@mail.com", 40); //just Shipping fees
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 16 passed\n");
        } catch (Exception e) {
            System.out.println("Test 16 failed: " + e.getMessage() + "\n");
        }

        //Test 17: Product with Weight zero
        try {
            System.out.println("Test 17: Product with zero weight");
            Cheese cheese = new Cheese("Weightless Cheese", 50.0, 5, LocalDate.of(2025, 12, 1),0);
            Cart cart = new Cart();
            cart.add(cheese, 2);
            Customer customer = new Customer("ZeroWeight", "zero@mail.com", 500.0);
            CheckoutService.checkout(customer, cart);
            System.out.println("Test 17 failed\n");
        } catch (Exception e) {
            System.out.println("Test 17 passed: " + e.getMessage() + "\n");
        }



         // Test 18
        try {
            System.out.println("Test 18: Update quantity beyond stock");
            Cheese cheese = new Cheese("Updatable", 50.0, 3, LocalDate.of(2025, 12, 1),0.2);
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.update(cheese, 5); // exceeds stock
            System.out.println("Test 22 failed (should have thrown error)\n");
        } catch (Exception e) {
            System.out.println("Test 22 passed: " + e.getMessage() + "\n");
        }






    }
    }

