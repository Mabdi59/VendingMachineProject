package com.techelevator;

public class CustomerAccount {
    private double balance = 0.0;

    public double getBalance() {
        return balance;
    }

    public double deposit(double amount) {
        if (amount > 0)
            balance += amount;
        return balance;
    }

    public double withdraw(double amount) {
        if (balance >= amount)
            balance -= amount;

        return balance;
    }

    public double giveChange() {
        if (balance <= 0)
            return 0;

        double change = getBalance();
        withdraw(balance);
        return change;
    }
}
