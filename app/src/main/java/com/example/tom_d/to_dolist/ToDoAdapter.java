package com.example.tom_d.to_dolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ToDoAdapter extends CursorAdapter {
    public ToDoAdapter(Context context, Cursor cursor) {
        super(context, cursor, R.layout.row_todos);

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row_todos, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView todoText = view.findViewById(R.id.text);
        CheckBox isCompleted = view.findViewById(R.id.completed);
        todoText.setText(cursor.getString(cursor.getColumnIndex("title")));
        if (cursor.getInt(cursor.getColumnIndex("completed"))==1){
            isCompleted.setChecked(true);
        }
        else{
            isCompleted.setChecked(false);
        }

    }

}