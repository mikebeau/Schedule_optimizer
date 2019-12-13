package com.example.scheduleoptimizer;

import java.io.Serializable;
import java.util.ArrayList;
import android.app.Application;

public class Course implements Serializable {

        public String College;
        public String Dept;
        public String coursenum;

        public Course(String col, String dpt, String num) {
            this.College = col;
            this.Dept = dpt;
            this.coursenum = num;
        }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public String getCoursenum() {
        return coursenum;
    }

    public void setCoursenum(String coursenum) {
        this.coursenum = coursenum;
    }

    public  class Lec implements Serializable{
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

        public class Dis implements Serializable{
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

        public class Lab implements Serializable{
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

        public class PLab implements Serializable{
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
