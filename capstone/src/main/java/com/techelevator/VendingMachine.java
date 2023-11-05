package com.techelevator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public void selectProduct() {
        while (true) {
            // Display the list of available products
            displayAllItems();

            // Ask the user to enter a product code
            String slotLocation = promptForProductSelection();

            // Check if the product code exists and the product is not sold out
            Product selectedProduct = searchItemBySlotLocation(slotLocation);

            if (selectedProduct == null) {
                System.out.println("Invalid product code or product is sold out. Please try again.");
            } else {
                // Check if the user has enough balance to make the purchase
                if (account.getBalance() >= selectedProduct.getPrice()) {
                    // Charge the account
                    account.subtractFromBalance(selectedProduct.getPrice());

                    // Decrease the quantity of the product
                    selectedProduct.decrementQuantity();

                    // Dispense the selected product
                    dispenseProduct(selectedProduct);

                    // Log the transaction
//                    logTransaction(selectedProduct);

                    // Go back to the Purchase menu
                    break;
                } else {
                    System.out.println("Insufficient balance to purchase this item. Please try again.");
                }
            }
        }
    }

    private void dispenseProduct(Product product) {
        System.out.println("Dispensing " + product.getName());
        System.out.println("Price: $" + product.getPrice());
        System.out.println("Money Remaining: $" + account.getBalance());

        String message = "";
        if (product.getType().equalsIgnoreCase("Chip"))
            message = "Crunch Crunch, Yum!";
        else if (product.getType().equalsIgnoreCase("Candy"))
            message = "Munch Munch, Yum!";
        else if (product.getType().equalsIgnoreCase("Drink"))
            message = "Glug Glug, Yum!";
        else if (product.getType().equalsIgnoreCase("Gum"))
            message = "Chew Chew, Yum!";

        System.out.println(message);
    }

    private String promptForProductSelection() {
        Scanner scanner = new Scanner(System.in);
        String slotLocation = "";
        while (true) {
            System.out.print("Enter the slot location of the product you want to purchase: ");
            slotLocation = scanner.nextLine();
            if (!isValidSlotLocation(slotLocation))
                System.out.println("Invalid slot location or product is sold out. Please try again.");
            else
                break;
        }
        scanner.close();
        return slotLocation;
    }

    private boolean isValidSlotLocation(String slotLocation) {
        if (slotLocation.isEmpty())
            return false;

        for (Product product : inventory) {
            if (product.getSlotLocation().equalsIgnoreCase(slotLocation) && !product.isSoldOut())
                return true;
        }
        return false;
    }

    private void logTransaction(String message, double amount) {
//        String transaction = String.format("%s %s $%.2f $%.2f",
//                LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")),
//                product.getName(),
//                product.getPrice(),
//                account.getBalance());
//        transactionsList.add(transaction);

        String transaction = String.format("%s %s $%.2f $%.2f",
                currentTime("MM/dd/yyyy hh:mm:ss a"),
                message,
                amount,
                account.getBalance()
        );

        writeToFile(transaction, "Log.txt");
    }


    public void generateSalesReport() {
        String filename = String.format("sales_report_%s.txt",
                currentTime("yyyyMMddHHmmss"));

        for (Product product : inventory) {
            int quantityRemaining = 5 - product.getQuantity();

            String line = String.format("%s|%s",
                    product.getName(),
                    quantityRemaining);

            writeToFile(line, filename);

        }

        String totalSales = String.format("\n**TOTAL SALES** $%.2f", calculateTotalSales());
        writeToFile(totalSales, filename);
    }

    public double calculateTotalSales() {
        double salesCounter = 0.0;
        for (Product product : inventory) {
            salesCounter+=product.getPrice() * (5 - product.getQuantity());
        }
        return salesCounter;
    }

    public static String currentTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public void writeToFile(String string, String filename) {
        try (FileOutputStream logWriter = new FileOutputStream(filename, true)) {
            logWriter.write(string.getBytes());
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public Product searchItemBySlotLocation(String slotLocation) {
        for (Product product : inventory) {
            if (Objects.equals(product.getSlotLocation(), slotLocation))
                return product;
        }
        return null;
    }
}