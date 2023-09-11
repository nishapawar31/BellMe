package com.appdroid.bellme;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appdroid.bellme.Activities.Holder;
import com.appdroid.bellme.databinding.ActivityEditProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    ActivityEditProfileBinding binding;
    FirebaseFirestore db;
    Holder holder;
    String s="f_name,l_name,n_email";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDataFromIntent();
        setupUpdateButton();
    }
    private void initDataFromIntent() {
             holder = (Holder) getIntent().getSerializableExtra("all");
            binding.firstName.setText(holder.getFirstName());
            binding.lastName.setText(holder.getLastName());
            binding.email.setText(holder.getEmail());
        }
        private void setupUpdateButton() {
            db = FirebaseFirestore.getInstance();
            binding.update.setOnClickListener(view -> {
                Map<String, Object> UsersData = new HashMap<>();
                UsersData.put("firstName",holder.getFirstName());
                UsersData.put("lastName", holder.getLastName());
                UsersData.put("email",holder.getEmail());
                db.collection("UsersData")
                        .document(holder.getDocID())
                        .update(UsersData)
                        .addOnSuccessListener(unused -> showToast("Data updated"))
                        .addOnFailureListener(e -> showToast("OOPS..Try Again for data updation"));
            });
        }
        private void showToast(String message) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }