package ru.itis.ec.app;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/data", "fedor", "");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM car");
            while (result.next()) {
                String mark = result.getString("mark");
                String model = result.getString("model");
                String number = result.getString("number");
                int yearOfManufacture = result.getInt("year_of_manufacture");
                System.out.println(mark + " " + model + " " + yearOfManufacture + " " + number);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }
}
