package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*Para SQLite*/
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
/*Para el check*/
import android.widget.CheckBox;
import android.content.ContentValues;
public class FoodActivity extends Activity {

    public static final String EXTRA_FOODID = "foodId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int foodId = (Integer) getIntent().getExtras().get(EXTRA_FOODID);
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);

        try{
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query(StarbuzzDatabaseHelper.TABLE_FOOD,
                    new String[]{"NAME","DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(foodId)},null,null, null);
            if(cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3)==1);

                TextView textName = (TextView) findViewById(R.id.foodName);
                textName.setText(nameText);

                TextView textDescriptin = (TextView) findViewById(R.id.foodDescription);
                textDescriptin.setText(descriptionText);

                ImageView photo = (ImageView) findViewById(R.id.foodPhoto);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                CheckBox favorite = (CheckBox) findViewById(R.id.checkFoodFavorite);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavoriteClicked(View view) {
        int foodId = (Integer) getIntent().getExtras().get(EXTRA_FOODID);

        CheckBox favorite = (CheckBox) findViewById(R.id.checkFoodFavorite);
        ContentValues foodValues = new ContentValues();
        foodValues.put("FAVORITE", favorite.isChecked());

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
            db.update(StarbuzzDatabaseHelper.TABLE_FOOD,foodValues,"_id = ?",new String[]{Integer.toString(foodId)});
            db.close();
        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
