package com.exercises.mykitchen.recipe;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.exercises.mykitchen.database.DatabaseAccess;
import com.exercises.mykitchen.R;

import java.util.List;

public class RecipeFragment extends Fragment{
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     final Context context = getActivity();
     View view = inflater.inflate(R.layout.recipie_home, container, false);


     DatabaseAccess dbAccess = DatabaseAccess.getInstance(context);

     final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.add_recipe_frame);
     TextView tv = (TextView) view.findViewById(R.id.no_recipe_textview);
     Button addRecipeButton = (Button) view.findViewById(R.id.add_recipe_button);

     //getting list of recipes from the database-------------
     List<Recipe> allRecipies = dbAccess.getAllRecipes();
     if (allRecipies.size()== 0) {
         tv.setText("No Recipes");
         tv.setVisibility(View.VISIBLE);
     } else {
         ListView listView = (ListView) view.findViewById(R.id.recipe_listView);
         RecipeListAdapter adapter = new RecipeListAdapter(this.getContext(),allRecipies);
         listView.setAdapter(adapter);
     }

     addRecipeButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            FragmentManager manager = getFragmentManager();

             frameLayout.setVisibility(View.VISIBLE);
            manager.beginTransaction().replace(R.id.add_recipe_frame, new AddRecipeFragment()).commit();
         }
     });


     return view;
 }



}
