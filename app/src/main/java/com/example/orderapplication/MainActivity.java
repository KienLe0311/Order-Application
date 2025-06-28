package com.example.orderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

        productList.add(new Product("Áo MU", 350000, R.drawable.image1));
        productList.add(new Product("King of club", 800000, R.drawable.image2));
        productList.add(new Product("3 C1", 320000, R.drawable.image3));

        productAdapter = new ProductAdapter(this, productList,true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        btnOrder.setOnClickListener(view -> {
            List<Product> selected = productAdapter.getSelectedProducts();
            if (selected.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất 1 sản phẩm!", Toast.LENGTH_SHORT).show();
            } else {
                StringBuilder result = new StringBuilder("Bạn đã đặt: \n");
                for (Product p : selected) {
                    result.append("- ").append(p.getName()).append("\n");
                }
                Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
            }
        });

        menuButton.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, menuButton);
            popupMenu.getMenuInflater().inflate(R.menu.menu_context, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.item_view_order) {
                    // Xử lý khi bấm "Xem hồ sơ đặt hàng"
                    Toast.makeText(MainActivity.this, "Mở hồ sơ đặt hàng", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
                    startActivity(intent);
                    // hoặc mở Activity mới, hoặc Dialog,...
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });
        btnOrder.setOnClickListener(view -> {
            List<Product> selected = productAdapter.getSelectedProducts();
            if (selected.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất 1 sản phẩm!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                ArrayList<Product> selectedList = new ArrayList<>(productAdapter.getSelectedProducts());
                intent.putParcelableArrayListExtra("selectedProducts", selectedList);
                startActivity(intent);
            }
        });

    }
}