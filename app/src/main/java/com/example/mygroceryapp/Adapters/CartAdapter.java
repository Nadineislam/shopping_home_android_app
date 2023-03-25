package com.example.mygroceryapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygroceryapp.Products.CartProducts;
import com.example.mygroceryapp.R;
import com.example.mygroceryapp.databinding.CartItemsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    ArrayList<CartProducts> cartProducts;
    Context context;
    int totalPrice = 0;
    FirebaseFirestore fireStore;
    FirebaseAuth auth;


    public CartAdapter(ArrayList<CartProducts> cartProducts, Context context) {
        this.cartProducts = cartProducts;
        this.context = context;
        fireStore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        binding.productNameVal.setText(cartProducts.get(position).getProductName());
        binding.productPriceVal.setText(cartProducts.get(position).getProductPrice());
        binding.totalQuantityVal.setText(cartProducts.get(position).getTotalQuantity());
        binding.totalPriceVal.setText(String.valueOf(cartProducts.get(position).getTotalPrice()));
        if(auth.getCurrentUser()!=null){
        binding.delete.setOnClickListener(view -> fireStore.collection("Current Client").
                document(auth.getCurrentUser().getUid())
                .collection("AddToCart").document(cartProducts.get(holder.getAdapterPosition()).
                        getDocumentId()).delete().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        cartProducts.remove(cartProducts.get(holder.getAdapterPosition()));
                        notifyItemChanged(holder.getAdapterPosition());
                        Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
                    }
                }));}


        totalPrice += cartProducts.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalPrice");
        intent.putExtra("totalPrice", totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    CartItemsBinding binding;

    public class CartViewHolder extends RecyclerView.ViewHolder {

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartItemsBinding.bind(itemView);
        }
    }
}
