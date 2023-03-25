package com.example.mygroceryapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mygroceryapp.MainActivity;
import com.example.mygroceryapp.UserModel;
import com.example.mygroceryapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference=db.getReference("Users");
        binding.tvLog.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        });
        binding.signBtn.setOnClickListener(view -> {
            String username = binding.signUserName.getText().toString();
            String email = binding.signEmail.getText().toString();
            String password = binding.signPassword.getText().toString();
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "name is empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "email is empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "password is empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    UserModel userModel=new UserModel(username,email,password);
                    String id=task.getResult().getUser().getUid();
                    db.getReference().child("Users").child(id).setValue(userModel);
                    Intent intent=new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "Hello" + username, Toast.LENGTH_SHORT).show();

                }
            });


    });

}}