package BusinessLayer;

import DataLayer.User;
import Utils.DisplayAs;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MenuItem implements Serializable {

    private static final long serialVersionUID = -8613347217470826671L;
    protected String title;
    protected double rating;
    protected int calories;
    protected int protein;
    protected int fat;
    protected int sodium;
    protected double price;

    public MenuItem(){}
    public MenuItem(String name, double rating, int calories, int protein, int fat, int sodium, double price) {
        this.title = name;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @DisplayAs(value = "Name ", index = 0)
    public String getName() {
        return computeName();
    }

    @DisplayAs(value = "Price ", index = 1)
    public double getPrice() {
        return computePrice();
    }

    @DisplayAs(value = "Rating ", index = 2)
    public double getRating() {
        return computeRating();
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @DisplayAs(value = "Calories ", index = 3)
    public int getCalories() {
        return computeCalories();
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    @DisplayAs(value = "Protein ", index = 4)
    public int getProtein() {
        return computeProtein();
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    @DisplayAs(value = "Fat", index = 5)
    public int getFat() {
        return computeFat();
    }

    public void setFat(int fat) {
        this.fat = fat;
    }
    @DisplayAs(value = "Sodium", index = 6)
    public int getSodium() {
        return computeSodium();
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setName(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String computeName();
    public abstract double computeRating();
    public abstract int computeCalories();
    public abstract int computeProtein();
    public abstract int computeFat();
    public abstract int computeSodium();
    public abstract double computePrice();


    @Override
    public String toString() {
        return title +" " + price + "\n";
    }






}
