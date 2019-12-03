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

    private List<String> workout = new ArrayList<>();
    private List<String> muscles;
    private DatabaseReference mExerciseReference, absRef, armsRef, backRef, chestRef, legsRef, shouldersRef;
    private String exerciseName;


    private TextView eTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("1");
        setContentView(R.layout.activity_generate_workout);
        System.out.println("2");
        muscles = getIntent().getStringArrayListExtra("muscleGroups");
        System.out.println("3");
        eTitle = findViewById(R.id.todaysWorkoutTItle);
        System.out.println("4");
        mExerciseReference = FirebaseDatabase.getInstance().getReference("Exercises");
        System.out.println("5");
        absRef = mExerciseReference.child("Abs");
        armsRef = mExerciseReference.child("Arms");
        backRef = mExerciseReference.child("Back");
        chestRef = mExerciseReference.child("Chest");
        legsRef = mExerciseReference.child("Legs");
        shouldersRef = mExerciseReference.child("Shoulders");

        System.out.println("Entering createWorkout()");
    }


    @Override
    public void onStart(){
        super.onStart();
        System.out.println("Entering createWorkout()");
        System.out.println("Entering loop");
        for (String temp : muscles) {
            System.out.println("Testing for: " + temp);
            if (temp.equals("abs")) {
                System.out.println("Entered Abs");
                absRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                            exerciseName = childSnapshot.getValue(String.class);
                            workout.add(exerciseName);
                            System.out.println("Added ~" + exerciseName + "~ to the list");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TAG", "loadExercise:onCancelled", databaseError.toException());
                    }
                });
            }
            else if (temp.equals("arms")) {
                System.out.println("Entered Arms");
                armsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            //System.out.println(childSnapshot.getValue(E.class));
                            exerciseName = childSnapshot.getValue(String.class);
                            workout.add(exerciseName);
                            System.out.println("Added ~" + exerciseName + "~ to the list");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TAG", "loadExercise:onCancelled", databaseError.toException());
                    }
                });
            }
            else if (temp.equals("back")) {
                System.out.println("Entered Back");
                backRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            //System.out.println(childSnapshot.getValue(E.class));
                            exerciseName = childSnapshot.getValue(String.class);
                            workout.add(exerciseName);
                            System.out.println("Added ~" + exerciseName + "~ to the list");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TAG", "loadExercise:onCancelled", databaseError.toException());
                    }
                });
            }
            else if (temp.equals("chest")) {
                System.out.println("Entered Chest");
                chestRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            //System.out.println(childSnapshot.getValue(E.class));
                            exerciseName = childSnapshot.getValue(String.class);
                            workout.add(exerciseName);
                            System.out.println("Added ~" + exerciseName + "~ to the list");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TAG", "loadExercise:onCancelled", databaseError.toException());
                    }
                });
            }
            else if (temp.equals("legs")) {
                System.out.println("Entered Legs");
                legsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            //System.out.println(childSnapshot.getValue(E.class));
                            exerciseName = childSnapshot.getValue(String.class);
                            workout.add(exerciseName);
                            System.out.println("Added ~" + exerciseName + "~ to the list");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TAG", "loadExercise:onCancelled", databaseError.toException());
                    }
                });
            }
            else if (temp.equals("shoulders")) {
                System.out.println("Entered Shoulders");
                shouldersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            //System.out.println(childSnapshot.getValue(E.class));
                            exerciseName = childSnapshot.getValue(String.class);
                            workout.add(exerciseName);
                            System.out.println("Added ~" + exerciseName + "~ to the list");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TAG", "loadExercise:onCancelled", databaseError.toException());
                    }
                });
            }
        }
    }


    /*@Override
    public void onStart(){
        super.onStart();
        Log.d("ONSTART", "Entered onStart()");
        absRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Test");
                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    //System.out.println(childSnapshot.getValue(E.class));
                    exerciseName = childSnapshot.getValue(String.class);
                    //System.out.println(ex.getOne());
                    //System.out.println(ex.getTwo());
                    //System.out.println(ex.getThree());
                    System.out.println(exerciseName);
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

