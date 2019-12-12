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
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button openInfo;
    private Button openSavedSchedule;
    private Button testButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Import information from bu student link;
        importClasses();

        // Test button
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


    //Get all classes code
    public void importClasses(){
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
                int a = allCourses.length/13;
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
                        Course[] MCL = new Course[200];

                        ArrayList<String> LecProf = new ArrayList<String>();
                        ArrayList<String> LecSec = new ArrayList<String>();
                        ArrayList<String> LecDay = new ArrayList<String>();
                        ArrayList<String> LecStart = new ArrayList<String>();
                        ArrayList<String> LecStop = new ArrayList<String>();

                        ArrayList<String> DisSec = new ArrayList<String>();
                        ArrayList<String> DisDay = new ArrayList<String>();
                        ArrayList<String> DisStart = new ArrayList<String>();
                        ArrayList<String> DisStop = new ArrayList<String>();

                        ArrayList<String> LabSec = new ArrayList<String>();
                        ArrayList<String> LabDay = new ArrayList<String>();
                        ArrayList<String> LabStart = new ArrayList<String>();
                        ArrayList<String> LabStop = new ArrayList<String>();

                        ArrayList<String> PLabSec = new ArrayList<String>();
                        ArrayList<String> PLabDay = new ArrayList<String>();
                        ArrayList<String> PLabStart = new ArrayList<String>();
                        ArrayList<String> PLabStop = new ArrayList<String>();

                        ArrayList<Course.Lec> MCLlec = new ArrayList<Course.Lec>();
                        ArrayList<Course.Dis> MCLdis = new ArrayList<Course.Dis>();
                        ArrayList<Course.Lab> MCLlab = new ArrayList<Course.Lab>();
                        ArrayList<Course.PLab> MCLplab = new ArrayList<Course.PLab>();

                        int k = 0;
                        for (int i = 0; i < 420; i++) {
                            String s1 = courseNumber[i];
                            String s2 = courseNumber[(i + 1)];
                            if (types[i].contains("Lecture")) {
                                LecProf.add(instructor[i]);
                                LecSec.add(section[i]);
                                LecDay.add(days[i]);
                                LecStart.add(starts[i]);
                                LecStop.add(stops[i]);
                            }
                            else if (types[i].contains("Discussion")) {
                                DisSec.add(section[i]);
                                DisDay.add(days[i]);
                                DisStart.add(starts[i]);
                                DisStop.add(stops[i]);
                            }
                            else if (types[i].contains("Lab")) {
                                LabSec.add(section[i]);
                                LabDay.add(days[i]);
                                LabStart.add(starts[i]);
                                LabStop.add(stops[i]);
                            }
                            else{
                                PLabSec.add(section[i]);
                                PLabDay.add(days[i]);
                                PLabStart.add(starts[i]);
                                PLabStop.add(stops[i]);
                            }

                            if (colleges[i] == null || departments[i] == null || courseNumber[i] == null ||
                                    colleges[i] == " " || departments[i] == " " || courseNumber[i] == " ") {

                                LecProf.clear();
                                LecSec.clear();
                                LecDay.clear();
                                LecStart.clear();
                                LecStop.clear();
                                DisSec.clear();
                                DisDay.clear();
                                DisStart.clear();
                                DisStop.clear();
                                LabSec.clear();
                                LabDay.clear();
                                LabStart.clear();
                                LabStop.clear();
                                PLabSec.clear();
                                PLabDay.clear();
                                PLabStart.clear();
                                PLabStop.clear();


                                //k++;
                            } else if (!s1.contains(s2)) {
                                MCL[k] = new Course(colleges[i], departments[i], courseNumber[i]);
                                MCLlec.add(MCL[k].new Lec((ArrayList<String>)LecProf.clone(), (ArrayList<String>)LecSec.clone(), (ArrayList<String>)LecDay.clone(), (ArrayList<String>)LecStart.clone(), (ArrayList<String>)LecStop.clone()));
                                MCLdis.add(MCL[k].new Dis((ArrayList<String>)DisSec.clone(),(ArrayList<String>)DisDay.clone(),(ArrayList<String>)DisStart.clone(),(ArrayList<String>)DisStop.clone()));
                                MCLlab.add(MCL[k].new Lab((ArrayList<String>)LabSec.clone(),(ArrayList<String>)LabDay.clone(),(ArrayList<String>)LabStart.clone(),(ArrayList<String>)LabStop.clone()));
                                MCLplab.add(MCL[k].new PLab((ArrayList<String>)PLabSec.clone(),(ArrayList<String>)PLabDay.clone(),(ArrayList<String>)PLabStart.clone(),(ArrayList<String>)PLabStop.clone()));
                                /*
                                classlist.append(MCL[k].display());
                                classlist.append(MCLlec.get(k).getLec());
                                classlist.append(MCLdis.get(k).getDis());
                                classlist.append(MCLlab.get(k).getLab());
                                classlist.append(MCLplab.get(k).getPLab());*/

                                LecProf.clear();
                                LecSec.clear();
                                LecDay.clear();
                                LecStart.clear();
                                LecStop.clear();
                                DisSec.clear();
                                DisDay.clear();
                                DisStart.clear();
                                DisStop.clear();
                                LabSec.clear();
                                LabDay.clear();
                                LabStart.clear();
                                LabStop.clear();
                                PLabSec.clear();
                                PLabDay.clear();
                                PLabStart.clear();
                                PLabStop.clear();

                                k++;
                            }
                        }
                    }
                });
            }
        }).start();
    }



}
