package com.example.scheduleoptimizer;

import android.content.Intent;
import android.content.res.Resources;
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

import java.util.ArrayList;
import java.util.List;

public class RankCourses extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button enterRankings;

    private Spinner topChoice;
    private Spinner Choice2;
    private Spinner Choice3;
    private Spinner Choice4;
    private Spinner Choice5;
    private List<String> CourseRankings;
    private List<String> ChosenCourses;

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

        //Creating list of chosen courses to compare with list of course rankings at end
        //this will confirm that they haven't double-ranked any courses, or left spots blank
        Resources res = getResources();
        String[] courses = res.getStringArray(R.array.testSpinArray);
        ChosenCourses=new ArrayList<String>();
        for(int i=0; i<5; i++)
        {   ChosenCourses.add(courses[i+1]);}

        //Creating list to be filled with user's ranked course choices upon spinner selection
        CourseRankings = new ArrayList<String>();
        String empty="empty";
        for (int i=0; i<5; i++)
        {CourseRankings.add(i,empty);}

        //Assigning layout button and action to enterRankings button initialized here
        enterRankings=findViewById(R.id.enterRankings);
        enterRankings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPreferencesMainMenu();
            }
    });
        //Assigning layout object spinner to spinner initialized here
        topChoice=findViewById(R.id.CourseChoice1);
        //Creating spinner click listener
        topChoice.setOnItemSelectedListener(this);
        //Same steps for other 4 spinners
        Choice2=findViewById(R.id.CourseChoice2);
        Choice2.setOnItemSelectedListener(this);
        Choice3=findViewById(R.id.CourseChoice3);
        Choice3.setOnItemSelectedListener(this);
        Choice4=findViewById(R.id.CourseChoice4);
        Choice4.setOnItemSelectedListener(this);
        Choice5=findViewById(R.id.CourseChoice5);
        Choice5.setOnItemSelectedListener(this);

        //Create array adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.testSpinArray, android.R.layout.simple_spinner_item);
        //Specify layout to use when choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply adapter to spinners
        topChoice.setAdapter(adapter);
        Choice2.setAdapter(adapter);
        Choice3.setAdapter(adapter);
        Choice4.setAdapter(adapter);
        Choice5.setAdapter(adapter);

    }

    //User-spinner interactions:
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        //Transfers spinner item chosen to string for later assignment
        String SpinResult = parent.getItemAtPosition(pos).toString();

        //switch statement discerns into which spinner input was entered
        //depending on spinner, assigns string to different members of preference array
        switch (parent.getId()) {
            case R.id.CourseChoice1:
                CourseRankings.remove(0);
                CourseRankings.add(0,SpinResult);
                //Showing selected spinner item
                Toast.makeText(parent.getContext(), "Top course selected: " + SpinResult, Toast.LENGTH_SHORT).show();
                break;
            case R.id.CourseChoice2:
                CourseRankings.remove(1);
                CourseRankings.add(1,SpinResult);
                Toast.makeText(parent.getContext(), "Second choice course selected: " + SpinResult, Toast.LENGTH_SHORT).show();
                break;
            case R.id.CourseChoice3:
                CourseRankings.remove(2);
                CourseRankings.add(2,SpinResult);
                Toast.makeText(parent.getContext(), "Third choice course selected: " + SpinResult, Toast.LENGTH_SHORT).show();
                break;
            case R.id.CourseChoice4:
                CourseRankings.remove(3);
                CourseRankings.add(3,SpinResult);
                Toast.makeText(parent.getContext(), "Fourth choice course selected: " + SpinResult, Toast.LENGTH_SHORT).show();
                break;
            case R.id.CourseChoice5:
                CourseRankings.remove(4);
                CourseRankings.add(4,SpinResult);
                Toast.makeText(parent.getContext(), "Fifth course selected: " + SpinResult, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //Action if nothing is selected here
        String testSpinResult = "@string/noSelection";
        Toast.makeText(arg0.getContext(), "@string/select", Toast.LENGTH_SHORT).show();
    }

    public void openPreferencesMainMenu(){
        int sum=0;
        for (int i=0; i<ChosenCourses.size(); i++) {
            if (CompareLists(i))
                sum++;
        }
        if(sum==ChosenCourses.size()) {
            Intent intent = new Intent(this, PreferencesMainMenu.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(),"Please double-check your rankings!",Toast.LENGTH_LONG).show();
    }

    public boolean CompareLists(int i) {
        return (CourseRankings.contains(ChosenCourses.get(i)));
    }
}