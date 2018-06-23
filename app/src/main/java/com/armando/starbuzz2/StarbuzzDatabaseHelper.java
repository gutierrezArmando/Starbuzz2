package com.armando.starbuzz2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;
    public static final String TABLE_DRINK = "DRINK";
    public static final String TABLE_FOOD = "FOOD";
    public static final String TABLE_STORE = "STORE";

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

    private static void insertData(SQLiteDatabase db, String nameTable, String name, String description, int resourceId) {
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("DESCRIPTION", description);
        values.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert(nameTable, null, values);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if( oldVersion < 1 ) {
            createDBDrink(db);
            createDBFood(db);
            createDBStore(db);
        }
        if(oldVersion < 2 ) {
            db.execSQL("alter table DRINK add column FAVORITE numeric; ");
            db.execSQL("alter table FOOD add column FAVORITE numeric; ");
            db.execSQL("alter table STORE add column FAVORITE numeric; ");
        }
    }

    private void createDBDrink(SQLiteDatabase db){
        /*Se crea la tabla drink*/
        String query =
                "create table DRINK ( " +
                        "_id integer primary key autoincrement, " +
                        "NAME text, " +
                        "DESCRIPTION text, " +
                        "IMAGE_RESOURCE_ID integer); ";
        db.execSQL(query);
        /*Se insertan cuatro filas invocando el metod creado insertDrink*/
        insertData(db, TABLE_DRINK,"LatteDB","Es cafe latte", R.drawable.bebida_latte);
        insertData(db, TABLE_DRINK,"CappuccinoDB","Cappuccino muy caliente", R.drawable.bebida_cappuccino);
        insertData(db, TABLE_DRINK,"FilterDB","Es nuestro mejor cafe", R.drawable.bebida_filter);
        insertData(db, TABLE_DRINK,"PersonalizadoDB","Un cafe personalizado", R.drawable.bebida_conejo);
    }

    private void createDBFood(SQLiteDatabase db){
        /*Se crea la tabla food*/
        String query =
                "create table FOOD ( " +
                        "_id integer primary key autoincrement, " +
                        "NAME text, " +
                        "DESCRIPTION text, " +
                        "IMAGE_RESOURCE_ID integer); ";
        db.execSQL(query);
        /*Se insertan cuatro filas invocando el metod creado insertDrink*/
        insertData(db, TABLE_FOOD,"Burritos","Son burrios (desde bd)", R.drawable.comida_burritos);
        insertData(db, TABLE_FOOD,"Chiles","Son chiles (desde bd)", R.drawable.comida_chiles);
        insertData(db, TABLE_FOOD,"Donas","Son donas (desde bd)", R.drawable.comida_donas);
        insertData(db, TABLE_FOOD,"Hamburguesas","Es hamburguesa (desde bd)", R.drawable.comida_hamburguesas);
    }

    private void createDBStore(SQLiteDatabase db){
        /*Se crea la tabla store*/
        String query =
                "create table STORE ( " +
                        "_id integer primary key autoincrement, " +
                        "NAME text, " +
                        "DESCRIPTION text, " +
                        "IMAGE_RESOURCE_ID integer); ";
        db.execSQL(query);
        /*Se insertan cuatro filas invocando el metod creado insertDrink*/
        insertData(db, TABLE_STORE,"Aurrera","Es aurrera (desde bd)", R.drawable.tienda_aurrera);
        insertData(db, TABLE_STORE,"Coopel","Es coopel (desde bd)", R.drawable.tienda_coopel);
        insertData(db, TABLE_STORE,"Oxxo","Es el oxxo (desde bd)", R.drawable.tienda_oxxo);
        insertData(db, TABLE_STORE,"Samsung","Electronica Samsung (desde bd)", R.drawable.tienda_samsung);
    }
}
