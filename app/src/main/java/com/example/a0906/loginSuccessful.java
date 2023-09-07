package com.example.a0906;

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

public class loginSuccessful extends AppCompatActivity {

    FirebaseAuth auth;
    Button button, b2;
    TextView textView, textView2;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        b2 = findViewById(R.id.nextbutton);
        textView = findViewById(R.id.user_details);
        textView2 = findViewById(R.id.user_names);
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
                    textView2.setText(username);
                    textView.setText(firebaseUser.getEmail());

                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext() ,page2.class);
                    startActivity(intent);
                    finish();
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}


//123456@123456.com