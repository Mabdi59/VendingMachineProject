package com.techelevator;

import com.techelevator.view.Menu;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";

    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.loadInventory("src/main/resources/inventory.txt");
        vendingMachine.createAccount();
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                vendingMachine.displayAllItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                CustomerAccount account = vendingMachine.getAccount();

                // Ask the user to add money
                double moneyToAdd = promptForMoney();
                account.addToBalance(moneyToAdd);

                // Ask the user to select a product
                String slotLocation = promptForProductSelection(vendingMachine);
                Product selectedProduct = vendingMachine.searchItemBySlotLocation(slotLocation);

                if (selectedProduct != null) {
                    // Check if the user has enough balance to make the purchase
                    if (account.getBalance() >= selectedProduct.getPrice() && !selectedProduct.isSoldOut()) {
                        // Charge the account
                        account.subtractFromBalance(selectedProduct.getPrice());

                        // Decrease the quantity of the product
                        selectedProduct.decrementQuantity();

                        // Give the user the selected product
                        System.out.println("Here's your " + selectedProduct.getName());

                        // Give change if needed
                        double change = account.giveChange();
                        System.out.println("Change: $" + change);
                    } else if (selectedProduct.isSoldOut()) {
                        System.out.println("SOLD OUT: " + selectedProduct.getName());
                    } else {
                        System.out.println("Insufficient balance to purchase this item.");
                    }
                }
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                break;
            } else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                // Implement sales report functionality
            }
        }
    }

    private double promptForMoney() {
        Scanner scanner = new Scanner(System.in);
        double moneyToAdd = 0.0;
        while (true) {
            System.out.print("Enter the amount to add (in dollars): $");
            if (scanner.hasNextDouble()) {
                moneyToAdd = scanner.nextDouble();
                if (moneyToAdd > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid positive amount.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return moneyToAdd;
    }

    private String promptForProductSelection(VendingMachine vendingMachine) {
        Scanner scanner = new Scanner(System.in);
        String slotLocation = "";
        while (true) {
            System.out.print("Enter the slot location of the product you want to purchase: ");
            slotLocation = scanner.nextLine();
            if (isValidSlotLocation(slotLocation, vendingMachine)) {
                break;
            } else {
                System.out.println("Invalid slot location or product is sold out. Please try again.");
            }
        }
        return slotLocation;
    }

    private boolean isValidSlotLocation(String slotLocation, VendingMachine vendingMachine) {
        if (!slotLocation.isEmpty()) {
            for (Product product : vendingMachine.getInventory()) {
                if (product.getSlotLocation().equalsIgnoreCase(slotLocation) && !product.isSoldOut()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
