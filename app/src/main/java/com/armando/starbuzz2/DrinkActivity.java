package com.armando.starbuzz2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Aqui");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);

        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME","DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkId)},null,null, null);
            if(cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                TextView textName = (TextView) findViewById(R.id.drinkName);
                textName.setText(nameText);

                TextView textDescriptin = (TextView) findViewById(R.id.drinkDescription);
                textDescriptin.setText(descriptionText);

                ImageView photo = (ImageView) findViewById(R.id.drinkPhoto);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
