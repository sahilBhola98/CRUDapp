package com.example.crudapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.crudapp.database.StudentContract.FeedEntry;
import androidx.annotation.Nullable;
import com.example.crudapp.database.SAO;

public class StudentReaderHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    protected static final String DATABASE_NAME="StudentDatabase";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+FeedEntry.TABLE_NAME+" (" +
            FeedEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            FeedEntry.COLUMN_NAME+" TEXT, "+
            FeedEntry.COLUMN_EMAIL+" TEXT)";
    private static final String SQL_DELETE_QUERY="DROP TABLE IF EXISTS "+FeedEntry.TABLE_NAME;
    private static final String SQL_COUNT_QUERY= "SELECT * FROM "+FeedEntry.TABLE_NAME;


    public StudentReaderHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_QUERY);
            onCreate(db);
    }
    public int count(){
        SQLiteDatabase db=this.getWritableDatabase();
        int recordCount = db.rawQuery(SQL_COUNT_QUERY,null).getCount();
        db.close();
        return  recordCount;
    }
}
