package com.example.kitchgym;


import androidx.appcompat.app.AppCompatActivity;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class doWorkout extends AppCompatActivity {

    //partially sourced from: https://www.zoftino.com/android-chronometer-timer-stopwatch-tutorial
    private Chronometer chronometer;
    private boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_workout);

        chronometer = findViewById(R.id.chronometer);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometerChanged) {
                chronometer = chronometerChanged;
            }
        });
    }

    public void switchToGymHome(View view) {
        Intent intent = new Intent(this, Gym.class);
        startActivity(intent);
    }

    public void startStopChronometer(View view){
        if(isStart){
            chronometer.stop();
            isStart = false;
            ((Button)view).setText(getString(R.string.startStopWatch));
        }else{
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            isStart = true;
            ((Button)view).setText(getString(R.string.stopStopWatch));
        }
    }
}
