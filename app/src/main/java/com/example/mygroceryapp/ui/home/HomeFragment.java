package com.example.mygroceryapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygroceryapp.Activities.ViewAllActivity;
import com.example.mygroceryapp.Adapters.HomeAdapter;
import com.example.mygroceryapp.Adapters.PopularAdapter;
import com.example.mygroceryapp.Adapters.RecommendedAdapter;
import com.example.mygroceryapp.Adapters.ViewAllAdapter;
import com.example.mygroceryapp.Products.HomeProducts;
import com.example.mygroceryapp.Products.PopularProducts;
import com.example.mygroceryapp.Products.RecommendedProducts;
import com.example.mygroceryapp.Products.ViewAllProducts;
import com.example.mygroceryapp.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FirebaseFirestore db;
    FragmentHomeBinding binding;
    //for popular products
    PopularAdapter popularAdapter;
    ArrayList<PopularProducts> popularModels = new ArrayList<>();
    //for home products
    HomeAdapter homeAdapter;
    ArrayList<HomeProducts> homeProductsArrayList = new ArrayList<>();
    //for recommended products
    RecommendedAdapter recommendedAdapter;
    ArrayList<RecommendedProducts> recommendedProductsArrayList = new ArrayList<>();

    //for search products
    ArrayList<ViewAllProducts> viewAllProducts = new ArrayList<>();
    ViewAllAdapter viewAllAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.scrollView.setVisibility(View.GONE);
        db = FirebaseFirestore.getInstance();
        //for popular products
        binding.firstRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularAdapter = new PopularAdapter(popularModels, getActivity());
        binding.firstRv.setHasFixedSize(true);
        binding.firstRv.setAdapter(popularAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            PopularProducts popularModel = document.toObject(PopularProducts.class);
                            popularModels.add(popularModel);
                            popularAdapter.notifyDataSetChanged();
                            binding.progressBar.setVisibility(View.GONE);
                            binding.scrollView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(getActivity(), "empty", Toast.LENGTH_SHORT).show();
                    }
                });

        //for home products
        db = FirebaseFirestore.getInstance();
        binding.secRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeAdapter = new HomeAdapter(homeProductsArrayList, getActivity());
        binding.secRv.setHasFixedSize(true);
        binding.secRv.setAdapter(homeAdapter);


        db.collection("HomeProducts")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            HomeProducts homeProducts = document.toObject(HomeProducts.class);
                            homeProductsArrayList.add(homeProducts);
                            homeAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(getActivity(), "empty", Toast.LENGTH_SHORT).show();
                    }
                });


        db = FirebaseFirestore.getInstance();
        binding.thirdRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendedAdapter = new RecommendedAdapter(recommendedProductsArrayList, getActivity());
        binding.thirdRv.setHasFixedSize(true);
        binding.thirdRv.setAdapter(recommendedAdapter);


        db.collection("RecommendedProducts")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            RecommendedProducts r = document.toObject(RecommendedProducts.class);
                            recommendedProductsArrayList.add(r);
                            recommendedAdapter.notifyDataSetChanged();

                        }
                    } else {
                        Toast.makeText(getActivity(), "empty", Toast.LENGTH_SHORT).show();
                    }
                });
        popularAdapter.setOnClickItem(pos -> {
            Intent intent = new Intent(getContext(), ViewAllActivity.class);
            intent.putExtra("type", popularModels.get(pos).getType());
            startActivity(intent);
        });
        homeAdapter.setOnClickItem(pos -> {
            Intent intent = new Intent(getContext(), ViewAllActivity.class);
            intent.putExtra("type", homeProductsArrayList.get(pos).getType());
            startActivity(intent);
        });
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvSearch.setHasFixedSize(true);
        viewAllAdapter = new ViewAllAdapter(getContext(), viewAllProducts);
        binding.rvSearch.setAdapter(viewAllAdapter);
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    viewAllProducts.clear();
                    viewAllAdapter.notifyDataSetChanged();
                } else {
                    searchProduct(editable.toString());
                }

            }
        });

        return root;
    }

    private void searchProduct(String type) {
        if (!type.isEmpty()) {
            db.collection("ViewAllProducts").whereEqualTo("type", type).get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    viewAllProducts.clear();
                    viewAllAdapter.notifyDataSetChanged();
                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        ViewAllProducts viewAllProducts2 = document.toObject(ViewAllProducts.class);
                        viewAllProducts.add(viewAllProducts2);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }


}