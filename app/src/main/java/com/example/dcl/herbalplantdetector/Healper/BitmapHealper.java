package com.example.dcl.herbalplantdetector.Healper;

import android.graphics.Bitmap;

public class BitmapHealper {
    private Bitmap bitmap = null;
    private static final BitmapHealper instance = new BitmapHealper();

    public BitmapHealper() {

    }

    public static BitmapHealper getInstance() {
        return instance;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
