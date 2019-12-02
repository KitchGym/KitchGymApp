package com.example.kitchgym;


public class Exercise {
    public String name, description;

    public Exercise(){
        this.name = "Test";
        this.description = "TestTest";
    }

    public Exercise(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
}
