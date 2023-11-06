package com.techelevator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
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
        System.out.printf("%s %s $%.2f %s%n",
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
            displayAllItems();
            String slotLocation = promptForProductSelection();
            Product selectedProduct = searchItemBySlotLocation(slotLocation);

            if (selectedProduct == null) {
                System.out.println("Invalid product code or product is sold out. Please try again.");
            } else {
                if (account.getBalance() >= selectedProduct.getPrice()) {
                    account.subtractFromBalance(selectedProduct.getPrice());
                    selectedProduct.decrementQuantity();
                    dispenseProduct(selectedProduct);
                    logTransaction(selectedProduct.getName(), selectedProduct.getPrice());
                    break;
                } else {
                    System.out.println("Insufficient balance to purchase this item. Please try again.");
                }
            }
        }
    }

    private void dispenseProduct(Product product) {
        System.out.println("Dispensing " + product.getName());
        System.out.printf("Price: $%.2f%n", product.getPrice());
        System.out.printf("Money Remaining: $%.2f%n", account.getBalance());

        String message = getMessageForProductType(product.getType());
        System.out.println(message);
    }
    public String getMessageForProductType(String productType) {
        switch (productType.toLowerCase()) {
            case "chip":
                return "Crunch Crunch, Yum!";
            case "candy":
                return "Munch Munch, Yum!";
            case "drink":
                return "Glug Glug, Yum!";
            case "gum":
                return "Chew Chew, Yum!";
            default:
                return "Yum Yum!";
        }
    }

    private String promptForProductSelection() {
        String slotLocation = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the slot location of the product you want to purchase: ");
            slotLocation = scanner.nextLine();
            if (!isValidSlotLocation(slotLocation)) {
                System.out.println("Invalid slot location or product is sold out. Please try again.");
            } else {
                break;
            }
        }
        return slotLocation;
    }

    private boolean isValidSlotLocation(String slotLocation) {
        if (slotLocation.isEmpty()) {
            return false;
        }

        for (Product product : inventory) {
            if (Objects.equals(product.getSlotLocation(), slotLocation)) {
                if (product.isSoldOut()) {
                    System.out.println("SOLD OUT: " + product.getName());
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private void logTransaction(String productName, double amount) {
        String transaction = String.format("%s %s $%.2f $%.2f",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")),
                productName,
                amount,
                account.getBalance());

        writeTransactionLog(transaction, "Log.txt");
    }

    private void writeTransactionLog(String transaction, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(transaction);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing the transaction to the log: " + e.getMessage());
        }
    }

        public void generateSalesReport() {
            String filename = String.format("SalesReport_%s.txt",
                    currentTime("yyyyMMddHHmmss"));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (Product product : inventory) {
                    int quantityRemaining = 5 - product.getQuantity();
                    String line = String.format("%s|%d",
                          //  product.getSlotLocation(),
                            product.getName(),
                            quantityRemaining
                           // product.getType()
                    );
                    writer.write(line);
                    writer.newLine();
                }

                String totalSales = String.format("\n**TOTAL SALES** $%.2f", calculateTotalSales());
                writer.write(totalSales);
                System.out.println("Sales report has been generated: " + filename);
            } catch (IOException e) {
                System.out.println("Error writing the sales report to a file: " + e.getMessage());
            }
        }

        public double calculateTotalSales() {
            double totalSales = 0.0;
            for (Product product : inventory) {
                totalSales += product.getPrice() * (5 - product.getQuantity());
            }
            return totalSales;
        }

        public static String currentTime(String pattern) {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
        }

        public Product searchItemBySlotLocation(String slotLocation) {
            for (Product product : inventory) {
                if (Objects.equals(product.getSlotLocation(), slotLocation)) {
                    if (!product.isSoldOut()) {
                        return product;
                    }
                }
            }
            return null;
        }
    }