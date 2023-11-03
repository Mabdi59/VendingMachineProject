package com.techelevator;

import com.techelevator.view.Menu;

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
                // new account to do transactions
                CustomerAccount account = new CustomerAccount();

                // ask them what they want


                // do purchase



            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                break;
            } else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                // print sales report
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
