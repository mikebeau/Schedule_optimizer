package com.example.scheduleoptimizer;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class PreferencesMainMenu extends AppCompatActivity {

    private Button getSchedules;
    private Button editCourse1Preferences;
    private Button editCourse2Preferences;
    private Button editCourse3Preferences;
    private Button editCourse4Preferences;
    private Button editCourse5Preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_main_menu);
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

        Button getSchedules=findViewById(R.id.buttonGetSchedules);
        getSchedules.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //run optimization formulae and then
                openScheduleDisplay();
        }
    });

        Button editCourse1Preferences=findViewById(R.id.buttonC1);
        editCourse1Preferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCourse1Preferences();
            }
        });

        Button editCourse2Preferences=findViewById(R.id.buttonC2);
        editCourse2Preferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCourse1Preferences();
            }
        });

        Button editCourse3Preferences=findViewById(R.id.buttonC3);
        editCourse3Preferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCourse1Preferences();
            }
        });

        Button editCourse4Preferences=findViewById(R.id.buttonC4);
        editCourse4Preferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCourse1Preferences();
            }
        });

        Button editCourse5Preferences=findViewById(R.id.buttonC5);
        editCourse5Preferences.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCourse1Preferences();
            }
        });

}
public void openScheduleDisplay(){
        Intent intent =new Intent(this,ScheduleDisplay.class);
        startActivity(intent);
}

public void openCourse1Preferences(){
        Intent intent=new Intent(this, Course1Preferences.class);
        startActivity(intent);
}

}
