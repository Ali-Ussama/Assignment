package com.example.aliosama.porjectandroid.Database.Models;

/**
 * Created by aliosama on 5/21/2017.
 */

public class StudentModel {

    int id;
    String Name;
    String Email;
    String Password;

    public StudentModel(int id, String name, String email, String password) {
        this.id = id;
        Name = name;
        Email = email;
        Password = password;
    }

    public StudentModel(String name, String email, String password) {
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
}
