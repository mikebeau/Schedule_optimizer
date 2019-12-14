package com.example.scheduleoptimizer;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Course1Preferences extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RelativeLayout dynamicLayout;
    private int numProfs;
    private int numDaysTimes;
    private ArrayList<String> ProfList;
    private ArrayList<String> DayTime;
    private ArrayList<String> PorT;
    private Button InputPreferences;
    private ArrayList<String> DepartmentsEntered;
    private ArrayList<String> CourseNumbersEntered;
    private ArrayList<String> ChosenCourses;
    private ArrayList<String> Test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1_preferences);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Importing arrays so we can pass them back to PreferencesMainMenu at the end
        DepartmentsEntered=getIntent().getStringArrayListExtra("DepartmentsEntered");
        CourseNumbersEntered=getIntent().getStringArrayListExtra("NumbersEntered");
        ChosenCourses=getIntent().getStringArrayListExtra("CourseNames");
        Test=getIntent().getStringArrayListExtra("C1prof");

        ProfList=new ArrayList<String>();
        ProfList.add("Castanon");
        ProfList.add("Lewis");
        ProfList.add("Nagem");
        ProfList.add(0,"empty");
        DayTime=new ArrayList<String>();
        DayTime.add("Morning (7AM to noon)");
        DayTime.add("Afternoon (noon to 5PM)");
        DayTime.add("Evening (5PM to 10PM)");
        DayTime.add(0,"empty");
        numProfs=ProfList.size()-1;
        numDaysTimes=DayTime.size()-1;

        PorT = new ArrayList<String>();
        PorT.add("empty");
        PorT.add("Professor");
        PorT.add("Time");

        InputPreferences = findViewById(R.id.inputButton);
        InputPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPreferencesMainMenu();
            }
        });

        //initializing outside if statements, so that one variable can be re-used in many different contexts
        dynamicLayout = findViewById(R.id.dynamicLayout);
        TextView textView;
        RelativeLayout.LayoutParams params;
        ViewGroup.LayoutParams higherparams;
        Spinner spinnerx;

        //Creating adapter
        ArrayAdapter adapterDays = new ArrayAdapter(this, android.R.layout.simple_spinner_item, DayTime);
        //Specify layout to use when choices appear
        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Creating adapter
        ArrayAdapter adapterProfs = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ProfList);
        //Specify layout to use when choices appear
        adapterProfs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Creating adapter
        ArrayAdapter adapterPorT = new ArrayAdapter(this, android.R.layout.simple_spinner_item, PorT);
        //Specify layout to use when choices appear
        adapterPorT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Assigning adapter to PorT spinner (always there)
        Spinner PorT=findViewById(R.id.SpinPT);
        PorT.setOnItemSelectedListener(this);
        PorT.setAdapter(adapterPorT);

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
        if (numDaysTimes>1){
            //Assigning Spinner
            Spinner DayChoice1=findViewById(R.id.SpinDays1);
            //Creating spinner click listener
            DayChoice1.setOnItemSelectedListener(this);
            //Apply adapter to spinners
            DayChoice1.setAdapter(adapterDays);
            //Repeat for second spinner
            Spinner DayChoice2=findViewById(R.id.SpinDays2);
            DayChoice2.setOnItemSelectedListener(this);
            DayChoice2.setAdapter(adapterDays);
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
            Spinner DayChoice2=findViewById(R.id.SpinDays3);
            DayChoice2.setOnItemSelectedListener(this);
            DayChoice2.setAdapter(adapterDays);
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
        if (numProfs>1){
            Spinner ProfChoice1=findViewById(R.id.SpinProf1);
            ProfChoice1.setOnItemSelectedListener(this);
            ProfChoice1.setAdapter(adapterProfs);
            Spinner ProfChoice2=findViewById(R.id.SpinProf2);
            ProfChoice2.setOnItemSelectedListener(this);
            ProfChoice2.setAdapter(adapterProfs);
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
            Spinner ProfChoice3=findViewById(R.id.SpinProf3);
            ProfChoice3.setOnItemSelectedListener(this);
            ProfChoice3.setAdapter(adapterProfs);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        //Transfers spinner item chosen to string for later assignment
        String SpinResult = parent.getItemAtPosition(pos).toString();
        Toast.makeText(getApplicationContext(), "You chose a preference!", Toast.LENGTH_LONG);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //Action if nothing is selected here
        String testSpinResult = "@string/noSelection";
        Toast.makeText(arg0.getContext(), "@string/select", Toast.LENGTH_SHORT).show();
    }

    public void openPreferencesMainMenu(){
        Intent intent = new Intent(this, PreferencesMainMenu.class);
        intent.putExtra("DepartmentsEntered", DepartmentsEntered);
        intent.putExtra("NumbersEntered", CourseNumbersEntered);
        intent.putExtra("CourseNames",ChosenCourses);
        startActivity(intent);
    }

    }

