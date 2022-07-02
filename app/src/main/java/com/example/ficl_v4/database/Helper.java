package com.example.ficl_v4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DataFiCl.db";

    public Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constant.CREATE_TABLE_USER);
        db.execSQL(Constant.CREATE_TABLE_PRODUCT);
        db.execSQL(Constant.CREATE_TABLE_MANUFACTURER);
        db.execSQL(Constant.CREATE_TABLE_PRODUCT_IN_RATION);
//        db.execSQL(Constant.CREATE_TABLE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constant.DELETE_TABLE_USER);
        db.execSQL(Constant.DELETE_TABLE_PRODUCT);
        db.execSQL(Constant.DELETE_TABLE_MANUFACTURER);
        db.execSQL(Constant.DELETE_TABLE_PRODUCT_IN_RATION);
//        db.execSQL(Constant.DELETE_TABLE_HISTORY);
        onCreate(db);
    }
}