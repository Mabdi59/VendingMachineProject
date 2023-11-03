package com.techelevator.view;

import com.techelevator.Inventory;

import com.techelevator.exceptions.ProductFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory inventory;
    private String file = "src/test/resources/inventory_test.txt";

    @BeforeEach
    void setUp()  throws IOException, ProductFormatException {
        inventory = new Inventory(file);
    }

    @Test
    void test_reads_inventory_file() {
        assertEquals(4, inventory.getSize());
    }
}
