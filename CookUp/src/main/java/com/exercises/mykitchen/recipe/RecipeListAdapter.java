package com.exercises.mykitchen.recipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.exercises.mykitchen.R;

import java.util.List;

public class RecipeListAdapter extends ArrayAdapter<Recipe> {

    List<Recipe> recipes;
    Context context;

    public RecipeListAdapter(@NonNull Context _context, List<Recipe> _recipes) {
        super(_context, R.layout.row, R.id.textView, _recipes);
        this.context = _context;
        this.recipes = _recipes;//?? why do we need resource--check later

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        ImageView images = row.findViewById(R.id.img);
        TextView myTitle = row.findViewById(R.id.textView);
        myTitle.setText(recipes.get(position).getName());
        byte[] byteArray = recipes.get(position).getImage();

        if(byteArray!=null){
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        images.setImageBitmap(bitmap);}
        return row;
    }

}
