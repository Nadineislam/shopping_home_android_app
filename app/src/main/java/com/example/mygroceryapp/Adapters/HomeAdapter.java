package com.example.mygroceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygroceryapp.Products.HomeProducts;
import com.example.mygroceryapp.R;
import com.example.mygroceryapp.databinding.HomeItemsBinding;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    public PopularAdapter.OnClickItem OnClickItem;
    ArrayList<HomeProducts> homeProductsArrayList;
    Context context;

    public HomeAdapter(ArrayList<HomeProducts> homeProductsArrayList, Context context) {
        this.homeProductsArrayList = homeProductsArrayList;
        this.context = context;
    }

    public void setOnClickItem(PopularAdapter.OnClickItem OnClickItem) {
        this.OnClickItem = OnClickItem;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_items, parent, false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Glide.with(context).load(homeProductsArrayList.get(position).getImage()).into(binding.ivHome);
        binding.tvHome.setText(homeProductsArrayList.get(position).getName());
        if (OnClickItem != null) {
            holder.itemView.setOnClickListener(view -> OnClickItem.onClick(holder.getAdapterPosition()));

        }

    }


    @Override
    public int getItemCount() {
        return homeProductsArrayList.size();
    }

    HomeItemsBinding binding;

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeItemsBinding.bind(itemView);
        }
    }

}
