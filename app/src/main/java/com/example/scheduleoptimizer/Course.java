package com.example.scheduleoptimizer;

import java.util.ArrayList;

public class Course {

        public String College;
        public String Dept;
        public String coursenum;

        public Course(String col, String dpt, String num) {
            this.College = col;
            this.Dept = dpt;
            this.coursenum = num;
        }

        public  class Lec {
            ArrayList<String> Prof;
            ArrayList<String> Sec;
            ArrayList<String> Day;
            ArrayList<String> Start;
            ArrayList<String> Stop;

            public Lec(ArrayList<String> prof, ArrayList<String> sec, ArrayList<String> day, ArrayList<String> start, ArrayList<String> stop) {
                this.Prof = prof;
                this.Sec = sec;
                this.Day = day;
                this.Start = start;
                this.Stop = stop;
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

        public class Dis {
            ArrayList<String> Sec;
            ArrayList<String> Day;
            ArrayList<String> Start;
            ArrayList<String> Stop;

            public Dis(ArrayList<String> sec, ArrayList<String> day, ArrayList<String> start, ArrayList<String> stop) {
                this.Sec = sec;
                this.Day = day;
                this.Start = start;
                this.Stop = stop;
            }
            public String getDis(){
                String returnstring ="";
                for (int i=0;i<Sec.size();i++) {
                    returnstring = returnstring + " Discussion Section: " + Sec.get(i)
                            + ", " + Day.get(i) + " from " + Start.get(i) + " to " + Stop.get(i) + "\n";
                }
                return  returnstring;
            }
        }

        public class Lab {
            ArrayList<String> Sec;
            ArrayList<String> Day;
            ArrayList<String> Start;
            ArrayList<String> Stop;

            public Lab(ArrayList<String> sec, ArrayList<String> day, ArrayList<String> start, ArrayList<String> stop) {
                this.Sec = sec;
                this.Day = day;
                this.Start = start;
                this.Stop = stop;
            }
            public String getLab(){
                String returnstring ="";
                for (int i=0;i<Sec.size();i++) {
                    returnstring = returnstring + " Lab Section: " + Sec.get(i)
                            + ", " + Day.get(i) + " from " + Start.get(i) + " to " + Stop.get(i) + "\n";
                }
                return  returnstring;
            }
        }

        public class PLab {
            ArrayList<String> Sec;
            ArrayList<String> Day;
            ArrayList<String> Start;
            ArrayList<String> Stop;

            public PLab(ArrayList<String> sec, ArrayList<String> day, ArrayList<String> start, ArrayList<String> stop) {
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
            public String getPLab(){
                String returnstring ="";
                for (int i=0;i<Sec.size();i++) {
                    returnstring = returnstring + " Independent Section: " + Sec.get(i)
                            + ", " + Day.get(i) + " from " + Start.get(i) + " to " + Stop.get(i) + "\n";
                }
                return  returnstring;
            }
        }
        public String  display(){
            String returnstring = College+" "+Dept+" "+coursenum + "\n";
            return returnstring;
        }
    }
