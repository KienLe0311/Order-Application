package com.example.orderapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Order implements Parcelable {
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
        this.products = products != null ? products : new ArrayList<>();
    }

    // Constructor for Parcelable
    protected Order(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        paymentMethod = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
    }

    // Parcelable Creator
    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    // Parcelable Methods
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(phone);
        parcel.writeString(paymentMethod);
        parcel.writeTypedList(products);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getters
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getPaymentMethod() { return paymentMethod; }
    public ArrayList<Product> getProducts() { return products; }

    public int getTotal() {
        int total = 0;
        if (products != null) {
            for (Product p : products) {
                total += p.getPrice();
            }
        }
        return total;
    }
}