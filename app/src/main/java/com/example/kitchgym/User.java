package com.example.kitchgym;

import java.io.Serializable;

public class User implements Serializable {
    private String name, username, password, usernameAndPassword;
    private float weight, goalWeight;

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(float goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getUsernameAndPassword() {
        return usernameAndPassword;
    }

    public void setUsernameAndPassword(String usernameAndPassword) {
        this.usernameAndPassword = usernameAndPassword;
    }
}
