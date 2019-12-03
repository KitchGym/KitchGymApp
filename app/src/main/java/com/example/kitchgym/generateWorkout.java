// generateWorkout

package com.example.kitchgym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class generateWorkout extends AppCompatActivity {

    //private List<String> muscles = getIntent().getStringArrayListExtra("muscleGroups");
    private DatabaseReference mExerciseReference, absRef, armsRef, backRef, chestRef, legsRef, shouldersRef;
    private ValueEventListener mExerciseListener;
    private String exerciseName;
    Exercise ex;

    private TextView eTitle;
    private RecyclerView mExerciseRecycler;
    ArrayList<HashMap<String,String>> allExerciseList;
    String selectedExerciseName, selectedExerciseDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_workout);
        //final TextView optionsChosen = findViewById(R.id.bicepWorkouts);
        //muscles = getIntent().getStringArrayListExtra("muscleGroups");
        Log.d("ONCREATE", "Entered onCreate()");


        eTitle = findViewById(R.id.todaysWorkoutTItle);
        mExerciseReference = FirebaseDatabase.getInstance().getReference("Exercises");
        absRef = mExerciseReference.child("Abs");
        armsRef = mExerciseReference.child("Arms");
        backRef = mExerciseReference.child("Back");
        chestRef = mExerciseReference.child("Chest");
        legsRef = mExerciseReference.child("Legs");
        shouldersRef = mExerciseReference.child("Shoulders");


        mExerciseRecycler = findViewById(R.id.recyclerExercise);

        mExerciseRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    public void onStart(){
        super.onStart();
        Log.d("ONSTART", "Entered onStart()");
        mExerciseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Test");
                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    //System.out.println(childSnapshot.getValue(E.class));
                    ex = childSnapshot.getValue(Exercise.class);
                    System.out.println(ex.getOne());
                    System.out.println(ex.getTwo());
                    System.out.println(ex.getThree());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "loadExercise:onCancelled", databaseError.toException());
            }
        });

    }

    /*@Override
    public void onStart(){
        super.onStart();
        Log.d("ONSTART", "Entered onStart()");
        mExerciseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Test");
                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    //System.out.println(childSnapshot.getValue(E.class));
                    ex = childSnapshot.getValue(Exercise.class);
                    System.out.println(ex.getOne());
                    System.out.println(ex.getTwo());
                    System.out.println(ex.getThree());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "loadExercise:onCancelled", databaseError.toException());
            }
        });

    }*/


    public void switchToDoWorkout(View view) {
        Intent intent = new Intent(this, doWorkout.class);
        startActivity(intent);
    }
}

