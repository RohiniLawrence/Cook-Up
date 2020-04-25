package com.exercises.mykitchen.mealplan;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.exercises.mykitchen.R;
import com.exercises.mykitchen.database.DatabaseAccess;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MealPlanFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.meal_plan, container , false);
        final LinearLayout breakfastRow = (LinearLayout) view.findViewById(R.id.breakfast_row);
        final LinearLayout lunchRow= (LinearLayout) view.findViewById(R.id.lunch_row);
        final LinearLayout dinnerRow= (LinearLayout) view.findViewById(R.id.dinner_row);

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            Context context = getActivity();
            DatabaseAccess dbAccess = DatabaseAccess.getInstance(context);

            //getting list of mealplans from the database-------------
            List<MealPlan> allMealPlans = dbAccess.getAllMealPlans();

            TextView breakfastTv = (TextView) view.findViewById(R.id.breakfast_tv);
            TextView lunchTv = (TextView) view.findViewById(R.id.lunch_tv);
            TextView dinnerTv = (TextView) view.findViewById(R.id.dinner_tv);
            TextView detailTv = (TextView) view.findViewById(R.id._mealplan_detail_tv);

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dateString = String.valueOf(year) +String.valueOf(month+1)+String.valueOf(dayOfMonth);
                int date = Integer.parseInt(dateString);

                Log.i(TAG, "date=: " +date);



                for(int i = 0 ; i < allMealPlans.size() ; i++){
                    int findDate = allMealPlans.get(i).getID();
                    Log.i(TAG, "find date=: " +findDate);
                    if(date == findDate){

                        detailTv.setVisibility(View.GONE);
                        breakfastTv.setVisibility(View.VISIBLE);
                        lunchTv.setVisibility(View.VISIBLE);
                        dinnerTv.setVisibility(View.VISIBLE);


                        breakfastRow.setVisibility(View.VISIBLE);
                        lunchRow.setVisibility(View.VISIBLE);
                        dinnerRow.setVisibility(View.VISIBLE);

                        ImageView breakfastImages = (ImageView)breakfastRow.findViewById(R.id.img);
                        ImageView lunchImages = (ImageView)lunchRow.findViewById(R.id.img);
                        ImageView dinnerImages = (ImageView)dinnerRow.findViewById(R.id.img);

                        TextView breakfastTitle = (TextView)breakfastRow.findViewById(R.id.textView);
                        TextView lunchTitle = (TextView)lunchRow.findViewById(R.id.textView);
                        TextView dinnerTitle = (TextView)dinnerRow.findViewById(R.id.textView);

                        breakfastTitle.setText(allMealPlans.get(i).getBreakfast().getName());
                        lunchTitle.setText(allMealPlans.get(i).getLunch().getName());
                        dinnerTitle.setText(allMealPlans.get(i).getDinner().getName());


                        Log.i(TAG, "title: "+allMealPlans.get(i).getBreakfast().getName());

                        byte[] byteArray = allMealPlans.get(i).getBreakfast().getImage();
                        if(byteArray!=null){
                            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                            breakfastImages.setImageBitmap(bitmap);}

                        byte[] byteArray1 = allMealPlans.get(i).getLunch().getImage();
                        if(byteArray!=null){
                            Bitmap bitmap1 = BitmapFactory.decodeByteArray(byteArray1, 0, byteArray1.length);
                            lunchImages.setImageBitmap(bitmap1);}

                        byte[] byteArray2 = allMealPlans.get(i).getDinner().getImage();
                        if(byteArray!=null){
                            Bitmap bitmap2 = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);
                            dinnerImages.setImageBitmap(bitmap2);}

                        Log.i(TAG, "listviews added");

                    }
                    else{
                        detailTv.setVisibility(View.VISIBLE);
                        detailTv.setText("No meals planned for this date!\n (/*only for 27-feb*/)");
                        breakfastTv.setVisibility(View.GONE);
                        breakfastRow.setVisibility(View.GONE);
                        lunchTv.setVisibility(View.GONE);
                        lunchRow.setVisibility(View.GONE);
                        dinnerTv.setVisibility(View.GONE);
                        dinnerRow.setVisibility(View.GONE);

                    }

                }



            }
        });



        return view;
    }





}
