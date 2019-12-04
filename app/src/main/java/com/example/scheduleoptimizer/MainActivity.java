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

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button openInfo;
    private Button openSavedSchedule;
    private Button launchBtn;
    private TextView result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        launchBtn = (Button) findViewById(R.id.launchBtn);
        launchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite();
            }
        });

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

    public void getWebsite(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575419340?ModuleName=univschr.pl&SearchOptionDesc=Class+Number&SearchOptionCd=S&KeySem=20203&ViewSem=Fall+2019&College=ENG&Dept=EK&Course=381&Section=").get();

                    String title = doc.title();
                    Elements links = doc.select("a[href]");

                    builder.append(title).append("\n");

                    for (Element link : links){
                        builder.append("\n").append("Link: ").append(link.attr("href"))
                                .append("\n").append("Text: ").append(link.text());
                    }
                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();


    }
}
