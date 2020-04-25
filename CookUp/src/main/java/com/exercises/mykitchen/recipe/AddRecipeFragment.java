package com.exercises.mykitchen.recipe;


import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.exercises.mykitchen.database.DatabaseAccess;
import com.exercises.mykitchen.R;

import java.io.ByteArrayOutputStream;


public class AddRecipeFragment extends Fragment {

    final int REQUEST_CODE_GALLERY = 999;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = getActivity();
        final View view = inflater.inflate(R.layout.add_recipe, container, false);
        final ImageView img = (ImageView) view.findViewById(R.id.add_recipe_imageView);
        final EditText title = (EditText) view.findViewById(R.id.add_recipe_title_tv);
        final EditText instructions = (EditText) view.findViewById(R.id.add_recipe_instruction_tv);
        Button imgButton = (Button) view.findViewById(R.id.add_recipe_image_button);
        Button newIngredientButton = (Button) view.findViewById(R.id.add_new_ingredient_button1);
        Button addRecipe = (Button) view.findViewById(R.id.add_recipe_to_database_button);

        final DatabaseAccess dbAccess = DatabaseAccess.getInstance(context);


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
                byte[] NewImg = imageViewToByte(img);
            }

            private byte[] imageViewToByte(ImageView img) {
                Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }
        });

        newIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mTitle = title.getText().toString();
                String mDes = instructions.getText().toString();

            }
        });

        return view;
    }

}
