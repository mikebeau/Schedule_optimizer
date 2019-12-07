package com.example.scheduleoptimizer;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SelectedClasses extends AppCompatActivity {

    private TextView classlist;
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_classes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Class List definition and manipulation
        classlist = (TextView) findViewById(R.id.classlist);
        test = (Button) findViewById(R.id.test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayClassList();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void displayClassList(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                String url = "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575419340?ModuleName=univschr.pl&SearchOptionDesc=Class+Number&SearchOptionCd=S&KeySem=20203&ViewSem=Fall+2019&College=ENG&Dept=EK&Course=381&Section=";
                try {
                    Document doc = Jsoup.connect(url).get();

                    String title = doc.title();
                    Elements links = doc.select("a[href]");
                    Elements classParams = doc.select(" table:eq(4) > tbody > tr > td:nth-child(n) > font");

                    //builder.append(title).append("\n");
                    int numOfClassAttributes = 11;
                    int loopVar = 0;
                    for (Element link : classParams){
                        builder.append(link.text()).append(" | ");
                        loopVar = loopVar + 1;
                        if (loopVar % numOfClassAttributes == 0){
                            builder.append("\n");
                        }
                    }
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        classlist.setText(builder.toString());
                    }
                });
            }
        }).start();


    }
}
