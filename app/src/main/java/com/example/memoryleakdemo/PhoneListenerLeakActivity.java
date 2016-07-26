package com.example.memoryleakdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.memoryleakdemo.adapter.ImageAdapter;
import com.example.memoryleakdemo.utils.ImgUtils;

import java.util.Arrays;

/**
 * 展示匿名内部类, 及退出页面时未取消 PhoneStateListener 的注册导致内存泄漏的页面
 *
 * @author clevergump
 * @date 2016/7/25
 */
public class PhoneListenerLeakActivity extends Activity {
    private static final String TAG = FixPhoneListenerLeakActivity.class.getSimpleName();

    private PhoneStateListener mListener;
    private TelephonyManager mPhoneManager;

    private GridView mGv;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phone_listener_leak);

        loadImg();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPhoneManager == null) {
            mPhoneManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        }
        if (mListener == null) {
            mListener = new PhoneStateListener(){
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    switch (state) {
                        case TelephonyManager.CALL_STATE_RINGING:
                            onCallRinging(incomingNumber);
                            break;
                    }
                }
            };
        }
        mPhoneManager.listen(mListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    /**
     * 当来电话了
     */
    public void onCallRinging(String incomingNumber) {
        Toast.makeText(App.get(), TAG + " :\n来电话了, 对方号码是: " + incomingNumber, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "来电话了, 对方号码是: " + incomingNumber);
    }

    private void loadImg() {
        mGv = (GridView) findViewById(R.id.gv);
        mAdapter = new ImageAdapter(this);
        mGv.setAdapter(mAdapter);
        mAdapter.addDatas(Arrays.asList(ImgUtils.URLS));
    }
}