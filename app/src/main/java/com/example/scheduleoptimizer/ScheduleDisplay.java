package com.example.scheduleoptimizer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScheduleDisplay extends AppCompatActivity {

    final Course[] MCL = new Course[150];
    final ArrayList<Course.Lec> MCLlec = new ArrayList<Course.Lec>();
    final ArrayList<Course.Dis> MCLdis = new ArrayList<Course.Dis>();
    final ArrayList<Course.Lab> MCLlab = new ArrayList<Course.Lab>();
    final ArrayList<Course.PLab> MCLplab = new ArrayList<Course.PLab>();
    int[] iarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        importClasses();

        iarray = getIntent().getIntArrayExtra("iarray");





        FloatingActionButton fab = findViewById(R.id.save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Saving data Schedule", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                openActivityMain();

            }
        });
    }

    public void importClasses() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String delims = "[*|]+";
                final String[] allCourses;
                final StringBuilder builder = new StringBuilder();
                String urls[] = {"https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575734487?College=ENG&Dept=BE&Course=437&Section=A1&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd=",
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
                        "https://www.bu.edu/link/bin/uiscgi_studentlink.pl/1575740239?College=ENG&Dept=SE&Course=&Section=&ModuleName=univschr.pl&KeySem=20204&ViewSem=Spring+2020&SearchOptionCd=S&SearchOptionDesc=Class+Number&MainCampusInd="};

                for (String url : urls) {
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
                int a = allCourses.length / 13;
                final String[] departments = new String[a];
                final String[] courseNumber = new String[a];
                final String[] colleges = new String[a];
                final String[] section = new String[a];
                final String[] title = new String[a];
                final String[] instructor = new String[a];
                final String[] types = new String[a];
                final String[] days = new String[a];
                final String[] starts = new String[a];
                final String[] stops = new String[a];

                int iterate = 0;
                for (int i = 16; i < allCourses.length; i += 14) {
                    departments[iterate] = allCourses[i];
                    title[iterate] = allCourses[i + 1];
                    types[iterate] = allCourses[i + 4];
                    days[iterate] = allCourses[i + 8];
                    starts[iterate] = allCourses[i + 9];
                    stops[iterate] = allCourses[i + 10];

                    if (departments[iterate] != null) {
                        if (departments[iterate].length() > 2) {
                            section[iterate] = (departments[iterate]).substring(departments[iterate].lastIndexOf(" ") - 2).trim();
                            colleges[iterate] = departments[iterate].substring(0, 3).trim();
                            departments[iterate] = departments[iterate].substring(3, departments[iterate].lastIndexOf(" ") - 2).trim();
                            courseNumber[iterate] = departments[iterate].substring(3);
                            departments[iterate] = departments[iterate].substring(0, 3);
                            title[iterate] = title[iterate].substring(0, title[iterate].length() - 1);
                            instructor[iterate] = title[iterate].substring(title[iterate].lastIndexOf(" "));
                            title[iterate] = title[iterate].substring(0, title[iterate].length() - instructor[iterate].length());
                        } else {
                            section[iterate] = " ";
                            departments[iterate] = " ";
                            courseNumber[iterate] = " ";
                            colleges[iterate] = " ";
                            title[iterate] = " ";
                            instructor[iterate] = " ";
                        }

                    } else {
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
                        //Course[] MCL = new Course[200];
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

                        //ArrayList<Course.Lec> MCLlec = new ArrayList<Course.Lec>();
                        //ArrayList<Course.Dis> MCLdis = new ArrayList<Course.Dis>();
                        //ArrayList<Course.Lab> MCLlab = new ArrayList<Course.Lab>();
                        //ArrayList<Course.PLab> MCLplab = new ArrayList<Course.PLab>();

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
                            } else if (types[i].contains("Discussion")) {
                                DisSec.add(section[i]);
                                DisDay.add(days[i]);
                                DisStart.add(starts[i]);
                                DisStop.add(stops[i]);
                            } else if (types[i].contains("Lab")) {
                                LabSec.add(section[i]);
                                LabDay.add(days[i]);
                                LabStart.add(starts[i]);
                                LabStop.add(stops[i]);
                            } else {
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

                            } else if (!s1.contains(s2)) {
                                MCL[k] = new Course(colleges[i], departments[i], courseNumber[i]);
                                MCLlec.add(MCL[k].new Lec((ArrayList<String>) LecProf.clone(), (ArrayList<String>) LecSec.clone(), (ArrayList<String>) LecDay.clone(), (ArrayList<String>) LecStart.clone(), (ArrayList<String>) LecStop.clone()));
                                MCLdis.add(MCL[k].new Dis((ArrayList<String>) DisSec.clone(), (ArrayList<String>) DisDay.clone(), (ArrayList<String>) DisStart.clone(), (ArrayList<String>) DisStop.clone()));
                                MCLlab.add(MCL[k].new Lab((ArrayList<String>) LabSec.clone(), (ArrayList<String>) LabDay.clone(), (ArrayList<String>) LabStart.clone(), (ArrayList<String>) LabStop.clone()));
                                MCLplab.add(MCL[k].new PLab((ArrayList<String>) PLabSec.clone(), (ArrayList<String>) PLabDay.clone(), (ArrayList<String>) PLabStart.clone(), (ArrayList<String>) PLabStop.clone()));

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

                        ArrayList<Integer> coursearray = new ArrayList<Integer>();
                        ArrayList<Integer> specificlecarray = new ArrayList<Integer>();
                        int jval=0;
                        boolean validlec=true;
                        for(int i = 1; i<5;i++)
                        {
                            for(int j=0; j<MCLlec.get(iarray[i]).Sec.size();j++)
                            {
                                if(MCLlec.get(iarray[0]).Start.get(0)== MCLlec.get(iarray[i]).Start.get(j))
                                {
                                    validlec=false;
                                }
                                jval=j;
                            }
                            if(validlec)
                                coursearray.add(0);
                            coursearray.add(jval);
                        }

                        // get Schedule and display classes
                        /*
                        String[] start_time_array = {"9:05am", "6:30pm", "2:30pm", "12:20pm", "2:30pm", "10:10am", "4:40pm", "9:00am", "10:10am", "1:30pm"};
                        String[] end_time_array = {"9:55am", "9:15pm", "4:15pm", "1:10pm", "4:15pm", "11:55am", "5:30pm", "10:45am", "11:55am", "3:15"};
                        String[] color_array = {"#ff0000", "#ffff00", "#80ff00", "#00ffff", "#0040ff", "#ff00ff", "#d966ff", "#00ffff", "#ff668c", "#fa53af", "#ff00ff", "#ff00ff", "#ff00ff", "#ff00ff"};
                        String[] class_name_array = {"BE 492", "BE 492", "EC410", "EC410", "EC 410", "EK424", "EK424", "EK481", "EK481", "EC500"};
                        String[] day_array = {"Mon", "Mon", "Mon,Wed", "Fri", "Fri", "Mon,Wed", "Fri", "Tue,Thu", "Fri", "Tue,Thu"};*/


                        // Initializing string lists
                        List<String> start_time_list=new ArrayList<>();
                        List<String> end_time_list=new ArrayList<>();
                        List<String> class_name_list=new ArrayList<>();
                        List<String> day_list=new ArrayList<>();

                        //Initializing courses that don't confict list
                        boolean validSchedule = true;

                        //Looping through to see if any classed confict
                        for(int classCurrent = 0; classCurrent < iarray.length; classCurrent++){
                            int numComparison = 0;
                            int numConflict = 0;
                            for(int classCompare = 0; classCompare < iarray.length; classCompare++){
                                if (MCLlec.get(iarray[classCurrent]).getStart().size() != 0 && MCLlec.get(iarray[classCompare]).getStart().size() != 0) {
                                    String start_time_current = MCLlec.get(iarray[classCurrent]).Start.get(0).trim();
                                    String end_time_current = MCLlec.get(iarray[classCurrent]).Stop.get(0).trim();
                                    String start_time_compare = MCLlec.get(iarray[classCompare]).Start.get(0).trim();
                                    String end_time_compare = MCLlec.get(iarray[classCompare]).Stop.get(0).trim();
                                    String day_string_current = MCLlec.get(iarray[classCurrent]).Day.get(0);
                                    String day_string_compare = MCLlec.get(iarray[classCompare]).Day.get(0);

                                    //Parse start_time string
                                    String delims = "[:]+";
                                    String[] start_string_current;
                                    start_string_current = start_time_current.split(delims);

                                    String[] end_string_current;
                                    end_string_current = end_time_current.split(delims);

                                    String[] start_string_compare;
                                    start_string_compare = start_time_current.split(delims);

                                    String[] end_string_compare;
                                    end_string_compare = end_time_current.split(delims);


                                    int ending_time_current;
                                    int begin_time_current;
                                    int begin_time_compare;
                                    int ending_time_compare;
                                    String am_string = "am";

                                    if (start_time_current.contains(am_string) || start_string_current[0].contains("12")) {
                                        begin_time_current = Integer.valueOf(start_string_current[0]) * 60 + Integer.valueOf(start_string_current[1].substring(0, 2)) - 7 * 60;
                                    } else {
                                        begin_time_current = Integer.valueOf(start_string_current[0]) * 60 + Integer.valueOf(start_string_current[1].substring(0, 2)) - 7 * 60 + 12 * 60;
                                    }

                                    if (end_time_current.contains(am_string) || end_string_current[0].contains("12")) {
                                        ending_time_current = Integer.valueOf(end_string_current[0]) * 60 + Integer.valueOf(end_string_current[1].substring(0, 2)) - 7 * 60;
                                    } else {
                                        ending_time_current = Integer.valueOf(end_string_current[0]) * 60 + Integer.valueOf(end_string_current[1].substring(0, 2)) - 7 * 60 + 12 * 60;
                                    }


                                    if (start_time_compare.contains(am_string) || start_string_compare[0].contains("12")) {
                                        begin_time_compare = Integer.valueOf(start_string_compare[0]) * 60 + Integer.valueOf(start_string_compare[1].substring(0, 2)) - 7 * 60;
                                    } else {
                                        begin_time_compare = Integer.valueOf(start_string_compare[0]) * 60 + Integer.valueOf(start_string_compare[1].substring(0, 2)) - 7 * 60 + 12 * 60;
                                    }

                                    if (end_time_compare.contains(am_string) || end_string_compare[0].contains("12")) {
                                        ending_time_compare = Integer.valueOf(end_string_compare[0]) * 60 + Integer.valueOf(end_string_compare[1].substring(0, 2)) - 7 * 60;
                                    } else {
                                        ending_time_compare = Integer.valueOf(end_string_compare[0]) * 60 + Integer.valueOf(end_string_compare[1].substring(0, 2)) - 7 * 60 + 12 * 60;
                                    }

                                    numComparison++;
                                    if((begin_time_current <= ending_time_compare && begin_time_current >= begin_time_compare )||(ending_time_current >= ending_time_compare && ending_time_current <= begin_time_compare ) || (begin_time_current >= begin_time_compare && ending_time_compare >= ending_time_current)){
                                        numConflict++;
                                    }


                                }

                            }

                            if(numComparison == numConflict){
                                validSchedule = false;
                            }
                        }// end of validSchedule for loop

                        if(!validSchedule){
                            TextView itran;
                            itran= findViewById(R.id.sampletest);
                            itran.setText("Courses Conflict");
                        }


                        // Appending lists with new classes
                        for(int classnum = 0; classnum < iarray.length; classnum++){
                            if (MCLlec.get(iarray[classnum]).getStart().size()!=0) {
                                String start_time_var = MCLlec.get(iarray[classnum]).Start.get(0).trim();
                                String end_time_var = MCLlec.get(iarray[classnum]).Stop.get(0).trim();

                                start_time_list.add(start_time_var);
                                end_time_list.add(end_time_var);
                                class_name_list.add(MCL[iarray[classnum]].display());
                                day_list.add(MCLlec.get(iarray[classnum]).Day.get(0));
                            }

                            if (MCLdis.get(iarray[classnum]).getStart().size()!=0|| MCLdis.get(iarray[classnum]).getStop().size()!=0) {
                                String start_time_var = MCLdis.get(iarray[classnum]).getStart().get(0).trim();
                                String end_time_var = MCLdis.get(iarray[classnum]).getStop().get(0).trim();

                                start_time_list.add(start_time_var);
                                end_time_list.add(end_time_var);
                                class_name_list.add(MCL[iarray[classnum]].display());
                                day_list.add(MCLdis.get(iarray[classnum]).Day.get(0));
                            }

                            if (MCLlab.get(iarray[classnum]).getStart().size()!=0 || MCLlab.get(iarray[classnum]).getStop().size()!=0) {
                                String start_time_var = MCLlab.get(iarray[classnum]).getStart().get(0).trim();
                                String end_time_var = MCLlab.get(iarray[classnum]).getStop().get(0).trim();

                                start_time_list.add(start_time_var);
                                end_time_list.add(end_time_var);
                                class_name_list.add(MCL[iarray[classnum]].display());
                                day_list.add(MCLlab.get(iarray[classnum]).Day.get(0));
                            }




                        }




                        int n = start_time_list.size();
                        String[] start_time_array = new String[n];
                        String[] end_time_array = new String[n];
                        String[] color_array = {"#ff0000", "#ffff00", "#80ff00", "#00ffff", "#0040ff", "#ff00ff", "#d966ff", "#00ffff", "#ff668c", "#fa53af", "#ff00ff", "#ff00ff", "#ff00ff", "#ff00ff"};
                        String[] class_name_array = new String[n];
                        String[] day_array = new String[n];

                        for(int coursei = 0; coursei<n; coursei++){
                            start_time_array[coursei] = start_time_list.get(coursei);
                            end_time_array[coursei] = end_time_list.get(coursei);
                            class_name_array[coursei] = class_name_list.get(coursei);
                            day_array[coursei] = day_list.get(coursei);
                        }

                        if(validSchedule) {
                            for (int i = 0; i < start_time_array.length; i++) {
                                String delims = "[,]+";
                                String[] day_string;
                                day_string = day_array[i].split(delims);
                                for (int j = 0; j < day_string.length; j++) {
                                    plotClass(day_string[j], start_time_array[i], end_time_array[i], color_array[i], class_name_array[i]);
                                }

                            }
                        }


                    }
                });
            }
        }).start();
    }

    public void plotClass(String day, String start_time, String  end_time, String color, String class_name) {
        TextView classnum = new TextView(this);
        TypedValue dimention = new TypedValue();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout mondaySchedule = findViewById(R.id.mondayRelativeLayout);
        RelativeLayout tuesdaySchedule = findViewById((R.id.tuesdayRelativeLayout));
        RelativeLayout wednesdaySchedule = findViewById(R.id.wednesdayRelativeLayout);
        RelativeLayout thursdaySchedule = findViewById((R.id.thursdayRelativeLayout));
        RelativeLayout fridaySchedule = findViewById(R.id.fridayRelativeLayout);

        //Parse start_time string
        String delims = "[:]+";
        String[] start_string;
        start_string = start_time.split(delims);

        String[] end_string;
        end_string = end_time.split(delims);

        int ending_time;
        int begin_time;
        String am_string = "am";

        if(end_time.contains(am_string)|| end_string[0].contains("12")) {
            ending_time = Integer.valueOf(end_string[0]) * 60 + Integer.valueOf(end_string[1].substring(0,2)) - 7 * 60;
        }
        else{
            ending_time = Integer.valueOf(end_string[0]) * 60 + Integer.valueOf(end_string[1].substring(0,2)) - 7 * 60 + 12*60;
        }

        if(start_time.contains(am_string) || start_string[0].contains("12")) {
            begin_time = Integer.valueOf(start_string[0]) * 60 + Integer.valueOf(start_string[1].substring(0,2)) - 7 * 60;
        }
        else{
            begin_time = Integer.valueOf(start_string[0]) * 60 + Integer.valueOf(start_string[1].substring(0,2)) - 7 * 60 + 12*60;
        }


        int class_duration = ending_time - begin_time;

        //set class width and height
        classnum.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        classnum.setHeight((int) dimention.applyDimension(dimention.COMPLEX_UNIT_DIP, class_duration, getResources().getDisplayMetrics()));
        classnum.setText(class_name);
        classnum.setBackgroundColor(Color.parseColor(color));
        classnum.setGravity(Gravity.CENTER);
        classnum.setPaddingRelative(0,0,0,0);
        params.setMargins(0,(int) dimention.applyDimension(dimention.COMPLEX_UNIT_DIP, begin_time, getResources().getDisplayMetrics()),0,0);
        classnum.setLayoutParams(params);

        //define what day the class is

        if (day.contains("Mon")) {
            mondaySchedule.addView(classnum);
        } else if (day.contains("Tue")) {
            tuesdaySchedule.addView(classnum);
        } else if (day.contains("Wed")) {
            wednesdaySchedule.addView(classnum);
        } else if (day.contains("Thu")) {
            thursdaySchedule.addView(classnum);
        } else if (day.contains("Fri")) {
            fridaySchedule.addView(classnum);
        }



    }
    public void openActivityMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
