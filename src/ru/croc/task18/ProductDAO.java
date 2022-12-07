package ru.croc.task18;

import java.sql.*;

public class ProductDAO {
    private static final String connectionURL = "jdbc:h2:tcp://localhost/~/test";
    private static final String user = "sa";
    private static final String password = "";

    Product findProduct(String productCode) {
        String sql = "SELECT * FROM Product WHERE code = ?";
        try (Connection connection = DriverManager.getConnection(connectionURL, user, password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, productCode);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        String code = result.getString("code");
                        String name = result.getString("name");
                        int price = result.getInt("price");
                        return new Product(code, name, price);
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            return null;
        }
    }



    Product createProduct(Product product) throws SQLException {
        String sql = "SELECT * FROM Product WHERE code = ?";
        try (Connection connection = DriverManager.getConnection(connectionURL, user, password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.code);
                try (ResultSet result = statement.executeQuery()) {
                    if (!result.next()) {
                        //add product if not already added
                        String insSql = "INSERT INTO Product (code, name, price) VALUES (?, ?, ?)";
                        try (PreparedStatement insStatement = connection.prepareStatement(insSql)) {
                            insStatement.setString(1, product.code);
                            insStatement.setString(2, product.name);
                            insStatement.setInt(3, product.price);
                            insStatement.executeUpdate();
                            return product;
                        }
                    } else
                        throw new SQLException();
                }
            }
        }
    }

    Product updateProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(connectionURL, user, password)) {
            String sql = "UPDATE Product SET name = ?, price = ? WHERE code = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.name);
                statement.setInt(2, product.price);
                statement.setString(3, product.code);
                if(statement.executeUpdate() > 0)
                    return product;
                else
                    return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    void deleteProduct(String productCode) throws SQLException {
        try (Connection connection = DriverManager.getConnection(connectionURL, user, password)) {
            String delOrdSql = "DELETE FROM ORDER_PRODUCTS WHERE product_code = ?";
            try (PreparedStatement statement = connection.prepareStatement(delOrdSql)) {
                statement.setString(1, productCode);
                statement.executeUpdate();
            }

            String delProdSql = "DELETE FROM Product WHERE code = ?";
            try (PreparedStatement statement = connection.prepareStatement(delProdSql)) {
                statement.setString(1, productCode);
                statement.executeUpdate();
            }
        }
    }
}
