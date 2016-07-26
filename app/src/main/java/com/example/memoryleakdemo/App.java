package com.example.memoryleakdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * 自定义 Application
 *
 * @author clevergump
 * @date 2016/7/24
 */
public class App extends Application {

    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        LeakCanary.install(this);
    }

    public static App get() {
        return sApp;
    }
}