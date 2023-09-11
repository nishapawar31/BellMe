package com.appdroid.bellme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appdroid.bellme.Activities.Holder;
import com.appdroid.bellme.Holders.UserDataHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PreferencesActivity extends AppCompatActivity {
    Chip male, female, others, age1, age2, age3, single, engaged, married, english, hindi, marathi;
    ChipGroup grp1, grp2, grp3, grp4;
    TextView add, update1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    UserDataHolder holder;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        grp1 = findViewById(R.id.grp1);
        grp2 = findViewById(R.id.grp2);
        grp3 = findViewById(R.id.grp3);
        grp4 = findViewById(R.id.grp4);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        others = findViewById(R.id.others);
        age1 = findViewById(R.id.age1);
        age2 = findViewById(R.id.age2);
        age3 = findViewById(R.id.age3);
        single = findViewById(R.id.single);
        engaged = findViewById(R.id.engaged);
        married = findViewById(R.id.married);
        english = findViewById(R.id.english);
        hindi = findViewById(R.id.hindi);
        marathi = findViewById(R.id.marathi);
        add = findViewById(R.id.add);
        update1 = findViewById(R.id.update1);

        grp1.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.male) {
                Toast.makeText(this, "selected male", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.female) {
                Toast.makeText(this, "selected female", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "selected other", Toast.LENGTH_SHORT).show();
            }
        }));
        grp2.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.age1) {
                Toast.makeText(this, "selected age 15 to 30", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.age2) {
                Toast.makeText(this, "selected 30 to 50", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "selected above 50", Toast.LENGTH_SHORT).show();

            }
        }));
        grp3.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.single) {
                Toast.makeText(this, "selected Single", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.engaged) {
                Toast.makeText(this, "selected Engaged", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "selected Married", Toast.LENGTH_SHORT).show();
            }
        }));
        grp4.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.english) {
                Toast.makeText(this, "selected English", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.hindi) {
                Toast.makeText(this, "selected Hindi", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "selected Marathi", Toast.LENGTH_SHORT).show();
            }
        }));

        holder = new UserDataHolder();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String male1, female1, others1, new_age1, new_age2, new_age3, new_single, new_engaged, new_married, new_english, new_hindi, new_marathi;

                male1 = male.getText().toString();
                female1 = female.getText().toString();
                others1 = others.getText().toString();
                new_age1 = age1.getText().toString();
                new_age2 = age2.getText().toString();
                new_age3 = age3.getText().toString();
                new_single = single.getText().toString();
                new_engaged = engaged.getText().toString();
                new_married = married.getText().toString();
                new_english = english.getText().toString();
                new_hindi = hindi.getText().toString();
                new_marathi = marathi.getText().toString();

                Map<String, Object> Information = new HashMap<>();
                    if (grp1.getCheckedChipId() == R.id.male) {
                        Information.put("gender", male1);
                    } else if (grp1.getCheckedChipId() == R.id.female) {
                        Information.put("gender", female1);
                    } else {
                        Information.put("gender", others1);
                    }

                    if (grp2.getCheckedChipId() == R.id.age1) {
                        Information.put("age", new_age1);
                    } else if (grp1.getCheckedChipId() == R.id.age2) {
                        Information.put("age", new_age2);
                    } else {
                        Information.put("age", new_age3);
                    }

                    if (grp3.getCheckedChipId() == R.id.single) {
                        Information.put("status", new_single);
                    } else if (grp3.getCheckedChipId() == R.id.engaged) {
                        Information.put("status", new_engaged);
                    } else {
                        Information.put("status", new_married);
                    }

                    if (grp4.getCheckedChipId() == R.id.english) {
                        Information.put("language", new_english);
                    } else if (grp4.getCheckedChipId() == R.id.hindi) {
                        Information.put("language", new_hindi);
                    } else {
                        Information.put("language", new_marathi);
                    }

                if(grp1.getCheckedChipId() == -1 || grp2.getCheckedChipId() == -1 || grp3.getCheckedChipId() == -1 || grp4.getCheckedChipId() == -1) {
                    Toast.makeText(PreferencesActivity.this, "Please select options in all groups", Toast.LENGTH_SHORT).show();
                }else {
                    db.collection("Information")
                            .add(Information)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(PreferencesActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PreferencesActivity.this, "OOPS...Try again", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (grp1.getCheckedChipId() == -1 || grp2.getCheckedChipId() == -1 || grp3.getCheckedChipId() == -1 || grp4.getCheckedChipId() == -1) {
                    Toast.makeText(PreferencesActivity.this, "Please select options in all groups", Toast.LENGTH_SHORT).show();
                }else {
                    if (grp1.getCheckedChipId() == R.id.male) {
                        holder.setGender("Male");
                    } else if (grp1.getCheckedChipId() == R.id.female) {
                        holder.setGender("Female");
                    } else {
                        holder.setGender("Others");
                    }
                    if (grp2.getCheckedChipId() == R.id.age1) {
                        holder.setAge("15 to 30");
                    } else if (grp2.getCheckedChipId() == R.id.age2) {
                        holder.setAge("30 to 50");
                    } else {
                        holder.setAge("Above 50");
                    }
                    if (grp3.getCheckedChipId() == R.id.single) {
                        holder.setStatus("Single");
                    } else if (grp3.getCheckedChipId() == R.id.engaged) {
                        holder.setStatus("Engaged");
                    } else {
                        holder.setStatus("Married");
                    }
                    if (grp4.getCheckedChipId() == R.id.english) {
                        holder.setLanguage("English");
                    } else if (grp4.getCheckedChipId() == R.id.hindi) {
                        holder.setLanguage("Hindi");
                    } else {
                        holder.setLanguage("Marathi");
                    }
                    Update();
                }
            }
            public void Update() {
                DocumentReference documentSnapshot = FirebaseFirestore.getInstance().collection("Information").document("5BGB4ne7yLJFoxi88sp5");
                Map<String, Object> Information = new HashMap<>();
                Information.put("age", holder.getAge());
                Information.put("gender", holder.getGender());
                Information.put("language", holder.getLanguage());
                Information.put("status", holder.getStatus());
                documentSnapshot.update(Information).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PreferencesActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PreferencesActivity.this, "OOPS", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}