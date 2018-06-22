package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);
        Drink drink = Drink.drinks[drinkId];

        TextView textName = (TextView) findViewById(R.id.drinkName);
        textName.setText(drink.getName());

        TextView textDescriptin = (TextView) findViewById(R.id.drinkDescription);
        textDescriptin.setText(drink.getDescription());

        ImageView photo = (ImageView) findViewById(R.id.drinkPhoto);
        photo.setImageResource(drink.getImageResourceId());
        photo.setContentDescription(drink.getName());
    }
}
