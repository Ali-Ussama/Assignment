package com.example.aliosama.assignment.Database.Models;

import java.io.Serializable;

/**
 * Created by aliosama on 5/22/2017.
 */

public class CourseModel implements Serializable {
    int ID;
    String Name;
    String Point;
    String Semester;
    String Description;
    int Teacher_ID;

    public CourseModel(int ID,String name, String point, String semester,String description,int teacher_ID) {
        this.ID = ID;
        Name = name;
        Point = point;
        Semester = semester;
        Teacher_ID = teacher_ID;
        Description = description;
    }

    public CourseModel(String name, String point, String semester,String description,int teacher_ID) {
        Name = name;
        Point = point;
        Semester = semester;
        Description = description;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getTeacher_ID() {
        return Teacher_ID;
    }

    public void setTeacher_ID(int teacher_ID) {
        Teacher_ID = teacher_ID;
    }

}
