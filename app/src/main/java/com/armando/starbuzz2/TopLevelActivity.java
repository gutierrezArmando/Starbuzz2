package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;

/*Para la lista de favoritos*/
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import android.widget.CursorAdapter;
import android.widget.Toast;

public class TopLevelActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        setupOptionsListView();
        setupFavoriteListView();
    }

    private void setupFavoriteListView() {
        ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
//            favoritesCursor = db.query(StarbuzzDatabaseHelper.TABLE_DRINK,
//                    new String[]{"_id", "NAME"},
//                    "FAVORITE = 1",
//                    null,null,null,null);
            String query = "select _id, NAME, 'DRINK' as TYPE from DRINK where FAVORITE = 1 union " +
                    "select _id, NAME, 'FOOD' as TYPE from FOOD where FAVORITE = 1 union " +
                    "select _id, NAME, 'STORE' as TYPE from STORE where FAVORITE = 1; ";
            favoritesCursor = db.rawQuery(query,null);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(TopLevelActivity.this,
                    android.R.layout.simple_list_item_1,
                    favoritesCursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},0);
            listFavorites.setAdapter(favoriteAdapter);
        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = null;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                favoritesCursor.moveToPosition(position);

                String type = favoritesCursor.getString(2);
                switch (type)
                {
                    case "DRINK":{
                        intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                        intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int)id);
                    }break;
                    case "FOOD": {
                        intent = new Intent(TopLevelActivity.this, FoodActivity.class);
                        intent.putExtra(FoodActivity.EXTRA_FOODID, (int)id);
                    }break;
                    case "STORE":{
                        intent = new Intent(TopLevelActivity.this, StoreActivity.class);
                        intent.putExtra(StoreActivity.EXTRA_STOREID, (int)id);}break;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

    private void setupOptionsListView(){
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

    @Override
    public void onRestart(){
        super.onRestart();
        String query = "select _id, NAME, 'DRINK' as TYPE from DRINK where FAVORITE = 1 union " +
                "select _id, NAME, 'FOOD' as TYPE from FOOD where FAVORITE = 1 union " +
                "select _id, NAME, 'STORE' as TYPE from STORE where FAVORITE = 1; ";
        Cursor newCursor = db.rawQuery(query,null);
        ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
        CursorAdapter adapter = (CursorAdapter) listFavorites.getAdapter();
        adapter.changeCursor(newCursor);
        favoritesCursor = newCursor;
    }
}
