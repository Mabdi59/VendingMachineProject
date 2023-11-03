package com.techelevator.view;

import com.techelevator.Product;
import com.techelevator.VendingMachine;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendingMachineTest {
    private VendingMachine vendingMachine;
    @BeforeEach
    void setUp(){
        vendingMachine = new VendingMachine();
        vendingMachine.loadInventory("src/test/resources/inventory_test.txt");
    }
    @Test
    void test_should_dispense_product_when_in_inventory(){
        Product selection = vendingMachine.searchItemBySlotLocation("B1");

        Product received = vendingMachine.dispenseProduct(selection);
        Assert.assertEquals();
    }
}
