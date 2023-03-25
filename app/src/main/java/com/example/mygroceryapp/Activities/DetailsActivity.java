package com.example.mygroceryapp.Activities;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mygroceryapp.Products.ViewAllProducts;
import com.example.mygroceryapp.databinding.ActivityDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    int quantity = 1;
    int totalPrice = 0;
    FirebaseFirestore fireStore;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fireStore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        ViewAllProducts viewAllProducts = (ViewAllProducts) getIntent().getSerializableExtra("detailItem");
        if (viewAllProducts != null) {

            Glide.with(getApplicationContext()).load(viewAllProducts.getImage()).into(binding.ivDetails);
            binding.tvPriceDetails.setText("Price: " + viewAllProducts.getPrice() + " L.E");
            binding.tvDescDetail.setText(viewAllProducts.getDescription());
            totalPrice = viewAllProducts.getPrice() * quantity;
            binding.ivAdd.setOnClickListener(view -> {
                if (quantity < 10) {
                    quantity++;
                    binding.tvNumber.setText(String.valueOf(quantity));
                }
            });
            binding.ivRemove.setOnClickListener(view -> {
                if (quantity > 1) {
                    quantity--;
                    binding.tvNumber.setText(String.valueOf(quantity));

                }
            });
            binding.btnAddToCart.setOnClickListener(view -> {
                HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("productName", viewAllProducts.getName());
                cartMap.put("productPrice", binding.tvPriceDetails.getText().toString());
                cartMap.put("totalQuantity", binding.tvNumber.getText().toString());
                totalPrice = viewAllProducts.getPrice() * quantity;
                cartMap.put("totalPrice", totalPrice);
                if(firebaseAuth.getCurrentUser()!=null)
                fireStore.collection("Current Client").document(firebaseAuth.getCurrentUser().getUid())
                        .collection("AddToCart").add(cartMap).addOnCompleteListener(task -> {
                            Toast.makeText(DetailsActivity.this, "Added to cart successfully", Toast.LENGTH_SHORT).show();
                            finish();

                        });
            });
        }


    }
}