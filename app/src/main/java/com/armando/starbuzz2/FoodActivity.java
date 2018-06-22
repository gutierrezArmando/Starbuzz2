package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends Activity {

    public static final String EXTRA_FOODID = "foodId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int foodId = (Integer) getIntent().getExtras().get(EXTRA_FOODID);
        Food food = Food.foods[foodId];

        TextView textName = (TextView) findViewById(R.id.foodName);
        textName.setText(food.getName());

        TextView textDescriptin = (TextView) findViewById(R.id.foodDescription);
        textDescriptin.setText(food.getDescription());

        ImageView photo = (ImageView) findViewById(R.id.foodPhoto);
        photo.setImageResource(food.getImageResourceId());
        photo.setContentDescription(food.getName());
    }
}
