package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";

    private static final String[] MAIN_MENU_OPTIONS = {
            MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT
    };

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.loadInventory("src/main/resources/inventory.txt");
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                vendingMachine.displayAllItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                vendingMachine.createAccount();
                double moneyToAdd = promptForMoney();
                vendingMachine.getAccount().addToBalance(moneyToAdd);
                vendingMachine.selectProduct();
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                break;
            } else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                // Placeholder for generateSalesReport method
                // generateSalesReport(vendingMachine.getTransactions());
                System.out.println("Sales report feature is under construction.");
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
                scanner.next(); // Consume invalid input
            }
        }

        return moneyToAdd;
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}