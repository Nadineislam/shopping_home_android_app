package com.example.mygroceryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mygroceryapp.MainActivity;
import com.example.mygroceryapp.UserModel;
import com.example.mygroceryapp.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    FirebaseDatabase db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        binding.tvLog.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        });
        binding.signBtn.setOnClickListener(view -> {
            String username = binding.signUserName.getText().toString();
            String email = binding.signEmail.getText().toString();
            String password = binding.signPassword.getText().toString();
            if (TextUtils.isEmpty(username)) {
                binding.signUserName.setError("username is empty!");
                binding.signUserName.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                binding.signEmail.setError("email is empty!");
                binding.signEmail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                binding.signPassword.setError("password is empty!");
                binding.signPassword.requestFocus();
                return;
            }

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    UserModel userModel = new UserModel(username, email, password);
                    if (task.getResult().getUser() != null) {
                        String id = task.getResult().getUser().getUid();
                        db.getReference().child("Users").child(id).setValue(userModel);
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "Hello " + username, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if(task.getException()!=null)
                    Toast.makeText(getBaseContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            });


        });

    }
}