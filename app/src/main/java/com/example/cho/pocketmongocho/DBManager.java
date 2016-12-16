package com.example.cho.pocketmongocho;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cho on 2016-12-17.
 */

public class DBManager extends SQLiteOpenHelper {
    public DBManager(Context context, String name, CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE POCKET(id INTEGER PRIMARY KEY AUTOINCREMENT, catch INTEGER, locX REAL, locY REAL);");
        for (int i = 0; i < 9; i++){
            db.execSQL("INSERT INTO POCKET VALUES(null, 0, 0.00, 0.00);");
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void setDB(int index, double locX, double locY){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE POCKET SET catch = 1 WHERE id = " + (index+1));
        db.execSQL("UPDATE POCKET SET locX = " + locX + " WHERE id = " + (index+1));
        db.execSQL("UPDATE POCKET SET locY = " + locY + " WHERE id = " + (index+1));
        db.close();
    }
    public int getCatch(int index){
        int catchNum = 0;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM POCKET", null);
        for (int i = 0; i <= index; i++){
            cursor.moveToNext();
            catchNum = cursor.getInt(1);
        }
        db.close();

        return catchNum;
    }
    public double getLocX(int index){
        double locX = 0.00;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM POCKET", null);
        for (int i = 0; i <= index; i++){
            cursor.moveToNext();
            locX = cursor.getDouble(2);
        }
        db.close();

        return locX;
    }
    public double getLocY(int index){
        double locY = 0.00;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM POCKET", null);
        for (int i = 0; i <= index; i++){
            cursor.moveToNext();
            locY = cursor.getDouble(3);
        }
        db.close();

        return locY;
    }
}
