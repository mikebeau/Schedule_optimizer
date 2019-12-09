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
import java.lang.reflect.Array;
import java.util.ArrayList;

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
                                      Course[] MCL = new Course[200];
                                      Course teste = new Course("CGS", "BS", "101");
                                      //Course.Lec templec = MCL[0].new Lec("Dill","Z1","all ending with y","0","2359");
                                      ArrayList<String> LecProf = new ArrayList<String>();
                                      ArrayList<String> LecSec = new ArrayList<String>();
                                      ArrayList<String> LecDay = new ArrayList<String>();
                                      ArrayList<String> LecStart = new ArrayList<String>();
                                      ArrayList<String> LecStop = new ArrayList<String>();
                                      ArrayList<String> testlist = new ArrayList<String>();
                                      testlist.add("1");
                                      testlist.add("2");
                                      testlist.add("3");

                                      //MCLlec[0]=templec;
                                      ArrayList<Course.Lec> MCLlec = new ArrayList<Course.Lec>();
                                      ArrayList<Course.Lec> test = new ArrayList<Course.Lec>();
                                      test.add(teste.new Lec(testlist,testlist,testlist,testlist,testlist));
                                      //Course.Dis[] MCLdis = new Course.Dis[196];
                                      //Course.Lab[] MCLlab = new Course.Lab[196];
                                      // Course.PLab[] MCLplab = new Course.PLab[196];
                                      classlist.setText("In the beginning" + "\n");
                                      classlist.append(test.get(0).getLec());
                                      testlist.clear();
                                      testlist.add("4");
                                      testlist.add("5");
                                      testlist.add("6");
                                      test.add(teste.new Lec(testlist,testlist,testlist,testlist,testlist));
                                      classlist.append(test.get(0).getLec());

                                      int k = 0;
                                      for (int i = 2; i < 420; i++) {
                                          String s1 = courseNumber[i];
                                          String s2 = courseNumber[(i + 1)];
                                          if (types[i].contains("Lecture")) {
                                              LecProf.add(instructor[i]);
                                              LecSec.add(section[i]);
                                              LecDay.add(days[i]);
                                              LecStart.add(starts[i]);
                                              LecStop.add(stops[i]);
                                          }
                                          classlist.append(String.valueOf(LecProf.size()));
                                          if (colleges[i] == null || departments[i] == null || courseNumber[i] == null ||
                                                  colleges[i] == " " || departments[i] == " " || courseNumber[i] == " ") {
                                              MCL[k] = new Course("CGS", "BS", "101");
                                              MCLlec.add(MCL[k].new Lec(LecProf, LecSec, LecDay, LecStart, LecStop));
                                              LecProf.clear();
                                              LecSec.clear();
                                              LecDay.clear();
                                              LecStart.clear();
                                              LecStop.clear();
                                              classlist.append(String.valueOf(LecProf.size()));
                                              k++;
                                          } else if (!s1.contains(s2)) {
                                              MCL[k] = new Course(colleges[i], departments[i], courseNumber[i]);
                                              MCLlec.add(MCL[k].new Lec(LecProf, LecSec, LecDay, LecStart, LecStop));
                                              //classlist.append(MCLlec.get(k).toString());
                                              /*
                                              for (int b = 0; b < LecProf.size(); b++) {
                                                  classlist.append(LecProf.get(b) + " ");
                                                  classlist.append(LecSec.get(b) + " ");
                                                  classlist.append(LecDay.get(b) + " ");
                                                  classlist.append(LecStart.get(b) + " ");
                                                  classlist.append(LecStop.get(b) + "\n");
                                              }*/
                                                  LecProf.clear();
                                                  LecSec.clear();
                                                  LecDay.clear();
                                                  LecStart.clear();
                                                  LecStop.clear();
                                              classlist.append(String.valueOf(LecProf.size()));

                                              k++;
                                          }


                                          /*if (types[i].contains("Lecture")) {
                                              LecProf.add(instructor[i]);
                                              LecSec.add(section[i]);
                                              LecDay.add(days[i]);
                                              LecStart.add(starts[i]);
                                              LecStop.add(stops[i]);
                                          } else if (types[i] == "Discussion") {
                                              Course.Dis templec = MCL[i].new Dis(section[i], days[i], starts[i], stops[i]);
                                              MCLdis[discount] = templec;
                                              discount++;
                                          } else if (types[i] == "Lab") {
                                              Course.Lab templec = MCL[i].new Lab(section[i], days[i], starts[i], stops[i]);
                                              MCLlab[labcount] = templec;
                                              discount++;
                                          } else if (types[i] == "Independent" || types[i] == "Directed Study") {
                                              Course.PLab templec = MCL[i].new PLab(section[i], days[i], starts[i], stops[i]);
                                              MCLplab[plabcount] = templec;
                                              discount++;
                                          } else if (types[i] == null) {
                                              continue;
                                          }*/


                                      }

                                      //String s1 = courseNumber[1];
                                      //String s2 = courseNumber[2];
                                      classlist.append(String.valueOf(MCLlec.size()) + "\n");
                                      //classlist.append(courseNumber[2]);
                                      //classlist.append(courseNumber[3]);
                                      for (int j = 0; j < k; j++) {
                                          //classlist.append(String.valueOf(k));//MCL[j].display());

                                          //if (!s1.contains(s2)) {
                                          //  MCL[0] = new Course(colleges[1], departments[1], courseNumber[1]);
                                          classlist.append(MCL[j].display());
                                          //classlist.append(MCLlec.get(j).getLec());
                                          //classlist.append(courseNumber[0]);
                                          //classlist.append(courseNumber[1]);
                                          //classlist.append(courseNumber[2]);
                                          //classlist.append(courseNumber[3]);
                                          //classlist.append("\n");
                                          //} else
                                          //  classlist.append(" equal");//*/

                                          //classlist.append((Integer.valueOf(courseNumber[2])).toString());
                                          //classlist.append((Integer.valueOf(courseNumber[3])).toString());
                                          //classlist.append(MCL[j].getLec());
                                          //classlist.append(MCLdis[j].getDis());
                                          //classlist.append(MCLlab[j].getLab());
                                          //classlist.append(MCLplab[j].getPLab());
                                      }
                                  }
                              });


                /*
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

                        }*/

                progressBar.setVisibility((View.INVISIBLE));
            }
        }).start();
    }
}
