package com.example.mygroceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygroceryapp.Products.RecommendedProducts;
import com.example.mygroceryapp.R;
import com.example.mygroceryapp.databinding.RecommendedItemsBinding;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> {

    ArrayList<RecommendedProducts> recommendedProductsArrayList;
    Context context;

    public RecommendedAdapter(ArrayList<RecommendedProducts> recommendedProductsArrayList, Context context) {
        this.recommendedProductsArrayList = recommendedProductsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_items, parent, false);
        return new RecommendedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {
        Glide.with(context).load(recommendedProductsArrayList.get(position).getImage()).into(binding.ivRecommended);
        binding.nameRecommended.setText(recommendedProductsArrayList.get(position).getName());
        binding.descRecommended.setText(recommendedProductsArrayList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return recommendedProductsArrayList.size();
    }

    RecommendedItemsBinding binding;

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {

        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecommendedItemsBinding.bind(itemView);
        }
    }
}
