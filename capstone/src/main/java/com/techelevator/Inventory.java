package com.techelevator;

import com.techelevator.exceptions.ProductFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Inventory {
    private String filePath;
    private Set<Product> inventory = new HashSet<>();

    public Inventory(String filePath) {
        this.filePath = filePath;
        loadInventory();
    }

    public Set<Product> getInventory() {
        return inventory;
    }

    public int getSize() {
        return inventory.size();
    }

    private void loadInventory() {
        try {
            for (String string : readFile()) {
                inventory.add(Product.toProduct(string));
            }
        } catch (ProductFormatException e) {
            System.out.println("Invalid product format" + e);
        }
    }

    private List<String> readFile() {
        List<String> files = null;
        try {
            files = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            System.out.println("File not found: " + filePath);
        }
        return files;
    }
}
