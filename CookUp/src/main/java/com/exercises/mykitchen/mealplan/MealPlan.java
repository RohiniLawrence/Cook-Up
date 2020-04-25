package com.exercises.mykitchen.mealplan;

import com.exercises.mykitchen.recipe.Recipe;

public class MealPlan {

    int ID;
    Recipe Breakfast;
    Recipe Lunch;
    Recipe Dinner;

    public Recipe getLunch() {
        return Lunch;
    }

    public void setLunch(Recipe lunch) {
        Lunch = lunch;
    }

    public Recipe getDinner() {
        return Dinner;
    }

    public void setDinner(Recipe dinner) {
        Dinner = dinner;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Recipe getBreakfast() {
        return Breakfast;
    }

    public void setBreakfast(Recipe breakfast) {
        Breakfast = breakfast;
    }
}
