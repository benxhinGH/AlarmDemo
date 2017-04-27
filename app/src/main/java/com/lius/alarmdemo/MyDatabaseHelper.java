package com.lius.alarmdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_ALARM="create table Alarm ("
            +"id integer primary key autoincrement,"
            +"hour integer,"
            +"minute integer,"
            +"type integer,"
            +"status integer)";


    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ALARM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
