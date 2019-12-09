package com.example.scheduleoptimizer;


import java.util.ArrayList;

public class Course {

        String College;
        String Dept;
        String coursenum;

        public Course(String col, String dpt, String num) {
            this.College = col;
            this.Dept = dpt;
            this.coursenum = num;
        }

        class Lec {
            ArrayList<String> Prof;
            ArrayList<String> Sec;
            ArrayList<String> Day;
            ArrayList<String> Start;
            ArrayList<String> Stop;

            public Lec(ArrayList<String> prof, ArrayList<String> sec, ArrayList<String> day, ArrayList<String> start, ArrayList<String> stop) {
                Prof = prof;
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
            public String getLec(){
                String returnstring ="";
                for (int i=0;i<Prof.size();i++) {
                    returnstring = returnstring +"Professor "+ Prof.get(i) + " Lecture Section: " + Sec.get(i)
                            + ", " + Day.get(i) + " from " + Start.get(i) + " to " + Stop.get(i) + "\n";
                }
                return  returnstring;
            }
        }

        class Dis {
            String Sec;
            String Day;
            String Start;
            String Stop;

            public Dis(String sec, String day, String start, String stop) {
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
            public String getDis(){
                String returnstring =" ";
                if(Sec == null || Day == null||Start == null|| Stop == null)
                    return returnstring;
                returnstring = returnstring+ "Discussion Section: "+Sec
                        + ", " + Day+" from " + Start + " to " + Stop+ "\n";
                return  returnstring;
            }
        }

        class Lab {
            String Sec;
            String Day;
            String Start;
            String Stop;

            public Lab(String sec, String day, String start, String stop) {
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
            public String getLab(){
                String returnstring =" ";
                    returnstring = returnstring + display()+ "Discussion Section: "+Sec
                            + ", " + Day+" from " + Start + " to " + Stop+ "\n";
                return  returnstring;
            }
        }

        class PLab {
            String Sec;
            String Day;
            String Start;
            String Stop;

            public PLab(String sec, String day, String start, String stop) {
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
            public String getPLab(){
                String returnstring =" ";
                returnstring = returnstring+ "Discussion Section: "+Sec
                        + ", " + Day+" from " + Start + " to " + Stop+ "\n";
                return  returnstring;
            }
        }
        public String  display(){
            String returnstring = College+" "+Dept+" "+coursenum + "\n";
            return returnstring;
        }
    }
