package com.example.dcl.herbalplantdetector;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dcl.herbalplantdetector.Healper.BitmapHealper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rootLayout;
    private Button btnTakePhoto;
    private Button btnGallery;
    private int GALLERY_REQUEST_CODE=2;
    static final int CAPTURE_IMAGE_REQUEST=1;
    File photoFile = null;
    private String mCurrentPhotoPath ="";
    Uri photoURI = null;
    ///commiting in git to save files


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        btnTakePhoto = (Button)findViewById(R.id.btnTakePhoto);
        btnGallery = (Button)findViewById(R.id.btnGellary);



        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select pictures"), GALLERY_REQUEST_CODE);

            }
        });



        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });
    }
    private void captureImage(){

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager())!= null){

                try{
                    photoFile = createImageFile();
                    displayMessage(getBaseContext(), photoFile.getAbsolutePath());
                    Log.i("herbal_plant_images",photoFile.getAbsolutePath());

                    if (photoFile != null){
                        Uri photoUri = FileProvider.getUriForFile(this, "com.example.dcl.herbalplantdetector.fileprovider",photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST);
                    }
                }
                catch (Exception ex){
                    displayMessage(getBaseContext(),ex.getMessage().toString());
                }
            }

            else {
                displayMessage(getBaseContext(), "Null");
            }

    }


    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        return  image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //for gallery
        if (requestCode== GALLERY_REQUEST_CODE && resultCode== RESULT_OK && data!= null && data.getData()!=null){
            Uri uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                BitmapHealper.getInstance().setBitmap(bitmap);
                Intent galleryImage = new Intent(MainActivity.this,ViewImage.class);
                startActivity(galleryImage);

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        //Bundle extras = data.getExtras();
       // Bitmap imageBitmap = (Bitmap) extras.get("data");
        ///Intent i = new Intent(MainActivity.this, ViewImage.class);
       /// startActivity(i);

        else {
            if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK) {
                Bitmap myBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                BitmapHealper.getInstance().setBitmap(myBitmap);
                Intent capturedimageview = new Intent(MainActivity.this, ViewImage.class);

                startActivity(capturedimageview);

            } else {
                displayMessage(getBaseContext(), "Request cancel or something went wrong");
            }
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==0){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] ==PackageManager.PERMISSION_GRANTED){
                captureImage();
            }
            else {
                displayMessage(getBaseContext(), "This app is not going to work without permission");

            }
        }
    }

    private void displayMessage(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
