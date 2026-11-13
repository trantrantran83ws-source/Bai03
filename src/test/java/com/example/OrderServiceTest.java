package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    void testCalculateFinalPrice_ValidInput() {
        double result = orderService.calculateFinalPrice(1000.0, 10.0, 8.0);
        assertEquals(972.0, result, 0.01);
    }

    @Test
    void testCalculateFinalPrice_NoDiscountNoTax() {
        double result = orderService.calculateFinalPrice(500.0, 0.0, 0.0);
        assertEquals(500.0, result, 0.01);
    }

    @Test
    void testCalculateFinalPrice_NegativeBasePrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.calculateFinalPrice(-100.0, 10.0, 8.0);
        });
    }

    @Test
    void testCalculateFinalPrice_InvalidDiscount_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.calculateFinalPrice(1000.0, 150.0, 8.0);
        });
    }
}