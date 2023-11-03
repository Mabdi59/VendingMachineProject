package com.techelevator;

import java.util.Set;

public class VendingMachine {
    private Set<Product> inventory;
    private CustomerAccount account;

    public Set<Product> getInventory() {
        return inventory;
    }

    public void loadInventory(String filePath) {
        this.inventory = new Inventory(filePath).getInventory();
    }

    public void createAccount() {
        this.account = new CustomerAccount();
    }

    public CustomerAccount getAccount() {
        return account;
    }

    public void displayItem(Product product) {
        System.out.printf("%s %s %s %s%n",
                product.getSlotLocation(),
                product.getName(),
                product.getPrice(),
                product.getType());
    }

    public void displayAllItems() {
        for (Product product : inventory) {
            displayItem(product);
        }
    }

    public Product searchItemBySlotLocation(String slotLocation) {
        for (Product product : inventory) {
            if (product.isSoldOut()) {
                System.out.println("SOLD OUT: " + product.getName());
                break;
            }

            if (product.getSlotLocation() == slotLocation) {
                System.out.printf("You chose: " + product.getName());
                return product;
            }
        }
        return null;
    }
}



