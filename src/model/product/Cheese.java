package model.product;
import model.product.ExpirableProduct;
import model.product.Shippable;

import java.time.LocalDate;

public class Cheese extends ExpirableProduct implements Shippable {
    private final double weight; // in kg

    public Cheese(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
