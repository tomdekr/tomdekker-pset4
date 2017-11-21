package com.example.tom_d.to_dolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;

/**
 * Created by Tom_D on 11/19/2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private static TodoDatabase instance;
    private static final String dbName = "todos.db";
    private static final int version = 1;

    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    public static TodoDatabase getInstance(Context context){
        if(instance == null){
            instance = new TodoDatabase(context.getApplicationContext(),dbName,null , version);
        }
        return instance;}


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed INTEGER);");
      // ADD SAMPLE TO DO ITEMS
        db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("title","Do this");
//        values.put("completed",1);
//        db.insert("todos",null,values);
//        values.put("title","Do that");
//        values.put("completed",1);
//        db.insert("todos",null,values);
//        values.put("title","Do so");
//        values.put("completed",0);
//        db.insert("todos",null,values);

    }

    //CREATE INPUT METHOD
    public void insert(String title, int completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("completed", completed);
        db.insert("todos",null,values);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "todos");
        onCreate(db);    }



    public Cursor selectAll(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM todos",null);
        return cursor;
    }

    public void updateItem(long id, int completed){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", completed);
        db.update("todos",values,"_id="+id, null);

    }

    public void deleteItem(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos","_id="+id,null);

    }



}
