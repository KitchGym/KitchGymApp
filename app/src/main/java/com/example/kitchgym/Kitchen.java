package com.example.kitchgym;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Kitchen extends AppCompatActivity {

    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private ArrayList<Food> foodList;
    //SimpleAdapter adapter;
    private Map<String, String> pair;
    TextView goalWeight;
    TextView proteinView;
    TextView carbsView;
    TextView fatView;
    TextView calorieGoalView;
    Button macrosBtn;
    Button inputFoodBtn;
    Button completeEntry;
    Button reset;
    EditText food;
    EditText time;
    EditText cal;
    EditText foodProtein;
    EditText foodCarbs;
    EditText foodFat;
    int id;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        id = 0;
        goalWeight = findViewById(R.id.goalWeight);
        proteinView = findViewById(R.id.proteinNum);
        carbsView = findViewById(R.id.carbsNum);
        fatView = findViewById(R.id.fatNum);
        calorieGoalView = findViewById(R.id.calorieNum);
        macrosBtn = findViewById(R.id.macrosButton);
        inputFoodBtn = findViewById(R.id.inputFoodButton);
        completeEntry = findViewById(R.id.completeEntry);
        reset = findViewById(R.id.reset);
        food = findViewById(R.id.foodEntry);
        time = findViewById(R.id.timeEntry);
        cal = findViewById(R.id.calEntry);
        foodProtein = findViewById(R.id.foodProteinEntry);
        foodCarbs = findViewById(R.id.foodCarbsEntry);
        foodFat = findViewById(R.id.foodFatEntry);
        foodList = new ArrayList<>();

        user = (User)getIntent().getSerializableExtra("User");
        goalWeight.setText(user.getGoalWeight() + " pounds");

        list = (ListView) findViewById(R.id.foodList);
        //pair = new HashMap<String, String>(2);
        arrayList = new ArrayList<String>();
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

                /*new SimpleAdapter(this, arrayList,
                        android.R.layout.simple_list_item_2,
                        new String[] {"First Line", "Second Line" },
                        new int[] {android.R.id.text1, android.R.id.text2 }); */

        // Set the data in your ListView
        list.setAdapter(adapter);

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
                        Integer.parseInt(cal.getText().toString().trim()), Integer.parseInt(foodProtein.getText().toString().trim()),
                        Integer.parseInt(foodCarbs.getText().toString().trim()), Integer.parseInt(foodFat.getText().toString().trim()));
            }
        });

        completeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add foods to database for user

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                // check if adapter has changed
                adapter.notifyDataSetChanged();
            }
        });

        // Delete food item on click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                new AlertDialog.Builder( Kitchen.this )
                        .setTitle( "Nutrition Facts" )
                        .setMessage( foodList.get(position).toString() )
                        .setPositiveButton( "Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "AlertDialog", "Positive" );
                            }
                        })
                        .setNegativeButton( "Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "AlertDialog", "Negative" );
                                arrayList.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        } )
                        .show();

                /*arrayList.remove(position);

                adapter.notifyDataSetChanged();*/
            }
        });

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

    public void onInputFood(String food, String time, int calories, int protein, int carbs, int fat){

        Food foodItem = new Food();
        foodItem.setName(food);
        foodItem.setTime(time);
        foodItem.setCalories(calories);
        foodItem.setProtein(protein);
        foodItem.setCarbs(carbs);
        foodItem.setFat(fat);
        foodItem.setDate(new SimpleDateFormat("dd-MM-yyyy",
                Locale.getDefault()).format(new Date()));

        foodList.add(foodItem);

/*
        pair.clear();
        pair.put("First Line", "Food: " + food + "\tTime: " + time + "\tCalories: " + calories);
        pair.put("Second Line","Protein(g): " + protein + ", Carbs: " + carbs + "\tFat(g): " +
                fat + "\tCarbs(g): " + carbs);
*/
        String item = "";
        item += "Food: " + food + ", Time: " + time + ", Calories: " + calories;
        //item += foodItem.toString();
        arrayList.add(item);

        /*adapter = new SimpleAdapter(this, arrayList,
                android.R.layout.simple_list_item_2,
                new String[] {Integer.toString(id-1), Integer.toString(id) },
                new int[] {android.R.id.text1, android.R.id.text2 });*/
        // check if adapter has changed
        adapter.notifyDataSetChanged();
        //list.setAdapter(adapter);
        //id++;
    }

}
