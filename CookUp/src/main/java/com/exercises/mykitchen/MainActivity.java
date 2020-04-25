package com.exercises.mykitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.exercises.mykitchen.mealplan.MealPlanFragment;
import com.exercises.mykitchen.pantry.PantryFragment;
import com.exercises.mykitchen.recipe.RecipeFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager){
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecipeFragment(), "Recipes");
        adapter.addFragment(new PantryFragment(), "Pantry");
        adapter.addFragment(new MealPlanFragment(), "Meal Plan");
        viewPager.setAdapter(adapter);
    }

}
