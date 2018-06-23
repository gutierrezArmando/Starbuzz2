package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;

/*Para SQLite*/
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class StoreCategoryActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_category);

        ListView listStores = (ListView) findViewById(R.id.list_stores);

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            db = starbuzzDatabaseHelper.getReadableDatabase();
            cursor = db.query(StarbuzzDatabaseHelper.TABLE_STORE,
                    new String[]{"_id", "NAME"},
                    null,null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1, cursor,
                    new String[]{"NAME"},
                    new int[] {android.R.id.text1}, 0);
            listStores.setAdapter(listAdapter);
        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

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

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
