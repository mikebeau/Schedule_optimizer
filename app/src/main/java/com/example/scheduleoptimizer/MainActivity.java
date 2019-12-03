package com.example.scheduleoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button openInfo;
    private Button openSavedSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openSavedSchedule = (Button) findViewById(R.id.openSavedSchedule);
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
