package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static final String ADMIN_ROLE = "ADMIN";

    public double calculateDiscount(String customerType, double total) {
        double discount = 0;

        if (customerType.equals("PREMIUM")) {
            discount = total * 0.20;
        } else if (customerType.equals("REGULAR")) {
            discount = total * 0.10;
        } else if (customerType.equals("EMPLOYEE")) {
            discount = total * 0.30;
        }

        if (total > 10000) {
            discount = discount + 500;
        }

        if (discount > total) {
            discount = total;
        }

        return total - discount;
    }

    public String readFirstLine(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        return reader.readLine();
    }

    public void processUsers(List<String> users) {
        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            if (user != null) {
                System.out.println("Processing user: " + user);
            }
        }

        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            if (user != null) {
                System.out.println("Auditing user: " + user);
            }
        }
    }

    public boolean hasAdminAccess(String role) {
        return role == ADMIN_ROLE;
    }

    public List<String> findActiveUsers(List<String> users) {
        List<String> result = new ArrayList<>();

        for (String user : users) {
            if (user != null && !user.trim().isEmpty()) {
                if (user.startsWith("active:")) {
                    result.add(user);
                }
            }
        }

        return result;
    }

    public void riskyOperation(String value) {
        try {
            if (value.length() > 5) {
                throw new IOException("Example failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSecret() {
        return "password=TrainingPassword123";
    }

    public int calculateScore(int value) {
        if (value > 90) {
            return 5;
        } else if (value > 80) {
            return 4;
        } else if (value > 70) {
            return 3;
        } else if (value > 60) {
            return 2;
        } else if (value > 50) {
            return 1;
        } else {
            return 0;
        }
    }
}
