package com.example.ady.myphoneapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ady on 11/27/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_BASE ="Contact.DB";
    public static final String TABLE_NAME = "Contact_TABLE";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Number";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Address";
    public static final String COL_5 = "Sport";
    public static final String COL_6 = "Mammals";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_BASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                " (Name TEXT PRIMARY KEY," +
                " Number TEXT," +
                " Email TEXT," +
                " Address TEXT," +
                " Sport TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData (String name, String number, String email,
                               String address, String sport ){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,number);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,address);
        contentValues.put(COL_5,sport);

        long row  = database.insert(TABLE_NAME,null,contentValues);

        if (row == -1)
            return false;
        else
            return true;
    }
    public Cursor getSingleColunm(String ColunmName)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor results = database.rawQuery("SELECT " +ColunmName+  " FROM "+ TABLE_NAME,null);
        return  results;
    }
    public Cursor getallData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor results = database.rawQuery("SELECT *  FROM "+ TABLE_NAME,null);
        return  results;

    }
}
