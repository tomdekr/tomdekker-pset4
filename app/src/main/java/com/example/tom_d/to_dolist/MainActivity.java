package com.example.tom_d.to_dolist;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        myToDoAdapter = new ToDoAdapter( this,overview);
        if(myToDoAdapter.getCount()<1){
            db.insert("Do this",0);
            db.insert("Do that",1);
            db.insert("Do so",0);
        }
        ListView list = (ListView) findViewById(R.id.mListView);
        list.setAdapter(myToDoAdapter);
    }
}
