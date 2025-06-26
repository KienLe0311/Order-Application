package com.example.orderapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Product
{
    private String name;
    private int price;
    private int image;
    public Product(String name, int price, int image)
    {
        this.name = name;
        this.price = price;
        this.image = image;
    }
    protected Product(Parcel in)
    {
        name = in.readString();
        price = in.readInt();
        image = in.readInt();
    }
public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>()
{   @Override
    public Product createFromParcel(Parcel in) {
        return new Product(in);
    }

    @Override
    public Product[] newArray(int size) {
        return new Product[size];
    }
};

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeInt(image);
    }


    public int describeContents() {
        return 0;
    }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImageResId() { return image; }

}
