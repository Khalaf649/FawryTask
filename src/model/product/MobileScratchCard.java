package model.product;
import  model.product.Product;

/**
 * A scratch card that is neither expirable nor shippable.
 */
public class MobileScratchCard extends Product {

    public MobileScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }


}
