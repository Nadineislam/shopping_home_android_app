package com.example.mygroceryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mygroceryapp.Products.CartProducts;
import com.example.mygroceryapp.databinding.ActivityCheckoutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckoutActivity extends AppCompatActivity {
    ActivityCheckoutBinding binding;
    FirebaseFirestore fireStore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fireStore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        ArrayList<CartProducts> cartProducts = (ArrayList<CartProducts>) getIntent().getSerializableExtra("orderList");
        if (cartProducts != null && cartProducts.size() > 0) {
            for (CartProducts cart : cartProducts) {
                HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("productName", cart.getProductName());
                cartMap.put("productPrice", cart.getProductPrice());
                cartMap.put("totalQuantity", cart.getTotalQuantity());
                cartMap.put("totalPrice", cart.getTotalPrice());
                if(auth.getCurrentUser()!=null) {
                    fireStore.collection("Current Client").document(auth.getCurrentUser().getUid())
                            .collection("MyOrder").add(cartMap).addOnCompleteListener(task -> Toast.makeText(CheckoutActivity.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show());
                }   }
        }

    }
}