package com.appdroid.bellme.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.appdroid.bellme.R;

import java.util.Timer;
import java.util.TimerTask;

public class FlashActivity extends AppCompatActivity {
    Animation topani;
    ImageView logo;

    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        topani = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        logo = findViewById(R.id.logo);
        logo.setAnimation(topani);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(FlashActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);

    }
}