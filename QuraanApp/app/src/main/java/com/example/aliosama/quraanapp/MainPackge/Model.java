package com.example.aliosama.quraanapp.MainPackge;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by aliosama on 4/23/2017.
 */

public class Model {
    int SoraImage;
    int SoraAudio;
    String ItemTitle;
    String ItemNumber;

    public Model(int soraImage, int soraAudio, String itemTitle, String itemNumber) {
        SoraImage = soraImage;
        SoraAudio = soraAudio;
        ItemTitle = itemTitle;
        ItemNumber = itemNumber;
    }



    public int getSoraAudio() {
        return SoraAudio;
    }

    public void setSoraAudio(int soraAudio) {
        SoraAudio = soraAudio;
    }

    public String getItemTitle() {
        return this.ItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.ItemTitle = itemTitle;
    }

    public String getItemNumber() {
        return this.ItemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.ItemNumber = itemNumber;
    }

    public int getSoraImage() {
        return SoraImage;
    }

    public void setSoraImage(int soraImage) {
        SoraImage = soraImage;
    }
}
