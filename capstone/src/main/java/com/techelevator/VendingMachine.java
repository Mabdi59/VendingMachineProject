package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
	private List<Product> inventory;
	private double currentBalance;

	public VendingMachine() {
		inventory = new ArrayList<>();
		currentBalance = 0.0;
	}
}
