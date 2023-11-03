package com.techelevator.view;

import com.techelevator.VendingMachine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {
    private VendingMachine vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine();
    }

    @Test
    void test_should_start_with_balance_of_zero() {
        double expected = 0.0;
        double received = vendingMachine.getBalance();
        assertEquals(expected, received, 0.0);
    }

    @Test
    void test_addToBalance_should_increase_balance_by_5() {
        double expected = vendingMachine.getBalance() + 5.0;
        double received = vendingMachine.addToBalance(5.0);
        assertEquals(expected, received, 0.0);
    }

    @Test
    void test_subtractFromBalance_should_decrease_balance_by_5() {
        vendingMachine.addToBalance(10.0);
        double expected = vendingMachine.getBalance() - 5.0;
        double received = vendingMachine.subtractFromBalance(5.0);
        assertEquals(expected, received, 0.0);
    }

    @Test
    void test_should_give_correct_change() {
        vendingMachine.addToBalance(2.5);
        double expected = 2.5;
        double received = vendingMachine.giveChange();
        assertEquals(expected, received, 0.0);
    }

    @Test
    void test_should_have_zero_balance_after_give_change() {
        vendingMachine.addToBalance(2.5);
        vendingMachine.giveChange();
        double expected = 0;
        double received = vendingMachine.getBalance();
        assertEquals(expected, received, 0.0);
    }
}
