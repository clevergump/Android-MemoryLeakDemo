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

import java.lang.ref.WeakReference;
import java.util.Arrays;

/**
 * 修复匿名内部类, 及退出页面时未取消 PhoneStateListener 的注册而导致内存泄漏的页面
 *
 * @author clevergump
 * @date 2016/7/25
 */
public class FixPhoneListenerLeakActivity extends Activity {
    private static final String TAG = FixPhoneListenerLeakActivity.class.getSimpleName();

    private PhoneStateListener mListener;
    private TelephonyManager mPhoneManager;

    private GridView mGv;
    private ImageAdapter mAdapter;

    private void loadImg() {
        mGv = (GridView) findViewById(R.id.gv);
        mAdapter = new ImageAdapter(this);
        mGv.setAdapter(mAdapter);
        mAdapter.addDatas(Arrays.asList(ImgUtils.URLS));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fix_phone_listener_leak);

        loadImg();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPhoneManager == null) {
            mPhoneManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        }
        if (mListener == null) {
            mListener = new MyListener(this);
        }
        mPhoneManager.listen(mListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private static class MyListener extends PhoneStateListener {
        private WeakReference<FixPhoneListenerLeakActivity> mActRef;

        public MyListener(FixPhoneListenerLeakActivity activity) {
            this.mActRef = new WeakReference<FixPhoneListenerLeakActivity>(activity);
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            FixPhoneListenerLeakActivity act = mActRef.get();
            if (act == null) {
                return;
            }
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    act.onCallRinging(incomingNumber);
                    break;
            }
        }
    }

    /**
     * 当来电话了
     */
    public void onCallRinging(String incomingNumber) {
        Toast.makeText(App.get(), TAG + " :\n来电话了, 对方号码是: " + incomingNumber, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "来电话了, 对方号码是: " + incomingNumber);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPhoneManager.listen(mListener, PhoneStateListener.LISTEN_NONE);
    }
}