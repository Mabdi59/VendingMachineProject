package com.techelevator;

public class VendingMachine {
	private Map<String, Integer> inventoryMap;

	public Map<String, Integer> getInventoryMap (){
		return inventoryMap;
	}
	public void addInventory(String name, int quantity){

		inventoryMap.put(name, quantity);
	}
}
