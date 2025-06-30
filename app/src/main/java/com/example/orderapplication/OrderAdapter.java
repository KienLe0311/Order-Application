package com.example.orderapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    private List<Product> orderList;

    public OrderAdapter(Context context, List<Product> orderList) {
        this.context = context;
        this.orderList = orderList != null ? orderList : new ArrayList<>();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Product p = orderList.get(position);
        holder.txtName.setText(p.getName());
        holder.txtPrice.setText(p.getPrice() + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvTenSanPham);
            txtPrice = itemView.findViewById(R.id.tvGiaSanPham);
        }
    }
}