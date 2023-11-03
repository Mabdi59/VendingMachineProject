package com.techelevator;

import com.techelevator.exceptions.ProductFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VendingMachine {
    private Set<Product> inventory;

    public Set<Product> getInventory() {
        return inventory;
    }

    public void loadInventory(String filePath) {
        this.inventory = new Inventory(filePath).getInventory();
    }

    public void displayItems() {
        for (Product product : inventory) {
            System.out.printf("%s %s %s %s%n",
                    product.getSlotLocation(),
                    product.getName(),
                    product.getPrice(),
                    product.getType());
        }
    }
}



