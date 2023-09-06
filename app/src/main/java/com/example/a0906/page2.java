package com.example.a0906;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class page2 extends AppCompatActivity {

    FirebaseAuth auth;
    Button button,b2;
    TextView textView;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        auth =FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        b2 = findViewById(R.id.nextbutton);
        textView= findViewById(R.id.user_details);
        firebaseUser = auth.getCurrentUser();
        if(firebaseUser == null){
            Intent intent = new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(firebaseUser.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();

                //應該是因為這條所以才會一直登出!!!!!
                Intent intent = new Intent(getApplicationContext(),page2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}