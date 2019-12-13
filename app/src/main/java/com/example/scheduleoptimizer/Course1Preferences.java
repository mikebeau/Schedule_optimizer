package com.example.scheduleoptimizer;

import android.app.ActionBar;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Course1Preferences extends AppCompatActivity {

    RelativeLayout dynamicLayout;
    int numProfs;
    int numTimes;
    int numDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1_preferences);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numDays=1;
        dynamicLayout = findViewById(R.id.dynamicLayout);
        TextView textView;
        RelativeLayout.LayoutParams params;
        switch(numDays){
            case 1:
                textView=findViewById(R.id.textViewTime1);
                dynamicLayout.removeView(textView);
                textView=findViewById(R.id.textViewTime2);
                dynamicLayout.removeView(textView);
                Spinner spinner=findViewById(R.id.SpinDays1);
                dynamicLayout.removeView(spinner);
                spinner=findViewById(R.id.SpinDays2);
                dynamicLayout.removeView(spinner);
                textView=findViewById(R.id.textViewTime);
                textView.setText("Only 1 option for days of week");
                break;
            case 2:
                break;
            case 3:
                textView=findViewById(R.id.textViewDay1);
                ViewGroup.LayoutParams higherparams=textView.getLayoutParams();
                higherparams.width=250;
                textView.setLayoutParams(higherparams);
                textView=findViewById(R.id.textViewDay2);
                higherparams=textView.getLayoutParams();
                higherparams.width=250;
                textView.setLayoutParams(higherparams);
                TextView textViewTime3=new TextView(this);
                textViewTime3.setText("Third choice:");
                params=new RelativeLayout.LayoutParams(250, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8,16,0,0);
                params.addRule(RelativeLayout.BELOW,R.id.textViewDay);
                params.addRule(RelativeLayout.RIGHT_OF,R.id.textViewDay2);
                textViewTime3.setLayoutParams(params);
                dynamicLayout.addView(textViewTime3);
                break;
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
}
