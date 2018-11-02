package com.example.dcl.herbalplantdetector;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.dcl.herbalplantdetector.Healper.BitmapHealper;

public class ViewImage extends AppCompatActivity {

    private ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);


        ImageView imageView = (ImageView)findViewById(R.id.imgview);
        imageView.setImageBitmap(BitmapHealper.getInstance().getBitmap());


    }


}
