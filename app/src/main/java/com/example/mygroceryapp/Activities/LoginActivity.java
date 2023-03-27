package com.example.mygroceryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mygroceryapp.MainActivity;
import com.example.mygroceryapp.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        binding.tvSign.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
            startActivity(intent);
        });
        binding.logBtn.setOnClickListener(view -> {
            String email = binding.logEmail.getText().toString();
            String password = binding.logPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                binding.logEmail.setError("email is empty!");
                binding.logEmail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                binding.logPassword.setError("password is empty!");
                binding.logPassword.requestFocus();
                return;
            }

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Intent intent=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "logged successfully", Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(getBaseContext(), "Email or password is not correct!", Toast.LENGTH_SHORT).show();
                }


            });

        });



    }


}