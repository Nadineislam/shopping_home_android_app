package com.example.mygroceryapp.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygroceryapp.Activities.CategoryDetailsActivity;
import com.example.mygroceryapp.Adapters.CategoryAdapter;
import com.example.mygroceryapp.Adapters.PopularAdapter;
import com.example.mygroceryapp.Products.CategoryProducts;
import com.example.mygroceryapp.Products.PopularProducts;
import com.example.mygroceryapp.databinding.FragmentCategoryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    FirebaseFirestore db;
   ArrayList<CategoryProducts> categoryProductsArrayList=new ArrayList<>();
   CategoryAdapter categoryAdapter;

    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = FirebaseFirestore.getInstance();
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        categoryAdapter = new CategoryAdapter(getActivity(),categoryProductsArrayList);
        binding.rvCategory.setHasFixedSize(true);
        binding.rvCategory.setAdapter(categoryAdapter);
        db.collection("CategoryProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryProducts categoryProducts = document.toObject(CategoryProducts.class);
                                categoryProductsArrayList.add(categoryProducts);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        categoryAdapter.setOnClickListener(new CategoryAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity(), CategoryDetailsActivity.class);
                intent.putExtra("details",categoryProductsArrayList.get(position).getType());
                startActivity(intent);
            }
        });
        return root;

    }

}