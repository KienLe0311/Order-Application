package com.example.orderapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedData {
    private static final List<Order> orderHistory = Collections.synchronizedList(new ArrayList<>());

    public static List<Order> getOrderHistory() {
        return orderHistory;
    }
}