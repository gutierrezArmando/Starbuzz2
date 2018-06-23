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
        updateMyDatabase(db, 0, DB_VERSION);
    }/*Fin del metodo onCreate*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }/*Fin del metodo onUpgrade*/

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, drinkValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if( oldVersion < 1 ) {
            /*Se crea la tabla drink*/
            String query =
                    "create table DRINK ( " +
                            "_id integer primary key autoincrement, " +
                            "NAME text, " +
                            "DESCRIPTION text, " +
                            "IMAGE_RESOURCE_ID integer); ";
            db.execSQL(query);
            /*Se insertan tres filas invocando el metod creado insertDrink*/
            insertDrink(db,"LatteDB","Es cafe latte", R.drawable.bebida_latte);
            insertDrink(db,"CappuccinoDB","Cappuccino muy caliente", R.drawable.bebida_cappuccino);
            insertDrink(db,"FilterDB","Es nuestro mejor cafe", R.drawable.bebida_filter);
            insertDrink(db,"PersonalizadoDB","Un cafe personalizado", R.drawable.bebida_conejo);
        }
        if(oldVersion < 2 ) {
            String query = "alter table drink add column favorite numeric; ";
            db.execSQL(query);
        }
    }
}
