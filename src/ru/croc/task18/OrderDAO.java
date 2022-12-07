package ru.croc.task18;

import java.sql.*;
import java.util.List;

public class OrderDAO {
    private static final String connectionURL = "jdbc:h2:tcp://localhost/~/test";
    private static final String user = "sa";
    private static final String password = "";

    Order createOrder(String userLogin, List<Product> products) throws SQLException {
        int ordNumber;
        try (Connection connection = DriverManager.getConnection(connectionURL, user, password)) {
            String sql = "Select max(ORD_NUMBER) From ORD";
            try (Statement statement = connection.createStatement()) {
                ResultSet result = statement.executeQuery(sql);
                if(result.next()) {
                    ordNumber = result.getInt("MAX(ORD_NUMBER)") + 1;
                } else
                    ordNumber = 1;
            }

            String insSql = "INSERT INTO Ord (ORD_NUMBER, customer) VALUES (?, ?)";
            try (PreparedStatement insStatement = connection.prepareStatement(insSql)) {
                insStatement.setInt(1, ordNumber);
                insStatement.setString(2, userLogin);
                insStatement.executeUpdate();
            }

            for(Product product : products) {
                insSql = "INSERT INTO Order_Products (ORD_NUMBER, product_code) VALUES (?, ?)";
                try (PreparedStatement insStatement = connection.prepareStatement(insSql)) {
                    insStatement.setInt(1, ordNumber);
                    insStatement.setString(2, product.code);
                    insStatement.executeUpdate();
                }
            }
        }
        return new Order(ordNumber, userLogin, products);
    }
}
