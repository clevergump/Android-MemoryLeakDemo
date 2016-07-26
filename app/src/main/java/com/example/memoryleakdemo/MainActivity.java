package com.example.memoryleakdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.memoryleakdemo.utils.AppUtils;
import com.example.memoryleakdemo.utils.ResUtils;

/**
 * 首页
 *
 * @author clevergump
 * @date 2016/7/25
 */
public class MainActivity extends AppCompatActivity {

    private ListView mLvLeak;
    private TextView mTvFixPhoneListenerLeak;
    private ArrayAdapter<String> mLeakAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLvLeak = (ListView) findViewById(R.id.lv_leak);
        mTvFixPhoneListenerLeak = (TextView) findViewById(R.id.tv_fix_phone_listener_leak);

        mLeakAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                ResUtils.getStringArrayRes(R.array.leak_list));

        mLvLeak.setAdapter(mLeakAdapter);
        mLvLeak.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        AppUtils.launchActivity(MainActivity.this, new Intent(MainActivity.this, StaticLeakActivity.class));
                        break;
                    case 1:
                        AppUtils.launchActivity(MainActivity.this, new Intent(MainActivity.this, PhoneListenerLeakActivity.class));
                        break;
                }
            }
        });

        mTvFixPhoneListenerLeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.launchActivity(MainActivity.this, new Intent(MainActivity.this, FixPhoneListenerLeakActivity.class));
            }
        });
    }
}
