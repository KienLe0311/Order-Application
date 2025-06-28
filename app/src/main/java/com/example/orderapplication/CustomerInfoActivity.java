package com.example.orderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CustomerInfoActivity extends AppCompatActivity {
    private EditText edtName, edtAddress, edtPhone;
    private RadioGroup radioGroupPayment;
    private RadioButton rdoZaloPay, rdoMomo;
    private Button btnConfirmPayment;
    private ArrayList<Product> orderedProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info); // Đúng tên file XML

        edtName = findViewById(R.id.editTextText);
        edtAddress = findViewById(R.id.editTextText1);
        edtPhone = findViewById(R.id.editTextText2);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        rdoZaloPay = findViewById(R.id.radioButtonZalo);
        rdoMomo = findViewById(R.id.radioButtonMomo);
        btnConfirmPayment = findViewById(R.id.button);
        orderedProducts = getIntent().getParcelableArrayListExtra("orderedProducts");


        btnConfirmPayment.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            int selectedId = radioGroupPayment.getCheckedRadioButtonId();


            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedId == -1) {
                Toast.makeText(this, "Vui lòng chọn phương thức thanh toán!", Toast.LENGTH_SHORT).show();
                return;
            }

            String payment = (selectedId == R.id.radioButtonZalo) ? "ZaloPay" : "MOMO";

            if (orderedProducts != null && !orderedProducts.isEmpty()) {
                Order newOrder = new Order(name, address, phone, payment, new ArrayList<>(orderedProducts));
                SharedData.orderHistory.add(newOrder);
            }


            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(CustomerInfoActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }
}
