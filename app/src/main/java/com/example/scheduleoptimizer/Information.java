package com.example.scheduleoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.exec.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Information extends AppCompatActivity {
    private Button openSelectedClasses;
    private EditText editC1;
    private EditText editC2;
    private EditText editC3;
    private EditText editC4;
    private EditText editC5;
    private EditText editD1;
    private EditText editD2;
    private EditText editD3;
    private EditText editD4;
    private EditText editD5;
    private EditText editN1;
    private EditText editN2;
    private EditText editN3;
    private EditText editN4;
    private EditText editN5;
    private ArrayList<String> CourseNumbersEntered;
    private ArrayList<String> DepartmentsEntered;
    private ArrayList<String> CourseNamesEntered;
    private ArrayList<String> ColStrings;

    //private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Initializing string lists for user input
        CourseNumbersEntered=new ArrayList<String>();
        DepartmentsEntered=new ArrayList<String>();
        ColStrings=new ArrayList<String>();
        CourseNamesEntered=new ArrayList<String>();

        //Connecting layout button and program response to button initialized here
        openSelectedClasses = findViewById(R.id.selectedclasses);
        openSelectedClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySelectedClasses();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Connecting all EditText boxes displayed here to layout objects
        editC1= (EditText) findViewById(R.id.editCollege1);
        editC2= (EditText) findViewById(R.id.editCollege2);
        editC3= (EditText) findViewById(R.id.editCollege3);
        editC4= (EditText) findViewById(R.id.editCollege4);
        editC5= (EditText) findViewById(R.id.editCollege5);
        editD1= (EditText) findViewById(R.id.editDepartment1);
        editD2= (EditText) findViewById(R.id.editDepartment2);
        editD3= (EditText) findViewById(R.id.editDepartment3);
        editD4= (EditText) findViewById(R.id.editDepartment4);
        editD5= (EditText) findViewById(R.id.editDepartment5);
        editN1= (EditText) findViewById(R.id.editCourse1);
        editN2= (EditText) findViewById(R.id.editCourse2);
        editN3= (EditText) findViewById(R.id.editCourse3);
        editN4= (EditText) findViewById(R.id.editCourse4);
        editN5= (EditText) findViewById(R.id.editCourse5);
    }

    public void openActivitySelectedClasses(){
        if(!ErrCheck())
            Toast.makeText(getApplicationContext(),"Check your class entries!",Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(this, RankCourses.class);
            intent.putExtra("DepartmentsEntered", DepartmentsEntered);
            intent.putExtra("CoursesEntered", CourseNumbersEntered);
            intent.putExtra("CourseNames",CourseNamesEntered);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selected_classes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    boolean ErrCheck() {
        /*//This list doesn't matter, since we're only accessing Engineering courses
        //Would include in later version of app
        ColStrings.add(editC1.getText().toString());
        ColStrings.add(editC2.getText().toString());
        ColStrings.add(editC3.getText().toString());
        ColStrings.add(editC4.getText().toString());
        ColStrings.add(editC5.getText().toString());
        for (int i = 0; i < ColStrings.size(); i++){
            if (!ColStrings.get(i).contains("ENG")) {
                return false;
            }
        }*/
        DepartmentsEntered.add(editD1.getText().toString());
        DepartmentsEntered.add(editD2.getText().toString());
        DepartmentsEntered.add(editD3.getText().toString());
        DepartmentsEntered.add(editD4.getText().toString());
        DepartmentsEntered.add(editD5.getText().toString());
        /*This is error-checking--caused app crashes.  Will only fix if time
        for (int i=0; i<DepartmentsEntered.size(); i++){
            if(DepartmentsEntered.get(1).length()!=2) {
                String toPrint=Integer.toString(DepartmentsEntered.get(1).length());
                Toast.makeText(getApplicationContext(),DepartmentsEntered.get(1), Toast.LENGTH_LONG).show();
                return false;
            }
            */

        CourseNumbersEntered.add(editN1.getText().toString());
        CourseNumbersEntered.add(editN2.getText().toString());
        CourseNumbersEntered.add(editN3.getText().toString());
        CourseNumbersEntered.add(editN4.getText().toString());
        CourseNumbersEntered.add(editN5.getText().toString());
        /*This is error-checking--caused app crashes.  Will only fix if time
        for (int i=0; i<CourseNumbersEntered.size(); i++){
            if(CourseNumbersEntered.get(1).length()!=3) {
                String toPrint=Integer.toString(CourseNumbersEntered.get(1).length());
                Toast.makeText(getApplicationContext(),toPrint, Toast.LENGTH_LONG).show();
                return false;
            }*/
        String name;
        for (int i=0; i<5; i++)
        {
            name="ENG "+DepartmentsEntered.get(i)+CourseNumbersEntered.get(i);
            CourseNamesEntered.add(name);
        }
        return true;
    }

}


