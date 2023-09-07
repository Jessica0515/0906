package com.example.a0906;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Menu extends AppCompatActivity {
    TextView txtNotiMarquee, txtCalenMarquee;
    FirebaseAuth auth;
    TextView menuName;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtNotiMarquee = (TextView) findViewById(R.id.notiMarquee);
        txtNotiMarquee.setSelected(true);

        txtCalenMarquee = (TextView) findViewById(R.id.calenMarquee);
        txtCalenMarquee.setSelected(true);

        //到包裹
        Button buttonPackage = findViewById(R.id.menuPackage);
        buttonPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this ,PackageReceive.class);
                Menu.this.startActivity(intent);
            }
        });

        //到維修
        Button buttonRepair = findViewById(R.id.menuRepair);
        buttonRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this ,Repair.class);
                Menu.this.startActivity(intent);
            }
        });


//        Firebase

        auth = FirebaseAuth.getInstance();
        menuName = findViewById(R.id.user_details);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

//        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (firebaseUser == null) {
            Intent intent = new Intent(getApplicationContext() ,login.class);
            startActivity(intent);
            finish();
        } else {
            String uid = firebaseUser.getUid();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("users").document(uid);

            userRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String username = documentSnapshot.getString("name");
                    menuName.setText(username);

                }
            });

        }
    }}
