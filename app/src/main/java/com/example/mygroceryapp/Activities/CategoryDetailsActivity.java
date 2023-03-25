package com.example.mygroceryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mygroceryapp.Adapters.CategoryDetailsAdapter;
import com.example.mygroceryapp.Products.CategoryDetailsProducts;
import com.example.mygroceryapp.databinding.ActivityCategoryDetailsBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CategoryDetailsActivity extends AppCompatActivity {
    ArrayList<CategoryDetailsProducts> categoryDetailsProducts = new ArrayList<>();
    CategoryDetailsAdapter categoryDetailsAdapter;
    FirebaseFirestore fireStore;
    ActivityCategoryDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fireStore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("details");
        binding.rvDetails.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
        categoryDetailsAdapter = new CategoryDetailsAdapter(categoryDetailsProducts, getBaseContext());
        binding.rvDetails.setHasFixedSize(true);
        binding.rvDetails.setAdapter(categoryDetailsAdapter);
        if (type != null && type.equalsIgnoreCase("drinks")) {
            fireStore.collection("CategoryDetailsProducts").whereEqualTo("type", "drinks").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        CategoryDetailsProducts categoryDetailsProducts2 = documentSnapshot.toObject(CategoryDetailsProducts.class);
                        categoryDetailsProducts.add(categoryDetailsProducts2);
                        categoryDetailsAdapter.notifyDataSetChanged();

                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("sweets")) {
            fireStore.collection("CategoryDetailsProducts").whereEqualTo("type", "sweets").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        CategoryDetailsProducts categoryDetailsProducts2 = documentSnapshot.toObject(CategoryDetailsProducts.class);
                        categoryDetailsProducts.add(categoryDetailsProducts2);
                        categoryDetailsAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("meat")) {
            fireStore.collection("CategoryDetailsProducts").whereEqualTo("type", "meat").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        CategoryDetailsProducts categoryDetailsProducts2 = documentSnapshot.toObject(CategoryDetailsProducts.class);
                        categoryDetailsProducts.add(categoryDetailsProducts2);
                        categoryDetailsAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("fruits")) {
            fireStore.collection("CategoryDetailsProducts").whereEqualTo("type", "fruits").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        CategoryDetailsProducts categoryDetailsProducts2 = documentSnapshot.toObject(CategoryDetailsProducts.class);
                        categoryDetailsProducts.add(categoryDetailsProducts2);
                        categoryDetailsAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("vegetables")) {
            fireStore.collection("CategoryDetailsProducts").whereEqualTo("type", "vegetables").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        CategoryDetailsProducts categoryDetailsProducts2 =
                                documentSnapshot.toObject(CategoryDetailsProducts.class);
                        categoryDetailsProducts.add(categoryDetailsProducts2);
                        categoryDetailsAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

    }
}