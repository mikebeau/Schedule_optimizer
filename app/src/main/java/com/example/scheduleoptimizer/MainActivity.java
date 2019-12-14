package com.example.scheduleoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Application;

public class MainActivity extends AppCompatActivity {

    private Button openInfo;
    private Button openSavedSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
}

