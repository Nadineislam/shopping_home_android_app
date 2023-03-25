package com.example.mygroceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygroceryapp.Products.CategoryDetailsProducts;
import com.example.mygroceryapp.R;
import com.example.mygroceryapp.databinding.CategoryDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailsViewHolder> {
    ArrayList<CategoryDetailsProducts> categoryDetailsProducts;
    Context context;
    int quantity=1;
    int totalPrice = 0;
    StringBuilder stringBuilder;
    FirebaseAuth auth;
    FirebaseFirestore fireStore;

    public CategoryDetailsAdapter(ArrayList<CategoryDetailsProducts> categoryDetailsProducts, Context context) {
        this.categoryDetailsProducts = categoryDetailsProducts;
        this.context = context;
        fireStore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }


    @NonNull
    @Override
    public CategoryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_details, parent, false);
        return new CategoryDetailsViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryDetailsViewHolder holder, int position) {
        Glide.with(context).load(categoryDetailsProducts.get(position).getImage()).into(binding.ivCategory);
        String price=String.valueOf(categoryDetailsProducts.get(position).getPrice());
        String priceSignature=" L.E";
        stringBuilder=new StringBuilder();
        stringBuilder.append(price);
        stringBuilder.append(priceSignature);
        binding.tvPrice.setText(stringBuilder);
        binding.tvName.setText(categoryDetailsProducts.get(position).getName());

        binding.btnBuyNow.setOnClickListener(view -> {
            HashMap<String, Object> cartMap = new HashMap<>();
            cartMap.put("productName", categoryDetailsProducts.get(holder.getAdapterPosition()).getName());
            cartMap.put("productPrice", binding.tvPrice.getText().toString());
            cartMap.put("totalQuantity", binding.tvCount.getText().toString());
            totalPrice = categoryDetailsProducts.get(holder.getAdapterPosition()).getPrice() *
                    quantity;
            cartMap.put("totalPrice", totalPrice);
            if (auth.getCurrentUser() != null)
                fireStore.collection("Current Client").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .add(cartMap)
                        .addOnCompleteListener
                                (task -> Toast.makeText(context, "Added To Cart Successfully", Toast.LENGTH_SHORT).show());

        });


        binding.ivPlus.setOnClickListener(view -> {
            if (quantity < 10) {
                holder.getAdapterPosition();
                quantity++;
                binding.tvCount.setText(String.valueOf(quantity));
            }
        });
        binding.ivMinus.setOnClickListener(view -> {
            if (quantity > 1) {
                holder.getAdapterPosition();
                quantity--;
                binding.tvCount.setText(String.valueOf(quantity));
            }
        });




    }


    @Override
    public int getItemCount() {
        return categoryDetailsProducts.size();
    }

    CategoryDetailsBinding binding;

    public class CategoryDetailsViewHolder extends RecyclerView.ViewHolder {

        public CategoryDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryDetailsBinding.bind(itemView);
        }
    }

}
