package com.example.kitchgym;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Exercise {
    private String one, two, three;

    public Exercise(){

    }

    public Exercise(String one, String two, String three){
        this.one = one;
        this.two = two;
        this.three = three;
    }


    public String getOne(){
        return one;
    }
    public String getTwo(){
        return two;
    }
    public String getThree(){
        return three;
    }
}
