package com.example.scheduleoptimizer;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SelectedClasses extends AppCompatActivity {
    public  int a;
    private TextView classlist;
    private Button test;
    private ProgressBar progressBar;
    //final String[] allCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_classes);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility((View.INVISIBLE));

        // Class List definition and manipulation
        classlist = (TextView) findViewById(R.id.classlist);
        test = (Button) findViewById(R.id.test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
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
                String delims = "[*|]+";
                final String[] allCourses;
                final StringBuilder builder = new StringBuilder();
                String urls[] ={"https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575734487?College=ENG&Dept=BE&Course=437&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
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
                        // connect to the url
                        Document doc = Jsoup.connect(url).get();

                        // select all the classes
                        Elements classParams = doc.select(" table:eq(4) > tbody > tr > td:nth-child(n) > font");

                        int numOfClassAttributes = 13;
                        int loopVar = -1;
                        for (Element link : classParams) {
                            builder.append(link.text()).append(" | ");
                            loopVar = loopVar + 1;
                            if (loopVar % numOfClassAttributes == 0) {
                                builder.append("\n\n*");
                            }
                        }
                    } catch (IOException e) {
                        builder.append("Error: ").append(e.getMessage()).append("\n");
                    }

                }
                //set turn this string builder into an array of courses
                allCourses = (builder.toString()).split(delims);
                a = allCourses.length/13;
                final String[] departments = new String[a];
                final String[] courseNumber = new String [a];
                final String[] colleges = new String[a];
                final String[] section = new String[a];
                final String[] title = new String[a];
                final String[] instructor = new String[a];
                final String[] types = new String[a];
                final String[] days = new String[a];
                final String[] starts = new String[a];
                final String[] stops = new String[a];

                int iterate = 0;
                for(int i = 16; i<allCourses.length;i+=14){
                    departments[iterate] = allCourses[i];
                    title[iterate] = allCourses[i+1];
                    types[iterate] = allCourses[i+4];
                    days[iterate] = allCourses[i+8];
                    starts[iterate] = allCourses[i+9];
                    stops[iterate] = allCourses[i+10];

                    if(departments[iterate] != null){
                        if(departments[iterate].length() > 2){
                            section[iterate] = (departments[iterate]).substring(departments[iterate].lastIndexOf(" ") - 2).trim();
                            colleges[iterate] = departments[iterate].substring(0,3).trim();
                            departments[iterate] = departments[iterate].substring(3,departments[iterate].lastIndexOf(" ") - 2).trim();
                            courseNumber[iterate] = departments[iterate].substring(3);
                            departments[iterate] = departments[iterate].substring(0,3);
                            title[iterate] = title[iterate].substring(0,title[iterate].length() - 1);
                            instructor[iterate] = title[iterate].substring(title[iterate].lastIndexOf(" "));
                            title[iterate] = title[iterate].substring(0,title[iterate].length() - instructor[iterate].length());
                        }
                        else {
                            section[iterate] = " ";
                            departments[iterate] = " ";
                            courseNumber[iterate] = " ";
                            colleges[iterate] = " ";
                            title[iterate] = " ";
                            instructor[iterate] = " ";
                        }

                    }
                    else{
                        section[iterate] = " ";
                        departments[iterate] = " ";
                        courseNumber[iterate] = " ";
                        colleges[iterate] = " ";
                        title[iterate] = " ";
                        instructor[iterate] = " ";
                    }

                    iterate++;
                }



                runOnUiThread(new Runnable() {

                                  @Override
                                  public void run() {
                                      /*classlist.setText(builder.toString());
                                      classlist.setText(" ");*/
                                      /*
                                      Course C1 = new Course("ENG", "EC", "327");
                                      Course.Lec c1lec = C1.new Lec();
                                      String LecProf = "Goyal";
                                      String LecSec = "A1";
                                      String LecDay = "MWF";
                                      String Start = "0";
                                      String Stop = "1000";
                                      c1lec.setLec(LecProf, LecSec, LecDay, Start, Stop);
                                      classlist.setText(C1.display());*/
                                      Course[] MCL = new Course[a];
                                      Course.Lec[] MCLlec = new Course.Lec[a];
                                      Course.Dis[] MCLdis = new Course.Dis[a];
                                      Course.Lab[] MCLlab = new Course.Lab[a];
                                      Course.PLab[] MCLplab = new Course.PLab[a];
                                      for (int i = 0; i < MCL.length; i++) {
                                          if (colleges[i] == null || departments[i] == null || courseNumber[i] == null ||
                                                  colleges[i] == " " || departments[i] == " " || courseNumber[i] == " ") {
                                              MCL[i] = new Course("CGS", "BS", "101");
                                          } else {
                                              MCL[i] = new Course(colleges[i], departments[i], courseNumber[i]);
                                          }
                                          /*
                                          int leccount = 0;
                                          int discount = 0;
                                          int labcount = 0;
                                          int plabcount = 0;

                                          if (types[i] == "Lecture") {
                                              MCLlec[leccount] = MCL[i].new Lec(instructor[i], section[i], days[i], starts[i], stops[i]);
                                              leccount++;
                                          } else if (types[i] == "Discussion") {
                                              MCLdis[discount] = MCL[i].new Dis(section[i], days[i], starts[i], stops[i]);
                                              discount++;
                                          } else if (types[i] == "Lab") {
                                              MCLlab[labcount] = MCL[i].new Lab(section[i], days[i], starts[i], stops[i]);
                                              labcount++;
                                          } else if (types[i] == "Independent" || types[i] == "Directed Study") {
                                              MCLplab[plabcount] = MCL[i].new PLab(section[i], days[i], starts[i], stops[i]);
                                              plabcount++;
                                          } else if (types[i] == null) {
                                              continue;
                                          }
                                          */

                                      }
                                      classlist.setText(" ");
                                      for(int j = 0; j<MCL.length;j++) {
                                          classlist.append(MCL[j].display());
                                      }
                                  }
                              });

                /*
                //
                        for (int i = 0; i < 100; i++) {
                            classlist.append("College: ");
                            classlist.append(colleges[i]);

                            classlist.append("Department: ");
                            classlist.append(departments[i]);

                            classlist.append("Course Number: ");
                            classlist.append(courseNumber[i]);

                            classlist.append("Section: ");
                            classlist.append(section[i]);

                            classlist.append(" Title: ");
                            classlist.append((title[i]));

                            classlist.append(" Instructor: ");
                            classlist.append((instructor[i]));

                            classlist.append("Types: ");
                            classlist.append(types[i]);

                            classlist.append("Days: ");
                            classlist.append(days[i]);

                            classlist.append("Start: ");
                            classlist.append(starts[i]);

                            classlist.append("Stop: ");
                            classlist.append(stops[i]);

                            classlist.append("\n\n");

                        }
                /*
                Course[] MCL = new Course[allCourses.length/13];
                Course.Lec[] MCLlec = new Course.Lec[a];
                Course.Dis[] MCLdis = new Course.Dis[a];
                Course.Lab[] MCLlab = new Course.Lab[a];
                Course.PLab[] MCLplab = new Course.PLab[a];

                for(int i = 0; i < 2;i++)
                {MCL[i].setCourse("CGS","BS","101");
                    if(colleges[i]==null||departments[i]==null||courseNumber[i]==null||
                            colleges[i]==" "||departments[i]==" "||courseNumber[i]==" ")
                    {MCL[i].setCourse("CGS","BS","101");}
                    else
                    {MCL[i].setCourse(colleges[i],departments[i],courseNumber[i]);}


                    int leccount = 0;
                    int discount = 0;
                    int labcount = 0;
                    int plabcount = 0;
                    if(types[i]=="Lecture"){
                        MCLlec[leccount]= MCL[i].new Lec();
                        MCLlec[leccount].setLec(instructor[i],section[i],days[i],starts[i],stops[i]);
                        leccount++;
                    }

                    else if(types[i]=="Discussion"){
                        MCLdis[discount]= MCL[i].new Dis();
                        MCLdis[discount].setDis(section[i],days[i],starts[i],stops[i]);
                        discount++;
                    }

                    else if(types[i]=="Lab"){
                        MCLlab[labcount]= MCL[i].new Lab();
                        MCLlab[labcount].setLab(section[i],days[i],starts[i],stops[i]);
                        labcount++;
                    }

                    else if(types[i]=="Independent"||types[i]=="Directed Study"){
                        MCLplab[plabcount]= MCL[i].new PLab();
                        MCLplab[plabcount].setPLab(section[i],days[i],starts[i],stops[i]);
                        plabcount++;
                    }
                    else if(types[i]==null){
                        continue;
                    }
                     */
                //}
                //classlist.setText(MCL[1].display(MCL[1]));
                //for(int j = 0;j<MCL.length;j++){
                  //  classlist.append(MCL[j].display(MCL[j]));
                //}

                progressBar.setVisibility((View.INVISIBLE));
            }
        }).start();
    }
}
