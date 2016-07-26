package com.example.memoryleakdemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.memoryleakdemo.App;

/**
 * 资源调用的工具类
 *
 * @author clevergump
 * @date 2016/7/26
 */
public class ResUtils {

    private ResUtils() {
    }

    private static Context getContext() {
        return App.get();
    }

    public static String getStringRes(int id) {
        try {
            return getContext().getResources().getString(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getStringRes(int id, Object... args) {
        try {
            return getContext().getResources().getString(id, args);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取 String[] 值. 如果id对应的资源文件不存在, 则返回 null.
     *
     * @param id
     * @return
     */
    public static String[] getStringArrayRes(int id) {
        try {
            return getContext().getResources().getStringArray(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取dimension px值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    public static int getDimenPixRes(int id) {
        try {
            return getContext().getResources().getDimensionPixelOffset(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取dimension float形式的 px值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    public static float getDimenFloatPixRes(int id) {
        try {
            return getContext().getResources().getDimension(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取 color 值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    public static int getColorRes(int id) {
        try {
            return ContextCompat.getColor(getContext(), id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取 Drawable 对象. 如果id对应的资源文件不存在, 则返回 null.
     *
     * @param id
     * @return
     */
    public static Drawable getDrawableRes(int id) {
        try {
            return ContextCompat.getDrawable(getContext(), id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
