package com.exercises.mykitchen.pantry;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exercises.mykitchen.R;
import com.exercises.mykitchen.database.DatabaseAccess;

import java.util.List;


public class PantryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pantry, container, false);

        Context context = getActivity();
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(context);


        //getting list of ingredients from the database-------------
        List<Ingredients> allIngredients = dbAccess.getAllIngredients();

        ListView listView = (ListView) view.findViewById(R.id.ingredients_listview);
        IngredientsListAdapter adapter = new IngredientsListAdapter(context, allIngredients);
        listView.setAdapter(adapter);

        return view;
    }
}




