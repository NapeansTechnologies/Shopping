package com.market.onshopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.market.onshopping.MainActivity;
import com.market.onshopping.R;


public class SplashScreen extends AppCompatActivity {
   //AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       // ImageView loading=(ImageView)findViewById(R.id.imageview);

        //animation=(AnimationDrawable)loading.getDrawable();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                        Intent i=new Intent(SplashScreen.this, MainActivity.class);
                        finish();
                        startActivity(i);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void start(View v){
       // animation.start();
    }
    public void stop(View v){
        //animation.stop();
    }
}
