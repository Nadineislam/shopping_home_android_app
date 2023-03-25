package com.example.mygroceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygroceryapp.Products.PopularProducts;
import com.example.mygroceryapp.R;
import com.example.mygroceryapp.databinding.PopularItemsBinding;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {
    public OnClickItem OnClickItem;
    ArrayList<PopularProducts> popularModels;
    Context context;

    public PopularAdapter(ArrayList<PopularProducts> popularModels, Context context) {
        this.popularModels = popularModels;
        this.context = context;
    }

    public void setOnClickItem(OnClickItem OnClickItem) {
        this.OnClickItem = OnClickItem;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items, parent, false);
        return new PopularViewHolder(v);
    }

    public interface OnClickItem {
        void onClick(int pos);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {

        Glide.with(context).load(popularModels.get(position).getImage()).into(binding.img);
        binding.name.setText(popularModels.get(position).getName());
        binding.desc.setText(popularModels.get(position).getDescription());
        binding.discount.setText(popularModels.get(position).getDiscount());
        if (OnClickItem != null) {
            holder.itemView.setOnClickListener(view -> OnClickItem.onClick(holder.getAdapterPosition()));

        }

    }


    @Override
    public int getItemCount() {
        return popularModels.size();
    }

    PopularItemsBinding binding;

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = PopularItemsBinding.bind(itemView);
        }
    }
}
