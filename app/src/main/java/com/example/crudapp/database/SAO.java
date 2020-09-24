package com.example.crudapp.database;
import com.example.crudapp.database.Note;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.crudapp.database.StudentContract.FeedEntry;
public class SAO  {
    SQLiteDatabase db;
    StudentReaderHelper helper;
    private static final String SQL_UPDATE="SELECT * FROM "+FeedEntry.TABLE_NAME+" WHERE "+FeedEntry._ID+" = ";
    public SAO(Context context){
        helper = new StudentReaderHelper(context);
    }
    public void openDb(){
        db = helper.getWritableDatabase();
    }
    public void closeDb(){
        db.close();
    }

    public boolean createRow(String name , String email){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME,name);
        values.put(FeedEntry.COLUMN_EMAIL,email);
        boolean createSuccess = db.insert(FeedEntry.TABLE_NAME,null,values)>0;
        return createSuccess;
    }
    public Cursor readRows(){
        openDb();
        return db.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
    }
    public String readRow(){
        Cursor cursor = db.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
       if(cursor!=null) {
           cursor.moveToLast();
           int nameIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME);
           int emailIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_EMAIL);
           String name = cursor.getString(nameIndex);
           String email = cursor.getString(emailIndex);


           return name + " - " + email;
       }else return "sorry";
    }
    public boolean createRow(Note note){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME,note.getName());
        values.put(FeedEntry.COLUMN_EMAIL,note.getEmail());
        boolean createSuccess = db.insert(FeedEntry.TABLE_NAME,null,values)>0;
        return createSuccess;
    }
    public boolean update(String name, String email){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME,name);
        values.put(FeedEntry.COLUMN_EMAIL,email);
        String where =" _id = ?";
        String[] whereArgs = {FeedEntry._ID};
        SQLiteDatabase db=helper.getWritableDatabase();
        boolean updated = db.update(FeedEntry.TABLE_NAME,values,null,null   )>0;
                db.close();
                return updated;
    }
    public boolean delete(String name){
        boolean deleteSuccess=false;
        SQLiteDatabase db = helper.getWritableDatabase();
        deleteSuccess = db.delete(FeedEntry.TABLE_NAME,FeedEntry.COLUMN_NAME+"="+name,null)>0;
        db.close();
        return deleteSuccess;

    }
}
