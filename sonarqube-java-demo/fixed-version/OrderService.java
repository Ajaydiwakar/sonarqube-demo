package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderService {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final double PREMIUM_DISCOUNT = 0.20;
    private static final double REGULAR_DISCOUNT = 0.10;
    private static final double EMPLOYEE_DISCOUNT = 0.30;
    private static final double HIGH_VALUE_BONUS = 500.0;

    public double calculateDiscount(String customerType, double total) {
        Objects.requireNonNull(customerType, "customerType must not be null");

        double rate = switch (customerType) {
            case "PREMIUM" -> PREMIUM_DISCOUNT;
            case "REGULAR" -> REGULAR_DISCOUNT;
            case "EMPLOYEE" -> EMPLOYEE_DISCOUNT;
            default -> 0.0;
        };

        double discount = total * rate;
        if (total > 10000) {
            discount += HIGH_VALUE_BONUS;
        }

        return total - Math.min(discount, total);
    }

    public String readFirstLine(String filePath) throws IOException {
        Path path = Path.of(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.readLine();
        }
    }

    public void processUsers(List<String> users) {
        for (String user : users) {
            if (user != null) {
                logProcessing(user);
                logAudit(user);
            }
        }
    }

    private void logProcessing(String user) {
        System.out.println("Processing user: " + user);
    }

    private void logAudit(String user) {
        System.out.println("Auditing user: " + user);
    }

    public boolean hasAdminAccess(String role) {
        return ADMIN_ROLE.equals(role);
    }

    public List<String> findActiveUsers(List<String> users) {
        List<String> result = new ArrayList<>();

        for (String user : users) {
            if (user != null && !user.isBlank() && user.startsWith("active:")) {
                result.add(user);
            }
        }

        return result;
    }

    public void riskyOperation(String value) {
        Objects.requireNonNull(value, "value must not be null");

        try {
            if (value.length() > 5) {
                throw new IOException("Example failure");
            }
        } catch (IOException exception) {
            System.err.println("Expected operation failure: " + exception.getMessage());
        }
    }

    public int calculateScore(int value) {
        if (value > 90) return 5;
        if (value > 80) return 4;
        if (value > 70) return 3;
        if (value > 60) return 2;
        if (value > 50) return 1;
        return 0;
    }
}
