package com.example.kitchgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchToGym(View view) {
        Intent intent = new Intent(this, Gym.class);
        startActivity(intent);
    }

    public void switchToKitchen(View view) {
        Intent intent = new Intent(this, Kitchen.class);
        startActivity(intent);
    }
}
