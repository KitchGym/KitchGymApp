package com.example.kitchgym;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class StartupPageActivity extends AppCompatActivity {

    final static String TAG = "Registering...";
    Toolbar toolbar;
    ProgressBar progressBar;
    Button signup;
    Button login;
    EditText name;
    EditText weight;
    EditText goalWeight;
    EditText username;
    EditText password;

    FirebaseAuth firebaseAuth;

    FirebaseDatabase database;
    DatabaseReference referenceToDatabase;

    ValueEventListener listener;

    User user;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        database = FirebaseDatabase.getInstance();
        referenceToDatabase = database.getReference().child("Users");

        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);
        signup = findViewById(R.id.signUpBtn);
        login = findViewById(R.id.loginBtn);
        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        goalWeight = findViewById(R.id.goalWeight);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        user = new User();

        toolbar.setTitle("KitchGym Login");


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //error checking to see if all fields are filled in
                boolean error = false;

                if (name.getText().toString().equals("")) {
                    error = true;
                    name.setError("Enter your name");
                }
                if (username.getText().toString().equals("") || username.getText().toString().contains(".") ||
                        username.getText().toString().contains("#") || username.getText().toString().contains("$") ||
                        username.getText().toString().contains("[") || username.getText().toString().contains("]")){
                    error = true;
                    username.setError("Enter your username");
                }
                if (password.getText().toString().equals("")) {
                    error = true;
                    password.setError("Enter a password");
                }
                if (weight.getText().toString().equals("")) {
                    error = true;
                    weight.setError("Enter a weight");
                }
                if (goalWeight.getText().toString().equals("")) {
                    error = true;
                    goalWeight.setError("Enter a goal weight");
                }


                if (error) {
                    Toast.makeText(getApplicationContext(), "Fill in all of the fields!", Toast.LENGTH_LONG).show();
                } else {

                    user.setName(name.getText().toString().trim());
                    user.setUsername(username.getText().toString().trim());
                    user.setPassword(password.getText().toString().trim());
                    user.setWeight(Float.parseFloat(weight.getText().toString().trim()));
                    user.setGoalWeight(Float.parseFloat(goalWeight.getText().toString().trim()));
                    user.setUsernameAndPassword(username.getText().toString().trim() + "_" + password.getText().toString().trim());

                    //this jumps up to the database listener
                    //query on the data to check for multiple usernames, activates the listener, so rest of code is there
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    Query query = rootRef.child("Users").orderByChild("username").equalTo(user.getUsername());
                    query.addListenerForSingleValueEvent(listener);
                }


            }
        });

        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {

                    DatabaseReference childUser = referenceToDatabase.child(user.getUsername());
                    childUser.setValue(user);
                    Toast.makeText(getApplicationContext(), "Successfully registered!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(StartupPageActivity.this, LoginActivity.class );
                    intent.putExtra("User", user);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Username is taken! Please choose another.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartupPageActivity.this, LoginActivity.class));
            }
        });

    }
}