package com.example.kitchgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Gym extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);
    }

    public void switchToMuscleGroups(View view) {
        Intent intent = new Intent(this, muscleGroups.class);
        startActivity(intent);
    }

    /*public void switchToMuscleGroups(View view) {
        Intent intent = new Intent(this, muscleGroups.class);
        startActivity(intent);
    }*/
}
