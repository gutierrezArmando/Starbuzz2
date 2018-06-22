package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;

public class StoreCategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_category);
        ArrayAdapter<Store> listAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, Store.stores);
        ListView listStores = (ListView) findViewById(R.id.list_stores);
        listStores.setAdapter(listAdapter);

        //        Para acceder a la siguiente actividad, que muestra los detalles
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listDrinks, View itemView, int position, long id) {
                Intent intent = new Intent(StoreCategoryActivity.this, StoreActivity.class);
                intent.putExtra(StoreActivity.EXTRA_STOREID, (int)id);
                startActivity(intent);
            }
        };

        listStores.setOnItemClickListener(itemClickListener);
    }
}
