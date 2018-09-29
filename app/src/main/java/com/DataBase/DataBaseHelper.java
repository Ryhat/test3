package com.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tasks1.db";
    public static final String TABLE_NAME = "tasks_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "TASK_NAME";
    public static final String COL_3 = "START_TIME";
    public static final String COL_4 = "END_TIME";
    public static final String COL_5 = "STATUS";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TASK_NAME TEXT, " +
                "START_TIME TEXT, " +
                "END_TIME TEXT, " +
                "STATUS INTEGER)", TABLE_NAME));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
    }

    public boolean insertData(String task_name, String start_time, String end_time, String status){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, task_name);
        contentValues.put(COL_3, start_time);
        contentValues.put(COL_4, end_time);
        contentValues.put(COL_4, status);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        //To check whether data is inserted in databese
        if(result == 1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_NAME), null);
        return  cursor;
    }
}





