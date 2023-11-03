package com.techelevator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine extends CustomerAccount {
    private double balance;
    private List<Product> inventory;

    public VendingMachine() {
        super();
        this.inventory = new ArrayList<>();
    }

    public VendingMachine(List<Product> inventory) {
        this.inventory = inventory;
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public void loadInventory(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (String line : lines) {
                String[] parts = line.split("\\|");

                if (parts.length == 4) {
                    String slotLocation = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String type = parts[3];

                    Product product = new Product(slotLocation, name, price, type);
                    inventory.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("File not found: " + filePath);
        }
    }
}



