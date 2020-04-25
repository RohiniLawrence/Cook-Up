package com.exercises.mykitchen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.exercises.mykitchen.pantry.Ingredients;
import com.exercises.mykitchen.mealplan.MealPlan;
import com.exercises.mykitchen.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instances;
    Cursor c = null;
    private int newRecipeID;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instances == null) {
            instances = new DatabaseAccess(context);
        }
        return instances;
    }



    public void close() {
        if (db != null) {
            this.db.close();
        }
    }



    //------------getting recipes-----------------------
    public ArrayList<Recipe> getAllRecipes() {


        ArrayList<Recipe> all_recipes = new ArrayList<>();

        this.db = openHelper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from recipe", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            Recipe myRecipe = new Recipe();
            myRecipe.setName(res.getString(res.getColumnIndex("recipe_name")));
            myRecipe.setDescription(res.getString(res.getColumnIndex("recipe_des")));
            myRecipe.setId(res.getInt(res.getColumnIndex("recipe_id")));
            Log.i(TAG, "bit"+res.getBlob(res.getColumnIndex("recipe_img")) );

            myRecipe.setImage(res.getBlob(res.getColumnIndex("recipe_img")));
            all_recipes.add(myRecipe);
            res.moveToNext();
        }
        if(all_recipes.size()!= 0)
        newRecipeID = all_recipes.get((all_recipes.size()-1)).getId();

        return all_recipes;
    }

//-----------adding a recipe-------------

    public boolean addRecipe(String _name, String _des, byte[] _img, List<Integer> ingredientList) {

        this.db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("recipe_name", _name);
        cv.put("recipe_des", _des);

        cv.put("recipe_img", _img);
        db.insert("recipe", null, cv);

        addRecipeIngredients(ingredientList);
        return true;
    }

    //----adding recipe-ingredients "called inside addRecipe()"

    public boolean addRecipeIngredients(List<Integer> ingredientList) {

        this.db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for (int i = 0; i < ingredientList.size(); i++) {
            cv.put("recipe_id", newRecipeID);
            cv.put("ingredient_id", ingredientList.get(i));
            db.insert("recipe_ingredient", null, cv);
        }
        return true;
    }

    //getting ingredients
    public ArrayList<Ingredients> getAllIngredients() {

        ArrayList<Ingredients> all_ingredients = new ArrayList<>();

        this.db = openHelper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from ingredient", null);
        res.moveToFirst();


        while (res.isAfterLast() == false) {

            Ingredients ingredients = new Ingredients();
            ingredients.setName(res.getString(res.getColumnIndex("ingredient_name")));
            ingredients.setImg(res.getBlob(res.getColumnIndex("ingredient_img")));
            ingredients.setID(res.getInt(res.getColumnIndex("ingredient_id")));
            all_ingredients.add(ingredients);
            res.moveToNext();
        }
        return all_ingredients;
    }

    //getting mealplans>>>>>>>>>>>>>>>>>>>>>>>>


    public ArrayList<MealPlan> getAllMealPlans() {

        ArrayList<MealPlan> allMealPlans = new ArrayList<>();

        this.db = openHelper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from meal_plan", null);
        res.moveToFirst();


        while (res.isAfterLast() == false) {

            MealPlan mealPlan = new MealPlan();
            mealPlan.setID(res.getInt(res.getColumnIndex("meal_plan_id")));
            mealPlan.setBreakfast(getRecipe(res.getColumnIndex("breakfast_recipe")));
            mealPlan.setLunch(getRecipe(res.getColumnIndex("lunch_recipe")));
            mealPlan.setDinner(getRecipe(res.getColumnIndex("dinner_recipe")));
            allMealPlans.add(mealPlan);
            res.moveToNext();
        }
        return allMealPlans;
    }


    public Recipe getRecipe(int id) {

        Recipe recipe = new Recipe();



        Cursor res = db.rawQuery("SELECT * FROM recipe WHERE  recipe_id = ?", new String[] {String.valueOf(id)});
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            recipe.setName(res.getString(res.getColumnIndex("recipe_name")));
            recipe.setDescription(res.getString(res.getColumnIndex("recipe_des")));
            recipe.setId(res.getInt(res.getColumnIndex("recipe_id")));
            recipe.setImage(res.getBlob(res.getColumnIndex("recipe_img")));
            res.moveToNext();


        }
        Log.i(TAG, "RECIPE FROM DATABASE: "+recipe.getName());
        return recipe;
    }
}
