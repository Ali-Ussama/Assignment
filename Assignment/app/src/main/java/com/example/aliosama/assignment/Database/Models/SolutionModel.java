package com.example.aliosama.assignment.Database.Models;

import java.io.Serializable;

/**
 * Created by aliosama on 5/23/2017.
 */

public class SolutionModel implements Serializable {
    int ID;
    byte[] Content;
    int Assignment_ID;
    int Student_ID;

    public SolutionModel(int ID, byte[] content, int assignment_ID,int student_id) {
        this.ID = ID;
        Content = content;
        Assignment_ID = assignment_ID;
        Student_ID = student_id;
    }

    public SolutionModel(byte[] content, int assignment_ID,int student_id) {
        Content = content;
        Assignment_ID = assignment_ID;
        Student_ID = student_id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public byte[] getContent() {
        return Content;
    }

    public void setContent(byte[] content) {
        Content = content;
    }

    public int getAssignment_ID() {
        return Assignment_ID;
    }

    public void setAssignment_ID(int assignment_ID) {
        Assignment_ID = assignment_ID;
    }

    public int getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(int student_ID) {
        Student_ID = student_ID;
    }
}

