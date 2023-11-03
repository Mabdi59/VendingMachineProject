package com.techelevator;

public class CustomerAccount {
    private double balance = 0.0;

    public double getBalance() {
        return balance;
    }

    public double addToBalance(double amount) {
        if (amount > 0)
            balance += amount;
        return balance;
    }

    public double subtractFromBalance(double amount) {
        if (balance >= amount)
            balance -= amount;

        return balance;
    }

    public double giveChange() {
        if (balance <= 0)
            return 0;

        double change = getBalance();
        balance = 0.0;
        return change;
    }
}
