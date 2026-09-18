package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ISSUE 1 (Code Smell - java:S1214): Constants interface implementation antipattern
interface Constants {
    // ISSUE 2 (Code Smell - java:S115): Constant name does not comply with naming convention
    public static final String admin_role = "ADMIN";
}

public class OrderService implements Constants {

    // ISSUE 3 (Code Smell - java:S1068): Unused private field
    private String unusedField = "temporary";

    // ISSUE 4 (Code Smell - java:S3008): Field name doesn't follow camelCase standard
    public String Customer_Name;

    // ISSUE 5 (Code Smell - java:S2111): BigDecimal created with a double literal (precision loss)
    private BigDecimal defaultRate = new BigDecimal(0.1);

    public double calculateDiscount(String customerType, double total) {
        double discount = 0;

        // ISSUE 6 (Bug - java:S2259): NullPointerException risk (customerType can be null)
        if (customerType.equals("PREMIUM")) {
            discount = total * 0.20;
        } else if (customerType.equals("REGULAR")) {
            discount = total * 0.10;
        } else if (customerType.equals("EMPLOYEE")) {
            discount = total * 0.30;
        }

        // ISSUE 7 (Bug - java:S2123): Value stored in 'discount' is useless / assignment issue
        if (total > 10000) {
            discount = discount;
        }

        if (discount > total) {
            discount = total;
        }

        return total - discount;
    }

    public String readFirstLine(String filePath) throws Exception {
        // ISSUE 8 (Bug/Code Smell - java:S2095): Resource leak - BufferedReader & FileReader unclosed
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        return reader.readLine();
    }

    public void processUsers(List<String> users) {
        // ISSUE 9 (Code Smell - java:S106): Standard output used instead of a dedicated logger
        // ISSUE 10 (Code Smell - java:S1854): Dead store to local variable 'counter'
        int counter = 0;

        // ISSUE 11 (Code Smell - java:S125): Commented-out code blocks
        // for (String u : users) {
        //     System.out.println(u);
        // }

        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            if (user != null) {
                System.out.println("Processing user: " + user);
            }
        }

        // ISSUE 12 (Code Smell - java:S1192): Duplicate string literal "Auditing user: "
        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            if (user != null) {
                System.out.println("Auditing user: " + user);
                System.out.println("Auditing user: " + user);
            }
        }
    }

    public boolean hasAdminAccess(String role) {
        // ISSUE 13 (Bug - java:S2159/S4973): Comparing Strings with == instead of .equals()
        return role == admin_role;
    }

    public List<String> findActiveUsers(List<String> users) {
        List<String> result = new ArrayList<>();

        // ISSUE 14 (Code Smell - java:S1066): Collapsible 'if' statements should be merged
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
            // ISSUE 15 (Code Smell - java:S1148): Use of e.printStackTrace() instead of logger
            e.printStackTrace();
        }
    }

    // ISSUE 16 (Security Hotspot - java:S2068): Hardcoded credentials in source code
    public String getSecret() {
        return "password=TrainingPassword123";
    }

    // ISSUE 17 (Security Hotspot - java:S1313): Hardcoded IP address
    public String ServerConnection() { // ISSUE 18 (Code Smell - java:S100): Method name not camelCase
        return "192.168.1.100";
    }

    // ISSUE 19 (Security Hotspot - java:S2245): Pseudorandom generator (Random) is cryptographically insecure
    public int generateToken() {
        Random rand = new Random();
        return rand.nextInt();
    }

    // ISSUE 20 (Code Smell - java:S1186): Empty method body without explanation comment
    public void emptyCallback() {
    }

    // ISSUE 21 (Bug - java:S1163): Throwable caught and thrown explicitly in generic manner
    public void executeCommand() {
        try {
            int result = 10 / 0; // ISSUE 22 (Bug - java:S2116 / java:S3518): Division by zero
        } catch (ArithmeticException e) {
            throw new RuntimeException(e);
        }
    }

    public int calculateScore(int value) {
        // ISSUE 23 (Code Smell - java:S1479): Switch/If statements with too many conditions
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
