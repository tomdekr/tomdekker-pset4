package com.example.tom_d.to_dolist;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ToDoAdapter myToDoAdapter;
    private TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //COMPILE AND RUN DATABASE
        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor overview = db.selectAll();
        myToDoAdapter = new ToDoAdapter(this, overview);
        if (myToDoAdapter.getCount() < 1) {
            db.insert("Do this", 1);
            db.insert("Do that", 1);
            db.insert("Do so", 1);
        }
        ListView list = (ListView) findViewById(R.id.mListView);
        list.setAdapter(myToDoAdapter);

        list.setOnItemClickListener(new shortClick());
        list.setOnItemLongClickListener(new longClick());
    }

        public class shortClick implements AdapterView.OnItemClickListener{


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox complete = view.findViewById(R.id.completed);
                Cursor overview = db.selectAll();
                overview.move(i+1);
                int id= overview.getInt(overview.getColumnIndex("_id"));
                if(complete.isChecked()){
                    db.update(id, 0);

                }
                else{
                    db.update(id,1);
                }
                updateData();
            }

        }
        private class longClick implements AdapterView.OnItemLongClickListener{
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor overview = db.selectAll();
                overview.move(i+1);
                int id= overview.getInt(overview.getColumnIndex("_id"));
                db.delete(id);
                updateData();
                return true;
            }
        }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText editor = (EditText) findViewById(R.id.editor);
        String text = editor.getText().toString();
        outState.putString("editor",text);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);
        EditText editor = (EditText) findViewById(R.id.editor);
        String text = inState.getString("editor");
        editor.setText(text);
    }

    public void add(View view){
        EditText editor = (EditText) findViewById(R.id.editor);
        String ToDo = editor.getText().toString();
        editor.setText("");
        if(ToDo.length()>0){
            TodoDatabase db = TodoDatabase.getInstance(getApplicationContext());
            db.insert(ToDo,0);
            updateData();}
    }


    private void updateData(){
        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor overview1 = db.selectAll();
        myToDoAdapter.swapCursor(overview1);
        ListView list = (ListView) findViewById(R.id.mListView);
        list.setAdapter(myToDoAdapter);
    }
}
