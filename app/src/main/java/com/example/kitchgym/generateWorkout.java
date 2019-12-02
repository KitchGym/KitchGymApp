// generateWorkout

package com.example.kitchgym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.List;

import com.google.firebase.database.*;


public class generateWorkout extends AppCompatActivity {

    //private List<String> muscles = getIntent().getStringArrayListExtra("muscleGroups");
    public Exercise ex = new Exercise();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference testRef = database.getReference("Exercises/kaedon");
    DatabaseReference absRef = database.getReference("Exercises/Abs");
    DatabaseReference armsRef = database.getReference("Exercises/Arms");
    DatabaseReference backRef = database.getReference("Exercises/Back");
    DatabaseReference chestRef = database.getReference("Exercises/Chest");
    DatabaseReference legsRef = database.getReference("Exercises/Legs");
    DatabaseReference shouldersRef = database.getReference("Exercises/Shoulders");


    public void testRetrieve(){
        absRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String extest = dataSnapshot.getValue(String.class);
                //Log.d("Test", extest);
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    String value = ds.getValue(String.class);
                    Log.d("TAG", key + value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("ERROR", "Did not work lol");
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_workout);
        //final TextView optionsChosen = findViewById(R.id.bicepWorkouts);
        //muscles = getIntent().getStringArrayListExtra("muscleGroups");
        Log.d("ONCREATE", "ONCREATE started");

        //testRetrieve();

        Log.d("ONCREATE", "ONCREATE ended");


    }






    public void switchToDoWorkout(View view) {
        Intent intent = new Intent(this, doWorkout.class);
        startActivity(intent);
    }
}

