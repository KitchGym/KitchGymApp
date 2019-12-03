// muscleGroups.java

package com.example.kitchgym;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;


public class muscleGroups extends AppCompatActivity {

    private CheckBox armsBox;
    private CheckBox chestBox;
    private CheckBox shoulderBox;
    private CheckBox backBox;
    private CheckBox legsBox;
    private CheckBox absBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_groups);

        armsBox = findViewById(R.id.arms);
        chestBox = findViewById(R.id.chest);
        shoulderBox = findViewById(R.id.shoulders);
        backBox = findViewById(R.id.back);
        legsBox = findViewById(R.id.legs);
        absBox = findViewById(R.id.abs);

        Log.d("ONSTART", "Entered onStart()");

    }



    public void switchToGenerateWorkout(View view) {

        //WORKOUT GENERATION HERE//
        List<String> muscleGroups = new ArrayList<String>();

        if(armsBox.isChecked())
            muscleGroups.add("bicep");
        if(chestBox.isChecked())
            muscleGroups.add("chest");
        if(shoulderBox.isChecked())
            muscleGroups.add("shoulder");
        if(backBox.isChecked())
            muscleGroups.add("back");
        if(legsBox.isChecked())
            muscleGroups.add("legs");
        if(absBox.isChecked())
            muscleGroups.add("abs");


        Intent intent = new Intent(this, generateWorkout.class);
        intent.putStringArrayListExtra("muscleGroups", (ArrayList<String>) muscleGroups);
        startActivity(intent);
    }
}

