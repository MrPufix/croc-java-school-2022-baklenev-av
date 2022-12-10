package ru.croc.task18;

import java.util.List;

public class Order {
    private final int ordNumber;
    private final String customer;
    private final List<Product> products;

    public Order(int ordNumber, String customer, List<Product> products) {
        this.ordNumber = ordNumber;
        this.customer = customer;
        this.products = products;
    }

    public int getOrd_number() {
        return ordNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }
}
