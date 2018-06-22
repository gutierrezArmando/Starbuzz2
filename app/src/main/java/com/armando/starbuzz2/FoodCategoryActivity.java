package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;

public class FoodCategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);
        ArrayAdapter<Food> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Food.foods
        );
        ListView listFoods = (ListView) findViewById(R.id.list_foods);
        listFoods.setAdapter(listAdapter);

        //        Para acceder a la siguiente actividad, que muestra los detalles
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listDrinks, View itemView, int position, long id) {
                Intent intent = new Intent(FoodCategoryActivity.this, FoodActivity.class);
                intent.putExtra(FoodActivity.EXTRA_FOODID, (int)id);
                startActivity(intent);
            }
        };

        listFoods.setOnItemClickListener(itemClickListener);
    }
}
