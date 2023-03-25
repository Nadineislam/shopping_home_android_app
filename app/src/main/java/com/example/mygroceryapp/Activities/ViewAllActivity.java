package com.example.mygroceryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mygroceryapp.Adapters.ViewAllAdapter;
import com.example.mygroceryapp.Products.ViewAllProducts;
import com.example.mygroceryapp.databinding.ActivityViewAllBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {
    ArrayList<ViewAllProducts> viewAllProductsArrayList = new ArrayList<>();
    ViewAllAdapter viewAllAdapter;
    FirebaseFirestore fireStore;
    ActivityViewAllBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        fireStore = FirebaseFirestore.getInstance();
        binding.viewRecycler.setLayoutManager(new LinearLayoutManager(this));
        viewAllAdapter = new ViewAllAdapter(this, viewAllProductsArrayList);
        binding.viewRecycler.setHasFixedSize(true);
        binding.viewRecycler.setAdapter(viewAllAdapter);
        if (type != null && type.equalsIgnoreCase("fruits")) {
            fireStore.collection("ViewAllProducts")
                    .whereEqualTo("type", "fruits").get().addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                ViewAllProducts viewProducts = documentSnapshot.toObject(ViewAllProducts.class);
                                viewAllProductsArrayList.add(viewProducts);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "empty", Toast.LENGTH_SHORT).show();
                        }

                    });
        }
        if (type != null && type.equalsIgnoreCase("vegetables")) {
            fireStore.collection("ViewAllProducts").whereEqualTo("type", "vegetables").get().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllProducts viewProducts = documentSnapshot.toObject(ViewAllProducts.class);
                        viewAllProductsArrayList.add(viewProducts);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "empty", Toast.LENGTH_SHORT).show();
                }

            });

        }
        if (type != null && type.equalsIgnoreCase("milk")) {
            fireStore.collection("ViewAllProducts").whereEqualTo("type", "milk").get().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllProducts viewProducts = documentSnapshot.toObject(ViewAllProducts.class);
                        viewAllProductsArrayList.add(viewProducts);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "empty", Toast.LENGTH_SHORT).show();
                }

            });

        }
        if (type != null && type.equalsIgnoreCase("fish")) {
            fireStore.collection("ViewAllProducts").whereEqualTo("type", "fish").get().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllProducts viewProducts = documentSnapshot.toObject(ViewAllProducts.class);
                        viewAllProductsArrayList.add(viewProducts);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "empty", Toast.LENGTH_SHORT).show();
                }

            });

        }
        if (type != null && type.equalsIgnoreCase("egg")) {
            fireStore.collection("ViewAllProducts").whereEqualTo("type", "egg").get().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllProducts viewProducts = documentSnapshot.toObject(ViewAllProducts.class);
                        viewAllProductsArrayList.add(viewProducts);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }

            });

        }
        viewAllAdapter.setOnClickItem(pos -> {
            Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
            intent.putExtra("detailItem", viewAllProductsArrayList.get(pos));
            startActivity(intent);
        });


    }
}