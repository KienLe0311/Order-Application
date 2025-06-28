package com.example.orderapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;

    public OrderHistoryAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_oder_history, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.txtName.setText("Khách: " + order.getName());
        holder.txtTotal.setText("Tổng tiền: " + order.getTotal() + " VNĐ");

        holder.itemView.setOnClickListener(v -> {
            StringBuilder products = new StringBuilder();
            for (Product p : order.getProducts()) {
                products.append("- ").append(p.getName()).append(": ").append(p.getPrice()).append(" VNĐ\n");
            }

            String message = "👤 Họ tên: " + order.getName()
                    + "\n🏠 Địa chỉ: " + order.getAddress()
                    + "\n📞 SĐT: " + order.getPhone()
                    + "\n💰 Thanh toán: " + order.getPaymentMethod()
                    + "\n🛍️ Sản phẩm:\n" + products
                    + "\n🧾 Tổng tiền: " + order.getTotal() + " VNĐ";

            new AlertDialog.Builder(context)
                    .setTitle("Chi tiết đơn hàng")
                    .setMessage(message)
                    .setPositiveButton("Đóng", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtTotal;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtCustomer);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }
}