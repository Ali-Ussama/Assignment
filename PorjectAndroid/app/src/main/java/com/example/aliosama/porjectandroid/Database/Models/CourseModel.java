package com.example.aliosama.porjectandroid.Database.Models;

import java.io.Serializable;

/**
 * Created by aliosama on 5/22/2017.
 */

public class CourseModel implements Serializable {
    int ID;
    String Name;
    String Point;
    String Semester;
    int Teacher_ID;

    public CourseModel(int ID, String name, String point, String semester,int teacher_ID) {
        this.ID = ID;
        Name = name;
        Point = point;
        Semester = semester;
        Teacher_ID = teacher_ID;
    }

    public CourseModel(String name, String point, String semester,int teacher_ID) {
        Name = name;
        Point = point;
        Semester = semester;
        Teacher_ID = teacher_ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPoint() {
        return Point;
    }

    public void setPoint(String point) {
        Point = point;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public int getTeacher_ID() {
        return Teacher_ID;
    }

    public void setTeacher_ID(int teacher_ID) {
        Teacher_ID = teacher_ID;
    }
}
