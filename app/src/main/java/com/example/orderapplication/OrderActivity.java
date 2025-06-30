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

        // Lấy danh sách sản phẩm từ Intent
        selectedProducts = getIntent().getParcelableArrayListExtra("selectedProducts");
        if (selectedProducts == null) {
            selectedProducts = new ArrayList<>();
        }

        // Thiết lập RecyclerView
        orderAdapter = new OrderAdapter(this, selectedProducts);
        recyclerOrder.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrder.setAdapter(orderAdapter);

        // Tính tổng tiền
        int total = 0;
        for (Product p : selectedProducts) {
            if (p != null) {
                total += p.getPrice();
            }
        }
        txtTotal.setText("Tổng tiền: " + total + " VNĐ");

        // Xử lý sự kiện nút xác nhận
        btnConfirm.setOnClickListener(v -> {
            if (selectedProducts.isEmpty()) {
                Toast.makeText(this, "Không có sản phẩm để đặt!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Chuyển sang màn hình thông tin khách hàng
            Intent intent = new Intent(OrderActivity.this, CustomerInfoActivity.class);
            intent.putParcelableArrayListExtra("orderedProducts", new ArrayList<>(selectedProducts));
            startActivity(intent);
            finish(); // Kết thúc activity hiện tại
        });
    }
}