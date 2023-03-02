package com.angelo_silvestre.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenCalculator extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int SPLASH_SCREEN = 3000;

        Animation topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        Animation botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        ImageView ssImage = findViewById(R.id.calculatorImg);
        TextView ssHeader = findViewById(R.id.ss_title);
        TextView ssParag = findViewById(R.id.ss_title2);
        ssImage.setAnimation(topAnim);
        ssHeader.setAnimation(botAnim);
        ssParag.setAnimation(botAnim);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenCalculator.this,MainActivity.class);
            Log.d("SplashScreenCalculator","HandlerIntent: Started");
            startActivity(intent);

            finish();
        }, SPLASH_SCREEN);
    }
}
