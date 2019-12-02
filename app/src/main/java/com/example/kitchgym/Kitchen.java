package com.example.kitchgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Kitchen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);


    }

    public void switchToInputFood(View view) {
        Intent intent = new Intent(this, inputFood.class);
        startActivity(intent);
    }

}
