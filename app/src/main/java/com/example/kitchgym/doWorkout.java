package com.example.kitchgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class doWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_workout);
    }

    public void switchToGymHome(View view) {
        Intent intent = new Intent(this, Gym.class);
        startActivity(intent);
    }
}
