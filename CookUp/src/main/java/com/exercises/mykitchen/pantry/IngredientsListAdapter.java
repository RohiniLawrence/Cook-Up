package com.exercises.mykitchen.pantry;

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

public class IngredientsListAdapter extends ArrayAdapter<Ingredients> {

    List<Ingredients> ingredients;
    Context context;

    public IngredientsListAdapter(@NonNull Context _context, List<Ingredients> _ingredients) {
        super(_context, R.layout.row, R.id.textView,_ingredients);
        this.context = _context;
        this.ingredients = _ingredients;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        ImageView images = row.findViewById(R.id.img);
        TextView myTitle = row.findViewById(R.id.textView);
        myTitle.setText(ingredients.get(position).getName());
        byte[] byteArray = ingredients.get(position).getImg();
        if(byteArray!=null){
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        images.setImageBitmap(bitmap);}
        return row;
    }

}