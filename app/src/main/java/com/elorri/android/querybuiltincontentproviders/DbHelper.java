package com.elorri.android.querybuiltincontentproviders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Elorri on 23/09/2017.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    static final String DATABASE_NAME = "contentprovider.db";
    private Context mContext;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table "+DualTable.NAME+" (numb text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
