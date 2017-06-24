package com.example.taz.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView homeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeText = (TextView) findViewById(R.id.homeText);



        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        //Intent mainIntent = new Intent(MainActivity.this,GetStartedActivity.class);
        //startActivity(mainIntent);
    }
}
