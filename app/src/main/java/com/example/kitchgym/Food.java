package com.example.kitchgym;

import androidx.annotation.NonNull;

import java.util.Date;

public class Food {

    private String name, time;
    private int calories, protein, carbs, fat;
    private String date;

    public Food(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return "Food: " + name + ", Time: " + time + ", Calories: " + calories + "\nProtein(g): " +
                protein + ", Carbs: " + carbs + "\tFat(g): " + fat + "\tCarbs(g): " + carbs;
    }
}
