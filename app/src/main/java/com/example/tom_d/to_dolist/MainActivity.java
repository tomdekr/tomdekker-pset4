package com.example.tom_d.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //COMPILE AND RUN DATABASE
        TodoDatabase db = TodoDatabase.getInstance(getApplicationContext());

    }
}
