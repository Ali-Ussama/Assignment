package com.example.aliosama.assignment.Database.Models;

/**
 * Created by aliosama on 5/21/2017.
 */

public class StudentModel {

    int id;
    int ImageResources;
    byte[] ImageResource;
    String Department;
    String Section;
    String Name;
    String Email;
    String Password;

    public StudentModel(int id, byte[] imageResource, String department, String section, String name, String email, String password) {
        this.id = id;
        ImageResource = imageResource;
        Department = department;
        Section = section;
        Name = name;
        Email = email;
        Password = password;
    }

    public StudentModel(byte[] imageResource, String department, String section, String name, String email, String password) {
        ImageResource = imageResource;
        Department = department;
        Section = section;
        Name = name;
        Email = email;
        Password = password;
    }
    // Temporary
    public StudentModel(int imageResources, String department, String section, String name, String email, String password) {
        ImageResources = imageResources;
        Department = department;
        Section = section;
        Name = name;
        Email = email;
        Password = password;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageResource() {
        return ImageResource;
    }

    public void setImageResource(byte[] imageResource) {
        ImageResource = imageResource;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getImageResources() {
        return ImageResources;
    }

    public void setImageResources(int imageResources) {
        ImageResources = imageResources;
    }
}
