

import model.product.*;
import  model.cart.*;
import model.service.*;
import  model.user.*;



import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {

            Customer customer=new Customer("Khawja","khawja@gmai.com",300000);


            Product cheese = new Cheese("Cheddar Cheese", 150, 5, LocalDate.now().plusDays(5), -0.5);
            Product tv = new TV("Samsung 55-inch", 7000, 2, 8.0);
            Product scratchCard = new MobileScratchCard("Vodafone Card", 50, 10);
            Cart cart = new Cart();
            cart.add(cheese,3);
            cart.add(tv,1);
            cart.add(scratchCard,10);
            System.out.println(cart.toString());
            CheckoutService.checkout(customer,cart);






        } catch (Exception e) {
            System.out.println(" ERROR: " + e.getMessage());
        }
    }
}
