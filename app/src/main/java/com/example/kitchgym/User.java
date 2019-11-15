package com.example.kitchgym;

public class User {
    public String name, email, password;
    public float weight, goalWeight;

    public User(){

    }

    public User(String name, String email, String password, float weight, float goalWeight) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.goalWeight = goalWeight;
    }

}
