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
        System.out.println("\n Shipping the following items:");

        for (Shippable item : items) {
            System.out.printf(" - %s (%.2f kg)%n", item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }

        double shippingFee = totalWeight * SHIPPING_RATE_PER_KG;
        System.out.printf(" Total weight: %.2f kg%n", totalWeight);
        System.out.printf("Shipping fee: %.2f EGP%n", shippingFee);
    }
}
