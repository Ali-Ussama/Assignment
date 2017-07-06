package com.example.aliosama.assignment.Database.Models;

import java.io.Serializable;

/**
 * Created by aliosama on 5/23/2017.
 */

public class AssignmentModel implements Serializable {
    int id;
    String Name;
    String Description;
    byte[] Content;
    String TypeOfWork;
    String StartTime;
    String EndTime;
    int Course_ID;

    public AssignmentModel(int id, String name, String description, byte[] content, String typeOfWork, String startTime, String endTime, int course_ID) {
        this.id = id;
        Name = name;
        Description = description;
        Content = content;
        TypeOfWork = typeOfWork;
        StartTime = startTime;
        EndTime = endTime;
        Course_ID = course_ID;
    }

    public AssignmentModel(String name, String description, byte[] content, String typeOfWork, String startTime, String endTime, int course_ID) {
        Name = name;
        Description = description;
        Content = content;
        TypeOfWork = typeOfWork;
        StartTime = startTime;
        EndTime = endTime;
        Course_ID = course_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public byte[] getContent() {
        return Content;
    }

    public void setContent(byte[] content) {
        Content = content;
    }

    public String getTypeOfWork() {
        return TypeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        TypeOfWork = typeOfWork;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(int course_ID) {
        Course_ID = course_ID;
    }
}
