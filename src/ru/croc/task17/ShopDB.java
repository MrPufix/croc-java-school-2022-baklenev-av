package ru.croc.task17;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class ShopDB {
    private static final String connectionURL = "jdbc:h2:tcp://localhost/~/test";
    private static final String user = "sa";
    private static final String password = "";
    private static final String orderTableName = "ORD";
    private static final String productTableName = "PRODUCT";
    private static final String orderProductsTableName = "ORDER_PRODUCTS";

    public ShopDB() {
        Driver driver = new org.h2.Driver();
    }

    public void initDB() throws SQLException {
        try (Connection connection = DriverManager.getConnection(connectionURL, user, password)) {
            DatabaseMetaData meta = connection.getMetaData();

            ResultSet productTable = meta.getTables(null, null, productTableName, null);
            if (!productTable.next()) {
                // create product table if not exists
                try (Statement statement = connection.createStatement()) {
                    String sql = """
                            CREATE TABLE Product(
                              code VARCHAR(255) PRIMARY KEY,
                              name VARCHAR(255),
                              price INT
                            );""";
                    statement.executeUpdate(sql);
                }
            }

            ResultSet orderTable = meta.getTables(null, null, orderTableName, null);
            if (!orderTable.next()) {
                // create order table if not exists
                try (Statement statement = connection.createStatement()) {
                    String sql = """
                            CREATE TABLE Ord(
                            	ord_number INT PRIMARY KEY AUTO_INCREMENT,
                            	customer VARCHAR(255)
                            );""";
                    statement.executeUpdate(sql);
                }
            }

            ResultSet orderProductsTable = meta.getTables(null, null, orderProductsTableName, null);
            if (!orderProductsTable.next()) {
                // create order products table if not exists
                try (Statement statement = connection.createStatement()) {
                    String sql = """
                            CREATE TABLE Order_Products(
                            	id INT PRIMARY KEY AUTO_INCREMENT,
                            	ord_number INT NOT NULL,
                            	product_code VARCHAR(255) NOT NULL,
                            	FOREIGN KEY (ord_number) REFERENCES Ord(ord_number),
                            	FOREIGN KEY (product_code) REFERENCES Product(code)
                            );""";
                    statement.executeUpdate(sql);
                }
            }
        }
    }

    public void clearDB() throws SQLException {
        try (Connection connection = DriverManager.getConnection(connectionURL, user, password)) {
            DatabaseMetaData meta = connection.getMetaData();

            ResultSet orderProductsTable = meta.getTables(null, null, orderProductsTableName, null);
            if (orderProductsTable.next()) {
                try (Statement statement = connection.createStatement()) {
                    String sql = "DROP TABLE ORDER_PRODUCTS";
                    statement.executeUpdate(sql);
                }
            }

            ResultSet productTable = meta.getTables(null, null, productTableName, null);
            if (productTable.next()) {
                try (Statement statement = connection.createStatement()) {
                    String sql = "DROP TABLE PRODUCT";
                    statement.executeUpdate(sql);
                }
            }

            ResultSet orderTable = meta.getTables(null, null, orderTableName, null);
            if (orderTable.next()) {
                try (Statement statement = connection.createStatement()) {
                    String sql = "DROP TABLE ORD";
                    statement.executeUpdate(sql);
                }
            }
        }
    }

    /**
     * Adds data to database from csv file containing lines in format
     * <номер_заказа:integer>,<логин_пользователя:string>,<артикул_товара:string>,
     * <название_товара:string>,<цена_в_рублях:integer>
     * @param filePath - path to csv file
     */
    public void addDataFromCSV(String filePath) throws FileNotFoundException, SQLException {
        try (Scanner scanner = new Scanner(new File(filePath));
        Connection connection = DriverManager.getConnection(connectionURL, user, password)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                addDataFromStr(line, connection);
            }
        }
    }

    /**
     * Adds data to database from string in format
     * <номер_заказа:integer>,<логин_пользователя:string>,<артикул_товара:string>,
     * <название_товара:string>,<цена_в_рублях:integer>
     * @param text - string to add data from
     */
    private void addDataFromStr(String text, Connection connection) throws SQLException {
        String[] data = text.split(",");
        if(data.length < 5) return;

        int orderNumber = Integer.parseInt(data[0]);
        String customer = data[1];
        String productCode = data[2];
        String productName = data[3];
        int price = Integer.parseInt(data[4].strip());

        String sql = "SELECT * FROM Product WHERE code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productCode);
            try (ResultSet result = statement.executeQuery()) {
                if(!result.next()) {
                    //add product if not already added
                    String insSql = "INSERT INTO Product (code, name, price) VALUES (?, ?, ?)";
                    try (PreparedStatement insStatement = connection.prepareStatement(insSql)) {
                        insStatement.setString(1, productCode);
                        insStatement.setString(2, productName);
                        insStatement.setInt(3, price);
                        insStatement.executeUpdate();
                    }
                }
            }
        }

        sql = "SELECT * FROM Ord WHERE ord_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderNumber);
            try (ResultSet result = statement.executeQuery()) {
                if(!result.next()) {
                    //add order if not already added
                    String insSql = "INSERT INTO Ord (ord_number, customer) VALUES (?, ?)";
                    try (PreparedStatement insStatement = connection.prepareStatement(insSql)) {
                        insStatement.setInt(1, orderNumber);
                        insStatement.setString(2, customer);
                        insStatement.executeUpdate();
                    }
                }
            }
        }

        String insSql = "INSERT INTO Order_Products (ord_number, product_code) VALUES (?, ?)";
        try (PreparedStatement insStatement = connection.prepareStatement(insSql)) {
            insStatement.setInt(1, orderNumber);
            insStatement.setString(2, productCode);
            insStatement.executeUpdate();
        }
    }
}
