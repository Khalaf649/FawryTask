package model.product;
import model.product.ExpirableProduct;
import java.time.LocalDate;

public class DigitalGiftCard extends ExpirableProduct {
    public DigitalGiftCard(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity, expiryDate);
    }

    // Not shippable — no weight
}
