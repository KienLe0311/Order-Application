package com.example.orderapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private List<Product> selectedList;
    private boolean showCheckbox;

    public ProductAdapter(Context context, List<Product> productList, boolean showCheckbox) {
        this.context = context;
        this.productList = productList != null ? productList : new ArrayList<>();
        this.selectedList = new ArrayList<>();
        this.showCheckbox = showCheckbox;
    }

    public List<Product> getSelectedProducts() {
        return selectedList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText(product.getPrice() + " VNĐ");
        holder.imgProduct.setImageResource(product.getImageResId());

        // Ẩn hoặc hiện checkbox
        if (showCheckbox) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setOnCheckedChangeListener(null);
            holder.checkBox.setChecked(selectedList.contains(product));
            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    selectedList.add(product);
                } else {
                    selectedList.remove(product);
                }
            });
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice;
        ImageView imgProduct;
        CheckBox checkBox;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvTenSanPham);
            txtPrice = itemView.findViewById(R.id.tvGiaSanPham);
            imgProduct = itemView.findViewById(R.id.imageSP);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}