package model.user;

public class Customer extends User {
    private double balance;

    public Customer(String name, String email, double balance) {
        super(name, email);
        setBalance(balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance must not be negative.");
        }
        this.balance = balance;
    }

    public boolean canAfford(double amount) {
        return amount >= 0 && balance >= amount;
    }

    public void deduct(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deduct a negative amount.");
        }
        if (!canAfford(amount)) {
            throw new IllegalStateException("Insufficient balance.");
        }
        this.balance -= amount;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit a negative amount.");
        }
        this.balance += amount;
    }
    @Override
    public String toString() {
        return super.toString() + " | Balance: " + balance + " EGP";
    }

}
