package com.example.scheduleoptimizer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScheduleDisplay extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        RelativeLayout mondaySchedule = findViewById(R.id.mondayRelativeLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        //Graphing example Class
        TextView class2 = new TextView(this);
        String start_time = "7:00";
        String end_time = "10:00";
        String color = "#cc0000";

        plotClass(class2, "Thursday", start_time, end_time, color, "mechanics");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void plotClass(TextView classnum, String day, String start_time, String  end_time, String color, String class_name) {
        TextView class1 = new TextView(this);
        TypedValue dimention = new TypedValue();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout mondaySchedule = findViewById(R.id.mondayRelativeLayout);
        RelativeLayout tuesdaySchedule = findViewById((R.id.tuesdayRelativeLayout));
        RelativeLayout wednesdaySchedule = findViewById(R.id.wednesdayRelativeLayout);
        RelativeLayout thursdaySchedule = findViewById((R.id.thursdayRelativeLayout));
        RelativeLayout fridaySchedule = findViewById(R.id.fridayRelativeLayout);

        //Parse start_time string
        String delims = "[:]+";
        String[] start_string;
        start_string = start_time.split(delims);

        String[] end_string;
        end_string = end_time.split(delims);

        int begin_time = Integer.valueOf(start_string[0]) * 60 + Integer.valueOf(start_string[1]) - 7*60;
        int ending_time = Integer.valueOf(end_string[0]) * 60 + Integer.valueOf(end_string[1]) - 7*60;
        int class_duration = ending_time - begin_time;

        //set class width and height
        classnum.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        classnum.setHeight((int) dimention.applyDimension(dimention.COMPLEX_UNIT_DIP, class_duration, getResources().getDisplayMetrics()));
        classnum.setText(class_name);
        classnum.setBackgroundColor(Color.parseColor(color));
        classnum.setGravity(Gravity.CENTER);
        classnum.setPaddingRelative(0,0,0,0);
        params.setMargins(0,(int) dimention.applyDimension(dimention.COMPLEX_UNIT_DIP, begin_time, getResources().getDisplayMetrics()),0,0);
        classnum.setLayoutParams(params);

        //define what day the class is
        if(day.contains("Mon")) {
            mondaySchedule.addView(classnum);
        }
        else if(day.contains("Tue")) {
            tuesdaySchedule.addView(classnum);
        }
        else if(day.contains("Wed")) {
            wednesdaySchedule.addView(classnum);
        }
        else if(day.contains("Thur")) {
            thursdaySchedule.addView(classnum);
        }
        else if(day.contains("Fri")) {
            fridaySchedule.addView(classnum);
        }
    }

}
