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
        TextView class1 = new TextView(this);
        TextView class2 = new TextView(this);
        TextView class3 = new TextView(this);
        TextView class4 = new TextView(this);
        TextView class5 = new TextView(this);

        String[] start_time_array = {"9:05am", "8:00pm"};
        String[] end_time_array = {"9:55am", "10:00pm"};
        String[] color_array = {"#ff0000", "#ffff00", "#80ff00", "#00ffff", "#0040ff", "#ff00ff"};
        String[] class_name_array = {"BE 492", "Class 2"};
        String[] day_array = {"Mon", "Tuesday"};

        for(int i = 0; i<start_time_array.length; i++){
            String delims = "[,]+";
            String[] day_string;
            day_string = day_array[i].split(delims);
            for(int j = 0; j < day_string.length;j++)
            {
                plotClass(day_string[j], start_time_array[i], end_time_array[i], color_array[i], class_name_array[i]);
            }

        }




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void plotClass(String day, String start_time, String  end_time, String color, String class_name) {
        TextView classnum = new TextView(this);
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

        int ending_time;
        int begin_time;
        String am_string = "am";

        if(end_time.contains(am_string)) {
            ending_time = Integer.valueOf(end_string[0]) * 60 + Integer.valueOf(end_string[1].substring(0,2)) - 7 * 60;
        }
        else{
            ending_time = Integer.valueOf(end_string[0]) * 60 + Integer.valueOf(end_string[1].substring(0,2)) - 7 * 60 + 12*60;
        }

        if(start_time.contains(am_string)) {
            begin_time = Integer.valueOf(start_string[0]) * 60 + Integer.valueOf(start_string[1].substring(0,2)) - 7 * 60;
        }
        else{
            begin_time = Integer.valueOf(start_string[0]) * 60 + Integer.valueOf(start_string[1].substring(0,2)) - 7 * 60 + 12*60;
        }


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

        if (day.contains("Mon")) {
            mondaySchedule.addView(classnum);
        } else if (day.contains("Tue")) {
            tuesdaySchedule.addView(classnum);
        } else if (day.contains("Wed")) {
            wednesdaySchedule.addView(classnum);
        } else if (day.contains("Thu")) {
            thursdaySchedule.addView(classnum);
        } else if (day.contains("Fri")) {
            fridaySchedule.addView(classnum);
        }

    }

}
