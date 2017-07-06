package com.example.aliosama.assignment.Database.Models;

import java.io.Serializable;

/**
 * Created by aliosama on 5/21/2017.
 */

public class TeacherModel implements Serializable{
    int id;
    String Name;
    String Phone;
    String Email;
    String Password;

    public TeacherModel(int id, String name, String phone, String email, String password) {
        this.id = id;
        Name = name;
        Phone = phone;
        Email = email;
        Password = password;
    }

    public TeacherModel(String name, String phone, String email, String password) {
        Name = name;
        Phone = phone;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
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
