package com.example.orderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private Button btnOrder;
    private ImageView menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnOrder = findViewById(R.id.btnOrder);
        menuButton = findViewById(R.id.menuButton);
        productList = new ArrayList<>();

        // Khởi tạo danh sách sản phẩm
        productList.add(new Product("Áo AC Milan 2006-2007", 350000, R.drawable.image1));
        productList.add(new Product("Áo AC Milan 2005-2006", 800000, R.drawable.image2));
        productList.add(new Product("Áo AC Milan 2022-2023", 320000, R.drawable.image3));

        productAdapter = new ProductAdapter(this, productList, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        // Xử lý sự kiện nút đặt hàng
        btnOrder.setOnClickListener(view -> {
            List<Product> selected = productAdapter.getSelectedProducts();
            if (selected.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất 1 sản phẩm!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putParcelableArrayListExtra("selectedProducts", new ArrayList<>(selected));
                startActivity(intent);
            }
        });

        menuButton.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, menuButton);
            popupMenu.getMenuInflater().inflate(R.menu.menu_context, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.item_view_order) {
                    Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });
    }
}