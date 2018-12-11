package com.example.dcl.herbalplantdetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class QuizActivity extends AppCompatActivity {
    //declared buttons
    private Button play;

    private ImageView quizLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

/// initilize the declared objects
        play = (Button)findViewById(R.id.btnPlay);
        quizLogo=(ImageView)findViewById(R.id.quizLogo);

/// play button if clicked
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, Play.class);
                startActivity(intent);
            }
        });
    }
}
