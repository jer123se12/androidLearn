package com.sp.restaurantlist;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "restaurantlist.db";
    private static final int SCHEMA_VERSION = 1;

    public RestaurantHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Will be called once when the database is not created
        db.execSQL("CREATE TABLE restaurants_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " restaurantName TEXT, restaurantAddress TEXT, restaurantTel TEXT, restaurantType TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Will not be called until SCHEMA_VERSION increases
        // Here we can upgrade the database e.g. add more tables
    }

    /* Read all records from restaurants_table */
    public Cursor getAll() {
        return (getReadableDatabase().rawQuery(
                "SELECT _id, restaurantName, restaurantAddress, restaurantTel, " +
                        "restaurantType FROM restaurants_table ORDER BY restaurantName", null));
    }

    /* Write a record into restaurants_table */
    public void insert(String restaurantName, String restaurantAddress,
                       String restaurantTel, String restaurantType) {
        ContentValues cv = new ContentValues();

        cv.put("restaurantName", restaurantName);
        cv.put("restaurantAddress", restaurantAddress);
        cv.put("restaurantTel", restaurantTel);
        cv.put("restaurantType", restaurantType);

        getWritableDatabase().insert("restaurants_table", "restaurantName", cv);
    }

    public String getRestaurantName(Cursor c) {
        return (c.getString(1));
    }

    public String getRestaurantAddress(Cursor c) {
        return (c.getString(2));
    }

    public String getRestaurantTel(Cursor c) {
        return (c.getString(3));
    }

    public String getRestaurantType(Cursor c) {
        return (c.getString(4));
    }
}

