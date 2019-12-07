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
                String urls[] ={"https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575419340?ModuleName=univschr.pl&SearchOptionDesc=Class+Number&SearchOptionCd=S&KeySem=20203&ViewSem=Fall+2019&College=ENG&Dept=EK&Course=381&Section=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575734487?College=ENG&Dept=BE&Course=437&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739455?College=ENG&Dept=EC&Course=311&Section=&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739464?College=ENG&Dept=EC&Course=410&Section=B1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739498?College=ENG&Dept=EC&Course=464&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739625?College=ENG&Dept=EC&Course=467&Section=S6&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739666?College=ENG&Dept=EC&Course=535&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739773?College=ENG&Dept=EK&Course=&Section=&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739787?College=ENG&Dept=EK&Course=125&Section=B7&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739810?College=ENG&Dept=EK&Course=210&Section=A5&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739828?College=ENG&Dept=EK&Course=307&Section=C5&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739870?College=ENG&Dept=EK&Course=424&Section=B3&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739883?College=ENG&Dept=ME&Course=305&Section=B2&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739900?College=ENG&Dept=ME&Course=306&Section=C8&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739913?College=ENG&Dept=ME&Course=358&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739913?College=ENG&Dept=ME&Course=358&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575739927?College=ENG&Dept=ME&Course=461&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575740162?College=ENG&Dept=ME&Course=528&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575740194?College=ENG&Dept=MS&Course=&Section=&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
                "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575740239?College=ENG&Dept=SE&Course=&Section=&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd="} ;

                for(String url : urls) {
                    try {
                        Document doc = Jsoup.connect(url).get();

                        String title = doc.title();
                        Elements links = doc.select("a[href]");
                        Elements classParams = doc.select(" table:eq(4) > tbody > tr > td:nth-child(n) > font");

                        //builder.append(title).append("\n");
                        int numOfClassAttributes = 13;
                        int loopVar = -1;
                        for (Element link : classParams) {
                            builder.append(link.text()).append(" | ");
                            loopVar = loopVar + 1;
                            if (loopVar % numOfClassAttributes == 0) {
                                builder.append("\n\n");
                            }
                        }
                    } catch (IOException e) {
                        builder.append("Error: ").append(e.getMessage()).append("\n");
                    }
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
