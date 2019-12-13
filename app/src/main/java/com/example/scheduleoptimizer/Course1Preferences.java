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

import java.util.ArrayList;

public class Course1Preferences extends AppCompatActivity {

    RelativeLayout dynamicLayout;
    int numProfs;
    int numDaysTimes;
    ArrayList<String> ProfList;
    ArrayList<String> DayList;
    ArrayList<String> StartTimeList;
    ArrayList<String> StopTimeList;
    ArrayList<String> DayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1_preferences);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numDaysTimes=10;
        numProfs=10;
        //initializing outside if statements, so that one variable can be re-used in many different contexts
        dynamicLayout = findViewById(R.id.dynamicLayout);
        TextView textView;
        RelativeLayout.LayoutParams params;
        ViewGroup.LayoutParams higherparams;
        Spinner spinnerx;
        if(numDaysTimes==1) {
            //removes unnecessary TextViews and Spinners
            textView = findViewById(R.id.textViewDay1);
            dynamicLayout.removeView(textView);
            textView = findViewById(R.id.textViewDay2);
            dynamicLayout.removeView(textView);
            Spinner spinner = findViewById(R.id.SpinDays1);
            dynamicLayout.removeView(spinner);
            spinner = findViewById(R.id.SpinDays2);
            dynamicLayout.removeView(spinner);
            //prints out new message explaining why there aren't spinners for this category
            textView = findViewById(R.id.textViewDay);
            textView.setText("Only 1 option for day and time");
            textView = findViewById(R.id.textViewPT);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.textViewDay);
            textView.setLayoutParams(params);
        }
        if (numDaysTimes>2) {
            //re-positions TextViews to accommodate a third on the same line
            textView = findViewById(R.id.textViewDay1);
            higherparams = textView.getLayoutParams();
            higherparams.width = 300;
            textView.setLayoutParams(higherparams);
            textView = findViewById(R.id.textViewDay2);
            higherparams = textView.getLayoutParams();
            higherparams.width = 300;
            textView.setLayoutParams(higherparams);
            //adds new TextView
            textView = findViewById(R.id.textViewDays3);
            textView.setText("Third choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewDay);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewDay2);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            //re-positions Spinners to accommodate a third on the same line
            spinnerx = findViewById(R.id.SpinDays1);
            higherparams = spinnerx.getLayoutParams();
            higherparams.width = 300;
            spinnerx.setLayoutParams(higherparams);
            spinnerx = findViewById(R.id.SpinDays2);
            higherparams = spinnerx.getLayoutParams();
            higherparams.width = 300;
            spinnerx.setLayoutParams(higherparams);
            //Adds new spinner
            spinnerx = findViewById(R.id.SpinDays3);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewDay1);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinDays2);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
        }
        if (numDaysTimes > 3) {
            //Adds new TextView and Spinner
            textView = findViewById(R.id.textViewDays4);
            textView.setText("Fourth choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinDays1);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            spinnerx = findViewById(R.id.SpinDays4);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewDays4);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
            //Adjusts row below Days choices
            textView = findViewById(R.id.textViewPT);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.SpinDays4);
            textView.setLayoutParams(params);
        }

        if (numDaysTimes > 4) {
            //Adds new TextView and Spinner
            textView = findViewById(R.id.textViewDays5);
            textView.setText("Fifth choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinDays2);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewDays4);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            spinnerx = findViewById(R.id.SpinDays5);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewDays5);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinDays4);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
            //Adjusts row below Days choices
            textView = findViewById(R.id.textViewPT);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.SpinDays4);
            textView.setLayoutParams(params);
            }
            if (numDaysTimes > 5) {
                //Adds new TextView and Spinner
                textView = findViewById(R.id.textViewDays6);
                textView.setText("Sixth choice:");
                params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
                int px = (int) (16 * getResources().getDisplayMetrics().density);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays3);
                params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewDays5);
                textView.setLayoutParams(params);
                textView.setVisibility(View.VISIBLE);
                spinnerx = findViewById(R.id.SpinDays6);
                params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.textViewDays6);
                params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinDays5);
                spinnerx.setLayoutParams(params);
                spinnerx.setVisibility(View.VISIBLE);
                //Adjusts row below Days choices
                textView = findViewById(R.id.textViewPT);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays4);
                textView.setLayoutParams(params);
            }
            if (numDaysTimes > 6) {
                //Adds new TextView and Spinner
                textView = findViewById(R.id.textViewDays7);
                textView.setText("Seventh choice:");
                params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
                int px = (int) (16 * getResources().getDisplayMetrics().density);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays4);
                textView.setLayoutParams(params);
                textView.setVisibility(View.VISIBLE);
                spinnerx = findViewById(R.id.SpinDays7);
                params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.textViewDays7);
                spinnerx.setLayoutParams(params);
                spinnerx.setVisibility(View.VISIBLE);
                //Adjusts row below Days choices
                textView = findViewById(R.id.textViewPT);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays7);
                textView.setLayoutParams(params);
            }
            if (numDaysTimes > 7) {
                //Adds new TextView and Spinner
                textView = findViewById(R.id.textViewDays8);
                textView.setText("Eighth choice:");
                params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
                int px = (int) (16 * getResources().getDisplayMetrics().density);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays5);
                params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewDays7);
                textView.setLayoutParams(params);
                textView.setVisibility(View.VISIBLE);
                spinnerx = findViewById(R.id.SpinDays8);
                params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.textViewDays8);
                params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinDays7);
                spinnerx.setLayoutParams(params);
                spinnerx.setVisibility(View.VISIBLE);
                //Adjusts row below Days choices
                textView = findViewById(R.id.textViewPT);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays7);
                textView.setLayoutParams(params);
            }
            if (numDaysTimes > 8) {
                //Adds new TextView and Spinner
                textView = findViewById(R.id.textViewDays9);
                textView.setText("Ninth choice:");
                params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
                int px = (int) (16 * getResources().getDisplayMetrics().density);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays6);
                params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewDays8);
                textView.setLayoutParams(params);
                textView.setVisibility(View.VISIBLE);
                spinnerx = findViewById(R.id.SpinDays9);
                params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.textViewDays9);
                params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinDays8);
                spinnerx.setLayoutParams(params);
                spinnerx.setVisibility(View.VISIBLE);
                //Adjusts row below Days choices
                textView = findViewById(R.id.textViewPT);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays7);
                textView.setLayoutParams(params);
            }

            if (numDaysTimes>9){
                //Adds TextView saying too many options!
                textView = findViewById(R.id.textViewDays10);
                textView.setText("Too many options for days and times available!  Please choose from among only these first 9 (above).");
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                int px = (int) (16 * getResources().getDisplayMetrics().density);
                params.setMargins(px, px, 0, 0);
                params.addRule(RelativeLayout.BELOW, R.id.SpinDays9);
                textView.setLayoutParams(params);
                textView.setVisibility(View.VISIBLE);
                //Adjusts row below Days choices
                textView = findViewById(R.id.textViewPT);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.textViewDays10);
                textView.setLayoutParams(params);
            }

        if(numProfs==1) {
            //removes unnecessary TextViews and Spinners
            textView = findViewById(R.id.textViewProf1);
            dynamicLayout.removeView(textView);
            textView = findViewById(R.id.textViewProf2);
            dynamicLayout.removeView(textView);
            Spinner spinner = findViewById(R.id.SpinProf1);
            dynamicLayout.removeView(spinner);
            spinner = findViewById(R.id.SpinProf2);
            dynamicLayout.removeView(spinner);
            //prints out new message explaining why there aren't spinners for this category
            textView = findViewById(R.id.textViewProf);
            textView.setText("Only 1 option for Professor");
            textView = findViewById(R.id.textViewDay);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf);
            textView.setLayoutParams(params);
        }
        if (numProfs>2) {
            //re-positions TextViews to accommodate a third on the same line
            textView = findViewById(R.id.textViewProf1);
            higherparams = textView.getLayoutParams();
            higherparams.width = 300;
            textView.setLayoutParams(higherparams);
            textView = findViewById(R.id.textViewProf2);
            higherparams = textView.getLayoutParams();
            higherparams.width = 300;
            textView.setLayoutParams(higherparams);
            //adds new TextView
            textView = findViewById(R.id.textViewProf3);
            textView.setText("Third choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewProf2);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            //re-positions Spinners to accommodate a third on the same line
            spinnerx = findViewById(R.id.SpinProf1);
            higherparams = spinnerx.getLayoutParams();
            higherparams.width = 300;
            spinnerx.setLayoutParams(higherparams);
            spinnerx = findViewById(R.id.SpinProf2);
            higherparams = spinnerx.getLayoutParams();
            higherparams.width = 300;
            spinnerx.setLayoutParams(higherparams);
            //Adds new spinner
            spinnerx = findViewById(R.id.SpinProf3);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf1);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinProf2);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
        }
        if (numProfs > 3) {
            //Adds new TextView and Spinner
            textView = findViewById(R.id.textViewProf4);
            textView.setText("Fourth choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf1);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            spinnerx = findViewById(R.id.SpinProf4);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf4);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
            //Adjusts row below Profs choices
            textView = findViewById(R.id.textViewDay);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf4);
            textView.setLayoutParams(params);
        }

        if (numProfs > 4) {
            //Adds new TextView and Spinner
            textView = findViewById(R.id.textViewProf5);
            textView.setText("Fifth choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf2);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewProf4);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            spinnerx = findViewById(R.id.SpinProf5);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf5);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinProf4);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
            //Adjusts row below Profs choices
            textView = findViewById(R.id.textViewDay);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf4);
            textView.setLayoutParams(params);
        }
        if (numProfs > 5) {
            //Adds new TextView and Spinner
            textView = findViewById(R.id.textViewProf6);
            textView.setText("Sixth choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf3);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewProf5);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            spinnerx = findViewById(R.id.SpinProf6);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf6);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinProf5);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
            //Adjusts row below Profs choices
            textView = findViewById(R.id.textViewDay);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf4);
            textView.setLayoutParams(params);
        }
        if (numProfs > 6) {
            //Adds new TextView and Spinner
            textView = findViewById(R.id.textViewProf7);
            textView.setText("Seventh choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf4);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            spinnerx = findViewById(R.id.SpinProf7);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf7);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
            //Adjusts row below Profs choices
            textView = findViewById(R.id.textViewDay);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf7);
            textView.setLayoutParams(params);
        }
        if (numProfs > 7) {
            //Adds new TextView and Spinner
            textView = findViewById(R.id.textViewProf8);
            textView.setText("Eighth choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf5);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewProf7);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            spinnerx = findViewById(R.id.SpinProf8);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf8);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinProf7);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
            //Adjusts row below Profs choices
            textView = findViewById(R.id.textViewDay);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf7);
            textView.setLayoutParams(params);
        }
        if (numProfs > 8) {
            //Adds new TextView and Spinner
            textView = findViewById(R.id.textViewProf9);
            textView.setText("Ninth choice:");
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf6);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.textViewProf8);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            spinnerx = findViewById(R.id.SpinProf9);
            params = new RelativeLayout.LayoutParams(300, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf9);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.SpinProf8);
            spinnerx.setLayoutParams(params);
            spinnerx.setVisibility(View.VISIBLE);
            //Adjusts row below Profs choices
            textView = findViewById(R.id.textViewDay);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf7);
            textView.setLayoutParams(params);
        }

        if (numProfs>9){
            //Adds TextView saying too many options!
            textView = findViewById(R.id.textViewProf10);
            textView.setText("Too many options for professors available!  Please choose from among only these first 9 (above).");
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int px = (int) (16 * getResources().getDisplayMetrics().density);
            params.setMargins(px, px, 0, 0);
            params.addRule(RelativeLayout.BELOW, R.id.SpinProf9);
            textView.setLayoutParams(params);
            textView.setVisibility(View.VISIBLE);
            //Adjusts row below Profs choices
            textView = findViewById(R.id.textViewDay);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, R.id.textViewProf10);
            textView.setLayoutParams(params);
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

