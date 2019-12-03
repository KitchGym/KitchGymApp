package com.example.kitchgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Kitchen extends AppCompatActivity implements InputFoodFragment.OnInputFoodListener{

    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    TextView goalWeight;
    TextView proteinView;
    TextView carbsView;
    TextView fatView;
    TextView calorieGoalView;
    Button macrosBtn;
    Button inputFoodBtn;
    EditText food;
    EditText time;
    EditText cal;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        goalWeight = findViewById(R.id.goalWeight);
        proteinView = findViewById(R.id.proteinNum);
        carbsView = findViewById(R.id.carbsNum);
        fatView = findViewById(R.id.fatNum);
        calorieGoalView = findViewById(R.id.calorieNum);
        macrosBtn = findViewById(R.id.macrosButton);
        inputFoodBtn = findViewById(R.id.inputFoodButton);
        food = findViewById(R.id.foodEntry);
        time = findViewById(R.id.timeEntry);
        cal = findViewById(R.id.calEntry);


        user = (User)getIntent().getSerializableExtra("User");
        goalWeight.setText(user.getGoalWeight() + " pounds");

        macrosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMacros();
            }
        });

        inputFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInputFood(food.getText().toString().trim(), time.getText().toString().trim(),
                        cal.getText().toString().trim());
            }
        });

        list = (ListView) findViewById(R.id.foodList);
        arrayList = new ArrayList<String>();
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        // Here, you set the data in your ListView
        list.setAdapter(adapter);
    }

    /*public void switchToInputFood() {
        InputFoodFragment fragment = new InputFoodFragment();
        String tag = InputFoodFragment.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment, tag).commit();

    }*/

    public void calculateMacros(){
        float protein, carbs, fat, weight = user.getWeight(), goalWeight = user.getGoalWeight();
        int calories = 0, calorieGoal = (int)(weight * 14); // weight * 14 is maintenance calories
        // One gram of protein per lb of body weight
        protein = weight * 1;
        // 4 calories per gram of protein
        calories += (protein * 4);
        // 0.35 gram of fat per pound of body weight
        fat = weight * 0.35f;
        // 9 calories per gram of protein
        calories += (fat * 9);
        // The rest is carbs
        if (goalWeight < weight)  // diet/cut
            calorieGoal -= 500;
        else if (goalWeight > weight) // lean bulk
            calorieGoal += 400;
        // else just maintain

        // Carbs are whatever calories are left
        if (calorieGoal >= calories)
            carbs = (calorieGoal - calories) / 4;
        else
            carbs = 0;

        proteinView.setText(protein + " g");
        carbsView.setText(carbs + " g");
        fatView.setText(fat + " g");
        calorieGoalView.setText(calorieGoal + " calories");
    }

    public void onInputFood(String food, String time, String calories){
        String item = "";
        item += "Food: " + food + ", Time: " + time + ", Calories: " + calories;
        arrayList.add(item);
        // check if adapter has changed
        adapter.notifyDataSetChanged();
    }

}
