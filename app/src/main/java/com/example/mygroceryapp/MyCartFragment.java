package com.example.mygroceryapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mygroceryapp.Activities.CheckoutActivity;
import com.example.mygroceryapp.Adapters.CartAdapter;
import com.example.mygroceryapp.Products.CartProducts;
import com.example.mygroceryapp.databinding.FragmentMyCartBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;

public class MyCartFragment extends Fragment {
    StringBuilder stringBuilder;
    FirebaseFirestore db;
    FirebaseAuth auth;
    FragmentMyCartBinding binding;
    CartAdapter cartAdapter;
    ArrayList<CartProducts> cartProducts = new ArrayList<>();

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        binding.rvCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartAdapter = new CartAdapter(cartProducts, getActivity());
        binding.rvCart.setAdapter(cartAdapter);
        if(auth.getCurrentUser()!=null)
        db.collection("Current Client").document(auth.getCurrentUser().getUid()).
                collection("AddToCart").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            String documentId = documentSnapshot.getId();

                            CartProducts cartProducts2 = documentSnapshot.toObject(CartProducts.class);
                            cartProducts2.setDocumentId(documentId);
                            cartProducts.add(cartProducts2);
                            cartAdapter.notifyDataSetChanged();
                        }
                        calculateTotalAmount(cartProducts);
                    }
                });

        binding.btnCheckout.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CheckoutActivity.class);
            intent.putExtra("orderList", (Serializable) cartProducts);
            startActivity(intent);
        });


        return root;
    }

    public void calculateTotalAmount(ArrayList<CartProducts> cartList) {
        double totalAmount = 0.0;
        for (CartProducts cartList2 : cartProducts) {
            totalAmount += cartList2.getTotalPrice();
        }
        stringBuilder =new StringBuilder();
         String total_amount=" Total Amount= ";
        String priceSignatures=" L.E";
        stringBuilder.append(total_amount);
        stringBuilder.append(totalAmount);
        stringBuilder.append(priceSignatures);
        binding.tvCartPrice.setText(stringBuilder);
    }

}