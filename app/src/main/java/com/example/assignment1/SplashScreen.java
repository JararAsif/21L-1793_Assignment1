package com.example.assignment1;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    Animation fade_in,first_anim;
    TextView firstline;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        logo.startAnimation(fade_in);
        firstline.startAnimation(fade_in);
        new Handler()//for delaying the splash screen for 3s
                .postDelayed(()->{
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }, 3000);

    }
    private void init()//initalizing logo,text,and animations
    {
        firstline=findViewById(R.id.startline);
        logo=findViewById(R.id.logo);
        fade_in= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        first_anim=AnimationUtils.loadAnimation(this,R.anim.first_anim);
    }
}