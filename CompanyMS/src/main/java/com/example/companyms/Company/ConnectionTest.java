package com.example.companyms.Company;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/jobapp";
        String username = "postgres";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            if (connection != null) {
                System.out.println("Connection established!");
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
