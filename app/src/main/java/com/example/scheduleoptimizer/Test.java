package com.example.scheduleoptimizer;

import android.app.Application;

import java.util.ArrayList;

public class Test extends Application {


    private  Course[] courses= new Course[150];
    private  ArrayList<Course.Lec> lectures = new ArrayList<Course.Lec>();
    private  ArrayList<Course.Dis> discussions = new ArrayList<Course.Dis>();
    private  ArrayList<Course.Lab> labs = new ArrayList<Course.Lab>();
    private  ArrayList<Course.PLab> plabs = new ArrayList<Course.PLab>();


    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public ArrayList<Course.Lec> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Course.Lec> lectures) {
        this.lectures = lectures;
    }

    public ArrayList<Course.Dis> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(ArrayList<Course.Dis> discussions) {
        this.discussions = discussions;
    }

    public ArrayList<Course.Lab> getLabs() {
        return labs;
    }

    public void setLabs(ArrayList<Course.Lab> labs) {
        this.labs = labs;
    }

    public ArrayList<Course.PLab> getPlabs() {
        return plabs;
    }

    public void setPlabs(ArrayList<Course.PLab> plabs) {
        this.plabs = plabs;
    }
}
