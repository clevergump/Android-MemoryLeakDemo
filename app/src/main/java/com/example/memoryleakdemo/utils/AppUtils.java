package com.example.memoryleakdemo.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

/**
 * 整个APP相关的工具类
 *
 * @author clevergump
 * @date 2016/7/26
 */
public class AppUtils {

    private AppUtils() {
    }

    /**
     * 安全的启动另一个 Activity
     *
     * @param from
     * @param intent
     */
    public static boolean launchActivity(Context from, Intent intent) {
        if (from == null)
            throw new NullPointerException("from == null");
        try {
            from.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}