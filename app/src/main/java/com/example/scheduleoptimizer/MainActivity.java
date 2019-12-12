package com.example.scheduleoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;
import org.jsoup.nodes.Document;


public class MainActivity extends AppCompatActivity {

    private Button openInfo;
    private Button openSavedSchedule;
    private Button testButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code for test button
        testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTestActivity();
            }
        });


        openSavedSchedule = findViewById(R.id.openSavedSchedule);
        openSavedSchedule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivitySavedSchedules();
            }
        });

        openInfo = (Button) findViewById(R.id.openInfo);
        openInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityInformation();
            }
        });
    }
    public void openActivityInformation(){
        Intent intent = new Intent(this, Information.class);
        startActivity(intent);
    }

    public void openActivitySavedSchedules(){
        Intent intent = new Intent(this, SavedSchedules.class);
        startActivity(intent);
    }

    public void openTestActivity(){
        Intent intent = new Intent(this, SelectedClasses.class);
        startActivity(intent);
    }



}
