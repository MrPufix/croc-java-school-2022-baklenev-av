package ru.croc.task17;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Task17 {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        ShopDB db = new ShopDB();
        db.initDB();
        db.addDataFromCSV(args[0]);
    }
}
