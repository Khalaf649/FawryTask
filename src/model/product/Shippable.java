package model.product;

/**
 * Represents a shippable product with a weight (in kilograms).
 * Any product that needs to be shipped must implement this.
 */
public interface Shippable {
    double getWeight();
    String getName();
}
