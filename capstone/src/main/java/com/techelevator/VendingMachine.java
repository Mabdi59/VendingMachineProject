package com.techelevator;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class VendingMachine {
    private Set<Product> inventory;
    private CustomerAccount account;
    private List<String> transactionsList;

    public VendingMachine() {
        transactionsList = new ArrayList<>();
    }

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

    public List<String> getTransactions() {
        return transactionsList;
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
                    logTransaction(selectedProduct);

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
        if (product.getType().equalsIgnoreCase("Chip")) {
            message = "Crunch Crunch, Yum!";
        } else if (product.getType().equalsIgnoreCase("Candy")) {
            message = "Munch Munch, Yum!";
        } else if (product.getType().equalsIgnoreCase("Drink")) {
            message = "Glug Glug, Yum!";
        } else if (product.getType().equalsIgnoreCase("Gum")) {
            message = "Chew Chew, Yum!";
        }
        System.out.println(message);
    }

    private String promptForProductSelection() {
        Scanner scanner = new Scanner(System.in);
        String slotLocation = "";
        while (true) {
            System.out.print("Enter the slot location of the product you want to purchase: ");
            slotLocation = scanner.nextLine();
            if (isValidSlotLocation(slotLocation)) {
                break;
            } else {
                System.out.println("Invalid slot location or product is sold out. Please try again.");
            }
        }
        return slotLocation;
    }

    private boolean isValidSlotLocation(String slotLocation) {
        if (!slotLocation.isEmpty()) {
            for (Product product : inventory) {
                if (product.getSlotLocation().equalsIgnoreCase(slotLocation) && !product.isSoldOut()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void logTransaction(Product product) {
        String transaction = String.format("%s %s $%.2f $%.2f",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")),
                product.getName(),
                product.getPrice(),
                account.getBalance());
        transactionsList.add(transaction);
    }

    public Product searchItemBySlotLocation(String slotLocation) {
        for (Product product : inventory) {
            if (product.isSoldOut()) {
                System.out.println("SOLD OUT: " + product.getName());
                break;
            }

            if (Objects.equals(product.getSlotLocation(), slotLocation)) {
                // Print a message indicating the selected product
                System.out.printf("You chose: %s%n", product.getName());
                return product;
            }
        }
        return null;
    }
}