package com.example.memoryleakdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

import com.example.memoryleakdemo.adapter.ImageAdapter;
import com.example.memoryleakdemo.utils.ImgUtils;

import java.util.Arrays;

/**
 * 展示静态变量引用 Activity 导致内存泄漏的页面
 *
 * @author clevergump
 * @date 2016/7/26
 */
public class StaticLeakActivity extends Activity {

    private GridView mGv;
    private ImageAdapter mAdapter;

    private static Context sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_leak);
        sContext = this;
        loadImg();
    }

    private void loadImg() {
        mGv = (GridView) findViewById(R.id.gv);
        mAdapter = new ImageAdapter(this);
        mGv.setAdapter(mAdapter);
        mAdapter.addDatas(Arrays.asList(ImgUtils.URLS));
    }
}