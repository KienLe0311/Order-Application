package com.example.orderapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String name;
    private int price;
    private int image;

    // Constructor
    public Product(String name, int price, int image) {
        this.name = name != null ? name : "";
        this.price = price;
        this.image = image;
    }

    // Constructor for Parcelable
    protected Product(Parcel in) {
        name = in.readString();
        price = in.readInt();
        image = in.readInt();
    }

    // Parcelable Creator
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    // Parcelable Methods
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getters
    public String getName() {
        return name != null ? name : "";
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return image;
    }
}