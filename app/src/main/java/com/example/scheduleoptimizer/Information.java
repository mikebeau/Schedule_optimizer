package com.example.scheduleoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Information extends AppCompatActivity {
    private Button openSelectedClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        openSelectedClasses = (Button) findViewById(R.id.selectedclasses);
        openSelectedClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySelectedClasses();
            }
        });
    }
    public void openActivitySelectedClasses(){
        Intent intent = new Intent(this, SelectedClasses.class);
        startActivity(intent);
    }
}


