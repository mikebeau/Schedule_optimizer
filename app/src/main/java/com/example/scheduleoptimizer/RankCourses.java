package com.example.scheduleoptimizer;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.Button;

public class RankCourses extends AppCompatActivity {

    private Button enterRankings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_courses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        enterRankings=findViewById(R.id.enterRankings);
        enterRankings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPreferencesMainMenu();
            }
    });

}

    public void openPreferencesMainMenu(){
        Intent intent =new Intent(this,PreferencesMainMenu.class);
        startActivity(intent);
    }}