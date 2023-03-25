package com.example.mygroceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygroceryapp.Products.ViewAllProducts;
import com.example.mygroceryapp.R;
import com.example.mygroceryapp.databinding.ViewAllItemsBinding;

import java.util.ArrayList;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewAllViewHolder> {
    OnClickItem onClickItem;
    Context context;
    ArrayList<ViewAllProducts> viewAllProductsArrayList;
    StringBuilder stringBuilder;
    public ViewAllAdapter(Context context, ArrayList<ViewAllProducts> viewAllProductsArrayList) {
        this.context = context;
        this.viewAllProductsArrayList = viewAllProductsArrayList;
    }

    @NonNull
    @Override
    public ViewAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_items, parent, false);
        return new ViewAllViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllViewHolder holder, int position) {
        Glide.with(context).load(viewAllProductsArrayList.get(position).getImage()).into(binding.iv2Category);
        binding.viewName.setText(viewAllProductsArrayList.get(position).getName());
        binding.viewDesc.setText(viewAllProductsArrayList.get(position).getDescription());
        String price=String.valueOf(viewAllProductsArrayList.get(position).getPrice());
        String priceSignature=" L.E";
        stringBuilder=new StringBuilder();
        stringBuilder.append(price);
        stringBuilder.append(priceSignature);
        binding.viewPrice.setText(stringBuilder);
        if (onClickItem != null) {
            holder.itemView.setOnClickListener(view -> onClickItem.onClick(holder.getAdapterPosition()));
        }
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public int getItemCount() {
        return viewAllProductsArrayList.size();
    }

    public interface OnClickItem {
        void onClick(int pos);
    }

    ViewAllItemsBinding binding;

    public class ViewAllViewHolder extends RecyclerView.ViewHolder {

        public ViewAllViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ViewAllItemsBinding.bind(itemView);
        }
    }
}
