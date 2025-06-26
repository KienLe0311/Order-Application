package com.example.orderapplication;
import java.util.ArrayList;
public class Order {
    private String name;
    private String address;
    private String phone;
    private String paymentMethod;
    private ArrayList<Product> products;

    public Order(String name, String address, String phone, String paymentMethod, ArrayList<Product> products) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.paymentMethod = paymentMethod;
        this.products = products;
    }

    // Getters
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getPaymentMethod() { return paymentMethod; }
    public ArrayList<Product> getProducts() { return products; }

    public int getTotal() {
        int total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }
}
