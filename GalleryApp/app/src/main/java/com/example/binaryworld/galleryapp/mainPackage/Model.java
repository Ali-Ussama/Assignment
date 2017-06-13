package com.example.binaryworld.galleryapp.mainPackage;

import java.io.Serializable;

/**
 * Created by BinaryWorld on 28-Feb-17.
 */
public class Model implements Serializable {

    private String img_med;
    private String img_large;
    private String name;
    private String date;

    public Model(String name,String img_med,String img_large, String date) {
        this.img_med = img_med;
        this.img_large=img_large;
        this.name = name;
        this.date = date;
    }

    public void setImg_med(String img_med) {
        this.img_med = img_med;
    }

    public void setImg_large(String img_large) {
        this.img_large = img_large;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg_med() {
        return img_med;
    }

    public String getImg_large() {
        return img_large;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
