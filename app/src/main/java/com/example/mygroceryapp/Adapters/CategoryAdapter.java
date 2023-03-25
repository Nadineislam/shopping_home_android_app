package com.example.mygroceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygroceryapp.Products.CategoryProducts;
import com.example.mygroceryapp.R;
import com.example.mygroceryapp.databinding.CategoryItemsBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    OnClickListener onClickListener;

    Context context;
    ArrayList<CategoryProducts> categoryProductsArrayList;

    public CategoryAdapter(Context context, ArrayList<CategoryProducts> categoryProductsArrayList) {
        this.context = context;
        this.categoryProductsArrayList = categoryProductsArrayList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_items, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Glide.with(context).load(categoryProductsArrayList.get(position).getImage()).into(binding.ivCategory);
        binding.tvName.setText(categoryProductsArrayList.get(position).getName());
        binding.tvDesc.setText(categoryProductsArrayList.get(position).getDescription());
        binding.tvOff.setText(categoryProductsArrayList.get(position).getDiscount());
        holder.itemView.setOnClickListener(view -> onClickListener.onClick(holder.getAdapterPosition()));

    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return categoryProductsArrayList.size();
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    CategoryItemsBinding binding;

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryItemsBinding.bind(itemView);
        }
    }
}
