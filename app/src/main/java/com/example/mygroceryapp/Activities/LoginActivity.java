package com.example.mygroceryapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mygroceryapp.MainActivity;
import com.example.mygroceryapp.UserModel;
import com.example.mygroceryapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


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
            String email = binding.logUsername.getText().toString();
            String password = binding.logPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "email is empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "password is empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent=new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "logged successfully", Toast.LENGTH_SHORT).show();}


                }
            });

        });



    }


}