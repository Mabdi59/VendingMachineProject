package com.techelevator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineApp {
	private static final String INVENTORY_FILE = "vendingmachine.csv";
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

	private List<Product> inventory;
	private double balance;

	public VendingMachineApp() {
		inventory = new ArrayList<>();
		balance = 0.0;
		loadInventory();
	}

	public void loadInventory() {
		try {
			List<String> lines = Files.readAllLines(Path.of(INVENTORY_FILE));
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
			e.printStackTrace();
		}
	}

	public void displayProducts() {
		for (Product product : inventory) {
			System.out.println(product);
		}
	}

	public void purchase() {
		// Implement the purchase process here
		// You'll need to interact with the user, manage their balance, and update the inventory
		// This is where you handle the steps mentioned in the README file
		// For simplicity, you can start with basic functionality
	}

	public static void main(String[] args) {
		VendingMachineApp vendingMachine = new VendingMachineApp();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nMain Menu:");
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			System.out.print("Select an option: ");

			String choice = scanner.nextLine();

			switch (choice) {
				case "1":
					vendingMachine.displayProducts();
					break;
				case "2":
					vendingMachine.purchase();
					break;
				case "3":
					System.out.println("Goodbye!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option. Please try again.");
					break;
			}
		}
	}
}

