package com.example.aliosama.contacts.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;

/**
 * Created by aliosama on 5/3/2017.
 */

public class ContactModel implements Serializable{
    int id;
    String Name;
    String phone;
    byte[] ContactImage;

    public ContactModel(String name, String phone, byte[] contactImage) {
        Name = name;
        this.phone = phone;
        this.ContactImage = contactImage;
    }

    public ContactModel(int id, String name, String phone, byte[] contactImage) {
        this.id = id;
        this.Name = name;
        this.phone = phone;
        this.ContactImage = contactImage;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getContactImage() {
        return this.ContactImage;
    }

    public void setContactImage(byte[] contactImage) {
        this.ContactImage = contactImage;
    }
}
