package ru.croc.task18;

import ru.croc.task17.ShopDB;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class Task18 {

    private static void testFindPr(ProductDAO productDAO, String productCode) {
        Product pr = productDAO.findProduct(productCode);
        if(pr != null)
            System.out.println(pr.name + " " + pr.price);
        else
            System.out.println("Not found");
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        ShopDB db = new ShopDB();
        db.clearDB();
        db.initDB();
        db.addDataFromCSV("task17.csv");

        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();

        testFindPr(productDAO, "Т1");
        testFindPr(productDAO, "Т2");
        testFindPr(productDAO, "Т3");
        testFindPr(productDAO, "Т4");
        testFindPr(productDAO, "Т5");
        testFindPr(productDAO, "Т6");

        Product sofa = new Product("Т6", "Диван", 45);
        productDAO.createProduct(sofa);
        testFindPr(productDAO, "Т6");

        sofa = productDAO.updateProduct(new Product(sofa.code, "Не диван", 4));
        testFindPr(productDAO, "Т6");

        productDAO.deleteProduct("Т6");
        testFindPr(productDAO, "Т6");

        Product table = new Product("Т7", "Стол", 15);
        productDAO.createProduct(table);
        testFindPr(productDAO, "Т7");

        orderDAO.createOrder("jo", List.of(table));
    }
}
