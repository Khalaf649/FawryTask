package model.product;

public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    //  Setter with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty.");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price must not be negative.");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity must not be negative.");
        }
        this.quantity = quantity;
    }
    public void adjustQuantity(int qtyChange) {
        int newQuantity = this.quantity + qtyChange;

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot go below zero.");
        }

        this.quantity = newQuantity;
    }



    public String getName()     { return name; }
    public double getPrice()    { return price; }
    public int getQuantity()    { return quantity; }

    public boolean isAvailable(int requestedQty) {
        return quantity >= requestedQty;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%.2f EGP, %d units)", getType(), getName(), getPrice(), getQuantity());
    }

}
