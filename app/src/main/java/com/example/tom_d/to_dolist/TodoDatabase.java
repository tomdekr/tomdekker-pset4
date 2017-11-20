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
    private static final String dbName = "toDos.db";
    private static final int version = 1;

    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    public static TodoDatabase getInstance(Context context){
        if(instance == null){
            instance = new TodoDatabase(context.getApplicationContext(),dbName,null , version);
        }
        return instance;}

//    //CLOSE DB
//    public void close(){
//        SQLiteDatabase.close();
//    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        sqliteDatabase.execSQL("create table todos (id INTEGER PRIMARY KEY AUTOINCREMENT, name title, name completed);");
      // ADD SAMPLE TO DO ITEMS
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title","Maak app af");
        values.put("completed",0);
        db.insert("todos",null,values);
        values.put("title","Doe de was");
        values.put("completed",1);
        db.insert("todos",null,values);
        values.put("title","Stuur een mail naar werk");
        values.put("completed",0);
        db.insert("todos",null,values);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int i, int i1) {
        sqliteDatabase.execSQL("DROP TABLE IF EXISTS " + "todos");
        onCreate(sqliteDatabase);    }

    //CREATE TO DO
    public void createToDo(String todoText){

    }

    public Cursor selectAll(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM todos",null);
        return cursor; }


}
