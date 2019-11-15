package com.example.kitchgym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    Button login;
    EditText email;
    EditText password;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar2);
        progressBar = findViewById(R.id.progressBar2);
        login = findViewById(R.id.userLoginBtn);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        toolbar.setTitle("Login");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString().trim(),
                        password.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Task completed successfully
                                    Toast.makeText(getApplicationContext(), "Login Successful",
                                            Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
                                } else {
                                    // Task failed with an exception
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });;
            }
        });
    }
}
