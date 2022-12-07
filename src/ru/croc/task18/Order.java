package ru.croc.task18;

import java.util.List;

public class Order {
    int ord_number;
    String customer;
    List<Product> products;

    public Order(int ord_number, String customer, List<Product> products) {
        this.ord_number = ord_number;
        this.customer = customer;
        this.products = products;
    }

    public int getOrd_number() {
        return ord_number;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }
}
