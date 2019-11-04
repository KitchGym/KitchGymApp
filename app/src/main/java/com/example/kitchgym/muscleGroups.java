package com.example.kitchgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class muscleGroups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_groups);
    }

    public void switchToGenerateWorkout(View view) {

        //WORKOUT GENERATION HERE//

        Intent intent = new Intent(this, generateWorkout.class);
        startActivity(intent);
    }
}
