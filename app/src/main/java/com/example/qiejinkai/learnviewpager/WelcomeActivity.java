package com.example.qiejinkai.learnviewpager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by qiejinkai on 16/4/1.
 */
public class WelcomeActivity extends Activity {

    private int waitTime = 2000;
    private boolean isFirstIn = false;

    private static final int GOHOME = 1001;
    private static final int GOGUIDE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.welcome_android);

        root.addView(iv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        setContentView(root);

        go();
    }

    private void go() {
        SharedPreferences preferences = getSharedPreferences("qjk",MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn",true);
        if(isFirstIn){
            welcomeHandler.sendEmptyMessageDelayed(GOGUIDE,waitTime);
            preferences.edit().putBoolean("isFirstIn",false).commit();
        }else{
            welcomeHandler.sendEmptyMessageDelayed(GOHOME,waitTime);
        }

    }

    private Handler welcomeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case GOGUIDE:
                    startActivity(new Intent(WelcomeActivity.this,Guide.class));
                    finish();
                    break;
                case GOHOME:
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                    break;
            }
        }
    };
}
