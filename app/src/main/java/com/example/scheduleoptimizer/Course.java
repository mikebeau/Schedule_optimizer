package com.example.scheduleoptimizer;

public class Course {
        String College;
        String Dept;
        int coursenum;

        public void setCourse(String col, String dpt, int num) {
            this.College = col;
            this.Dept = dpt;
            this.coursenum = num;
        }

        class Lec {
            String Prof[];
            String Sec[];
            String Day[];
            int Start[];
            int Stop[];

            public void setLec(String[] prof, String[] sec, String[] day, int[] start, int[] stop) {
                Prof = prof;
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
        }

        class Dis {
            String Sec[];
            String Day[];
            int Start[];
            int Stop[];

            public void setDis(String[] sec, String[] day, int[] start, int[] stop) {
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
        }

        class Lab {
            String Sec[];
            String Day[];
            int Start[];
            int Stop[];

            public void setLab(String[] sec, String[] day, int[] start, int[] stop) {
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
        }

        class PLab {
            String Sec[];
            String Day[];
            int Start[];
            int Stop[];

            public void setPLab(String[] sec, String[] day, int[] start, int[] stop) {
                Sec = sec;
                Day = day;
                Start = start;
                Stop = stop;
            }
        }
    }
