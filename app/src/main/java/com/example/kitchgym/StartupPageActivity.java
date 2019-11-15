package com.example.kitchgym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class StartupPageActivity extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    Button signup;
    Button login;
    EditText name;
    EditText weight;
    EditText goalWeight;
    EditText email;
    EditText password;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);
        signup = findViewById(R.id.signUpBtn);
        login = findViewById(R.id.loginBtn);
        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        goalWeight = findViewById(R.id.goalWeight);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        toolbar.setTitle("KitchGym Login");

        firebaseAuth = FirebaseAuth.getInstance();

        // Register User
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString().trim(),
                        password.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Task completed successfully
                                    AuthResult result = task.getResult();
                                    /*Toast.makeText(getApplicationContext(), "Registered Successfully",
                                            Toast.LENGTH_LONG).show();*/

                                    User user = new User(
                                            name.getText().toString().trim(),
                                            email.getText().toString().trim(),
                                            password.getText().toString().trim(),
                                            Float.parseFloat(weight.getText().toString().trim()),
                                            Float.parseFloat(goalWeight.getText().toString().trim())
                                    );
                                    // Add user to database
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {
                                                Toast.makeText(StartupPageActivity.this,
                                                        "Registration Successful",
                                                        Toast.LENGTH_LONG);
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                                    email.setText("");
                                    password.setText("");
                                    name.setText("");
                                    weight.setText("");
                                    goalWeight.setText("");

                                } else {
                                    // Task failed with an exception
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartupPageActivity.this, LoginActivity.class));
            }
        });

    }
}