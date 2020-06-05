package com.example.vita_1.item;

public class FoodData {
    int foodID;
    String foodName;
    String cat;

    public FoodData(int foodID, String foodName) {
        this.foodID = foodID;
        this.foodName = foodName;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
