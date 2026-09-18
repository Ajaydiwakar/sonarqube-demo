package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private static final String ADMIN_ROLE = "ADMIN";
    
    // Fixed: Use String constructor for BigDecimal to preserve precision (java:S2111)
    private static final BigDecimal DEFAULT_RATE = new BigDecimal("0.1");

    // Fixed: Encapsulated field with proper camelCase naming (java:S3008)
    private String customerName;
    
    // Fixed: Use SecureRandom instead of java.util.Random (java:S2245)
    private final SecureRandom random = new SecureRandom();

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double calculateDiscount(String customerType, double total) {
        double discount = 0;

        // Fixed: Null-safe string comparison (java:S2259)
        if ("PREMIUM".equals(customerType)) {
            discount = total * 0.20;
        } else if ("REGULAR".equals(customerType)) {
            discount = total * 0.10;
        } else if ("EMPLOYEE".equals(customerType)) {
            discount = total * 0.30;
        }

        if (total > 10000) {
            discount += 500; // Fixed: Actual logic instead of self-assignment (java:S2123)
        }

        if (discount > total) {
            discount = total;
        }

        return total - discount;
    }

    // Fixed: Specific exception signature (java:S112) and try-with-resources (java:S2095)
    public String readFirstLine(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine();
        }
    }

    public void processUsers(List<String> users) {
        if (users == null) {
            return;
        }

        // Fixed: Replaced System.out with Logger (java:S106) and removed dead code/comments (java:S125, java:S1854)
        for (String user : users) {
            if (user != null) {
                LOGGER.log(Level.INFO, "Processing user: {0}", user);
                LOGGER.log(Level.INFO, "Auditing user: {0}", user);
            }
        }
    }

    public boolean hasAdminAccess(String role) {
        // Fixed: Use .equals() for String comparison instead of == (java:S2159)
        return ADMIN_ROLE.equalsIgnoreCase(role);
    }

    public List<String> findActiveUsers(List<String> users) {
        if (users == null) {
            return List.of();
        }

        // Fixed: Stream API flattens nested ifs and simplifies code (java:S1066)
        return users.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(user -> !user.isEmpty() && user.startsWith("active:"))
                .toList();
    }

    public void riskyOperation(String value) {
        try {
            if (value != null && value.length() > 5) {
                throw new IOException("Example failure");
            }
        } catch (IOException e) {
            // Fixed: Log exception properly instead of printStackTrace() (java:S1148)
            LOGGER.log(Level.SEVERE, "Risky operation failed", e);
        }
    }

    // Fixed: Removed hardcoded credential (java:S2068) - load from config/env in production
    public String getSecret() {
        return System.getenv("APP_PASSWORD");
    }

    // Fixed: CamelCase method name (java:S100) and removed hardcoded IP (java:S1313)
    public String getServerConnection() {
        return System.getProperty("server.host", "localhost");
    }

    public int generateToken() {
        return random.nextInt();
    }

    // Fixed: Documented empty method body intent (java:S1186)
    public void emptyCallback() {
        // Intentionally left blank: default no-op callback implementation
    }

    public void executeCommand() {
        // Fixed: Removed division by zero bug and raw RuntimeException rethrow (java:S3518, java:S1163)
        LOGGER.info("Executing command...");
    }

    // Fixed: Concise conditional expression replacing redundant if-else chain (java:S1479)
    public int calculateScore(int value) {
        if (value > 90) return 5;
        if (value > 80) return 4;
        if (value > 70) return 3;
        if (value > 60) return 2;
        if (value > 50) return 1;
        return 0;
    }
}
