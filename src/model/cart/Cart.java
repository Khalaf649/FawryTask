package model.cart;

import model.product.Product;
import model.product.Shippable;
import model.product.ExpirableProduct;

import java.util.*;

public class Cart {
    private final Map<Product, CartItem> itemMap = new HashMap<>();
    private double subtotal = 0.0;

    public void add(Product product, int quantity) {
        validateInput(product, quantity);

        int alreadyInCart = itemMap.containsKey(product)
                ? itemMap.get(product).getQuantity()
                : 0;

        int totalRequested = alreadyInCart + quantity;

        if (!product.isAvailable(totalRequested)) {
            int available = product.getQuantity();
            throw new IllegalArgumentException(
                    "Not enough stock for " + product.getName() +
                            ". In cart: " + alreadyInCart +
                            ", Requested: " + quantity +
                            ", Available: " + available +
                            (available > alreadyInCart
                                    ? ". You can add up to " + (available - alreadyInCart) + " more."
                                    : ". Product is out of stock.")
            );
        }

        if (product instanceof ExpirableProduct expirable &&
                expirable.isExpired()) {
            throw new IllegalArgumentException("Cannot add expired product: " + product.getName());
        }




        if (itemMap.containsKey(product)) {
            CartItem item = itemMap.get(product);
            item.setQuantity(totalRequested);
        } else {
            itemMap.put(product, new CartItem(product, quantity));
        }

        subtotal += product.getPrice() * quantity;
    }

    public void update(Product product, int newQuantity) {
        if (!itemMap.containsKey(product)) {
            throw new IllegalArgumentException(" Product not found in cart: " + product.getName());
        }

        if (newQuantity <= 0) {
            remove(product);
            return;
        }

        if (!product.isAvailable(newQuantity)) {
            throw new IllegalArgumentException(
                    "Cannot update " + product.getName() + " to quantity " + newQuantity +
                            ". Only " + product.getQuantity() + " in stock.");
        }

        CartItem item = itemMap.get(product);
        int oldQuantity = item.getQuantity();
        item.setQuantity(newQuantity);
        subtotal += (newQuantity - oldQuantity) * product.getPrice();
    }

    public void remove(Product product) {
        if (!itemMap.containsKey(product)) return;
        CartItem item = itemMap.remove(product);
        subtotal -= item.getTotalPrice();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(new ArrayList<>(itemMap.values()));
    }

    public double getSubtotal() {
        return subtotal;
    }

    public boolean isEmpty() {
        return itemMap.isEmpty();
    }

    public List<ShippingItem> getShippableItems() {
        List<ShippingItem> result = new ArrayList<>();
        for (CartItem item : itemMap.values()) {
            Product p = item.getProduct();
            if (p instanceof Shippable s) {
                double totalWeight = s.getWeight() * item.getQuantity();
                result.add(new ShippingItem(p.getName(), totalWeight));
            }
        }
        return result;
    }


    @Override
    public String toString() {
        if (isEmpty()) return "Cart is empty.";
        StringBuilder sb = new StringBuilder("Cart contents:\n");
        for (CartItem item : itemMap.values()) {
            sb.append(" - ").append(item).append("\n");
        }
        sb.append("Subtotal: ").append(subtotal).append(" EGP");
        return sb.toString();
    }

    private void validateInput(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
    }
    public void clear() {
        itemMap.clear();
        subtotal = 0.0;
    }
}
