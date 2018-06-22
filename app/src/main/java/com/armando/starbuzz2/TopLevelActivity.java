package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;


public class TopLevelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:{
                        intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    }break;
                    case 1:{
                        intent = new Intent(TopLevelActivity.this, FoodCategoryActivity.class);
                    }break;
                    case 2:{
                        intent = new Intent(TopLevelActivity.this, StoreCategoryActivity.class);
                    }break;
                }
                startActivity(intent);
            }
        };
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
}
