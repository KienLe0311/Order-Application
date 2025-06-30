package com.example.orderapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderHistoryAdapter adapter;
    private ArrayList<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        recyclerView = findViewById(R.id.recyclerOrderHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orders = new ArrayList<>(SharedData.getOrderHistory());
        adapter = new OrderHistoryAdapter(this, orders);
        recyclerView.setAdapter(adapter);
    }
}