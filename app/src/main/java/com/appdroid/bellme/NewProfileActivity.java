package com.appdroid.bellme;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.appdroid.bellme.Activities.Holder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class NewProfileActivity extends AppCompatActivity {
    Button edit;
    TextInputEditText f_name,l_name,n_email;
    Holder holder;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
        edit = findViewById(R.id.edit);
        f_name = findViewById(R.id.f_name);
        l_name = findViewById(R.id.l_name);
        n_email = findViewById(R.id.n_email);
        getUserData();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewProfileActivity.this, EditProfile.class);
                i.putExtra("all",holder);
                startActivity(i);
                finish();
            }
        });
    }
    private void getUserData() {
        DocumentReference documentReference;
        documentReference = FirebaseFirestore.getInstance().collection("UsersData")
                .document("4YP6Z2eyurf2FIzPj4DIBshZgNC3");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                 holder = documentSnapshot.toObject(Holder.class);
                holder.setDocID(documentSnapshot.getId());
                f_name.setText(holder.getFirstName());
                l_name.setText(holder.getLastName());
                n_email.setText(holder.getEmail());
            }
        });
    }
}