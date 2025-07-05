package model.cart;

import model.product.Shippable;

public class ShippingItem implements Shippable {
    private String name;
    private double totalWeight;

    public ShippingItem(String name, double totalWeight) {
        setName(name);
        setWeight(totalWeight);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Shipping item name must not be empty.");
        }
        this.name = name;
    }

    @Override
    public double getWeight() {
        return totalWeight;
    }

    public void setWeight(double totalWeight) {
        if (totalWeight <= 0) {
            throw new IllegalArgumentException("Total weight must be greater than 0.");
        }
        this.totalWeight = totalWeight;
    }

    @Override
    public String toString() {
        return name + " (" + totalWeight + " kg)";
    }
}
