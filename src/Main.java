

import model.product.*;
import  model.cart.*;



import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {



            Product cheese = new Cheese("Cheddar Cheese", 150, 5, LocalDate.now().plusDays(5), 0.5);
            Product tv = new TV("Samsung 55-inch", 7000, 2, 8.0);
            Product scratchCard = new MobileScratchCard("Vodafone Card", 50, 10);
            Cart cart = new Cart();
            cart.add(cheese,3);
            cart.add(cheese,1);
            cart.update(cheese,5);
            cart.remove(cheese);
            System.out.println(cart);




        } catch (Exception e) {
            System.out.println(" ERROR: " + e.getMessage());
        }
    }
}
