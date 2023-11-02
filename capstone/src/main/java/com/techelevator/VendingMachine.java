package com.techelevator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
	private List<String> inventory;
	private double currentBalance;

	public VendingMachine() {
		inventory = new ArrayList<>();
		currentBalance = 0.0;
	}

	public void LoadInventory(String filePath) {
		this.inventory = readFile(filePath);
		System.out.println(inventory);

	}

	public List<String> readFile(String path) {
		List<String> listOfFiles = null;
		try {
			listOfFiles = Files.readAllLines(Path.of(path));

		} catch (IOException e) {
			System.out.printf("file not found: %s%n", path);
		}
		return listOfFiles;
	}
}
