package com.market.onshopping.activity;

import android.app.Application;

import com.market.onshopping.utils.TypefaceUtil;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(),
                "SERIF",
                "font/didactgothic-regular.otf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }
}