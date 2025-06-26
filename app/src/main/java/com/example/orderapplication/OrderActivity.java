package com.example.orderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerOrder;
    private TextView txtTotal;
    private Button btnConfirm;
    private List<Product> selectedProducts;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        recyclerOrder = findViewById(R.id.recyclerViewGioHang);
        txtTotal = findViewById(R.id.tvTongTien);
        btnConfirm = findViewById(R.id.btnThanhToan);

        selectedProducts = getIntent().getParcelableArrayListExtra("selectedProducts");

        orderAdapter = new OrderAdapter(this, selectedProducts);
        recyclerOrder.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrder.setAdapter(orderAdapter);



        int total = 0;
        for (Product p : selectedProducts) {
            total += p.getPrice();
        }
        txtTotal.setText("Tổng tiền: " + total + " VNĐ");

        btnConfirm.setOnClickListener(v -> {
            if (selectedProducts == null || selectedProducts.isEmpty()) {
                Toast.makeText(this, "Không có sản phẩm để đặt!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(OrderActivity.this, CustomerInfoActivity.class);
            intent.putParcelableArrayListExtra("orderedProducts", new ArrayList<>(selectedProducts));
            startActivity(intent);
        });

    }