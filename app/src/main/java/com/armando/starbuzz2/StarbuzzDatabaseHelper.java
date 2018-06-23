package com.armando.starbuzz2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;

    StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }/*Fin del constructor*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Se crea la tabla drink*/
            String query = "create table drink (_id integer primary key autoincrement, " +
                "name text, " +
                "description text, " +
                "image_resource_id integer); ";
        db.execSQL(query);
        /*Se insertan tres filas invocando el metod creado insertDrink*/
        insertDrink(db,"Latte","Es cafe latte", R.drawable.bebida_latte);
        insertDrink(db,"Cappuccino","Cappuccino muy caliente", R.drawable.bebida_latte);
        insertDrink(db,"Filter","Es nuestro mejor cafe", R.drawable.bebida_latte);
    }/*Fin del metodo onCreate*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }/*Fin del metodo onUpgrade*/

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("name", name);
        drinkValues.put("description", description);
        drinkValues.put("image_resourse_id", resourceId);
        db.insert("drink", null, drinkValues);
    }

}
