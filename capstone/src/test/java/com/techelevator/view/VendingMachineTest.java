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

    // TODO: test total sales calculator

//    @Test
//    void test_should_append_data_to_log_file(){
//
//    }
}
