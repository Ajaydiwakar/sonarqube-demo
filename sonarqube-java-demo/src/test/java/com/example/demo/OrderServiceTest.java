package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final OrderService service = new OrderService();

    @Test
    void shouldCalculatePremiumDiscount() {
        double result = service.calculateDiscount("PREMIUM", 1000);
        assertEquals(800, result);
    }

    @Test
    void shouldFindActiveUsers() {
        List<String> result = service.findActiveUsers(
                List.of("active:ajay", "inactive:rahul", "active:mohan")
        );

        assertEquals(2, result.size());
        assertTrue(result.contains("active:ajay"));
    }

    @Test
    void shouldCalculateScore() {
        assertEquals(5, service.calculateScore(95));
        assertEquals(0, service.calculateScore(40));
    }

    @Test
    void shouldCheckAdminAccess() {
        assertTrue(service.hasAdminAccess(new String("ADMIN")));
    }
}
