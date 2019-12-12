package com.example.scheduleoptimizer;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RankCourses extends AppCompatActivity {

    private Button enterRankings;

    private Spinner topChoice;
    private Spinner Choice2;
    private Spinner Choice3;
    private Spinner Choice4;
    private Spinner Choice5;

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

        topChoice=findViewById(R.id.CourseChoice1);
        //Spinner click listeners
        AdapterView.OnItemSelectedListener listener = topChoice.getOnItemSelectedListener();
        topChoice.setOnItemSelectedListener(listener);

        //Create array adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.testSpinArray, android.R.layout.simple_spinner_item);
        //Specify layout to use when choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply adapter to spinners
        topChoice.setAdapter(adapter);
    }

    //User-spinner interactions:
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        //Transfers spinner item chosen to string for later use
        String testSpinResult = parent.getItemAtPosition(pos).toString();
        //Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + testSpinResult, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //Action if nothing is selected here
        String testSpinResult = "@string/noSelection";
        Toast.makeText(arg0.getContext(), "@string/select", Toast.LENGTH_LONG).show();
    }

    public void openPreferencesMainMenu(){
        Intent intent =new Intent(this,PreferencesMainMenu.class);
        startActivity(intent);
    }}