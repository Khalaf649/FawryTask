package model.service;

import model.product.Shippable;
import  model.cart.*;

import java.util.List;

public class ShippingService {

    private static final double SHIPPING_RATE_PER_KG = 20.0;

    public static double calculateShippingFees(List<ShippingItem> items) {
        double totalWeight = 0;
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight * SHIPPING_RATE_PER_KG;
    }

    public static void shipItems(List<ShippingItem> items) {
        if (items == null || items.isEmpty()) {
            System.out.println(" No items to ship.");
            return;
        }

        double totalWeight = 0;
        System.out.println("** Shipment notice **");

        for (Shippable item : items) {
            double weight = item.getWeight(); // total weight in kg
            int grams = (int) (weight * 1000);
            totalWeight += weight;

            // Expect name to already include quantity (e.g., "2x Cheese")
            System.out.printf("%-15s %4dg%n", item.getName(), grams);
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
    }

