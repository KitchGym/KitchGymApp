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

public class LoginActivity extends AppCompatActivity {

    final String TAG = "Login";
    Toolbar toolbar;
    ProgressBar progressBar;
    Button login;
    EditText username;
    EditText password;
    User user;

    FirebaseAuth firebaseAuth;
    ValueEventListener listener;
    Query query;
    FirebaseDatabase database;
    DatabaseReference referenceToDatabase;
    DatabaseReference rootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // UI
        toolbar = findViewById(R.id.toolbar2);
        progressBar = findViewById(R.id.progressBar2);
        login = findViewById(R.id.userLoginBtn);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        toolbar.setTitle("Login");

        // Database
        database = FirebaseDatabase.getInstance();
        referenceToDatabase = database.getReference().child("Users");
        rootRef = FirebaseDatabase.getInstance().getReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                checkUsernameAndPassword();

            }
        });

              listener = new ValueEventListener(){
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      if(dataSnapshot.exists()) {
                          //if the query exists, login and save that user
                          System.out.println(user);
                          user = dataSnapshot.child(username.getText().toString().trim()).getValue(User.class);
                          System.out.println("~~~~~~~~~~~~~~");
                          System.out.println(user.getName());

                          Intent intent = new Intent(LoginActivity.this, MainActivity.class );
                          intent.putExtra("User", user);
                          startActivity(intent);
                      } else {
                          Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_LONG).show();
                      }
                  }
                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {
                      // Failed to read value
                      Log.w(TAG, "Failed to read value.", error.toException());
                  }

              };

    }
    private void checkUsernameAndPassword(){
        //checking username and password to see if such a query exists
        query = rootRef.child("Users").orderByChild("usernameAndPassword").equalTo(username.getText().toString().trim()
                + "_" + password.getText().toString().trim());
        query.addListenerForSingleValueEvent(listener);
    }
}
