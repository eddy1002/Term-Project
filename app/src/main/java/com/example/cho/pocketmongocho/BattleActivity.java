package com.example.cho.pocketmongocho;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class BattleActivity extends AppCompatActivity {
    MainValues mv;

    int animateNum = 0;
    ImageView imgPocketMon;
    ImageView imgBall;

    BackgroundThread backgroundThread;
    private final MyHandler mHandler = new MyHandler(this);

    Button buttonResult;
    Button buttonDraw;

    int randomMob;

    private GPSInfo gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        mv = MainValues.getInstance();
        animateNum = 0;

        imgPocketMon = (ImageView) findViewById(R.id.image_PocketMon);
        imgBall = (ImageView) findViewById(R.id.image_Ball);
        buttonResult = (Button) findViewById(R.id.button_Result);
        buttonDraw = (Button) findViewById(R.id.button);

        randomMob = (int) Math.round(Math.random() * 3);

        switch(randomMob){
            case 0:
                imgPocketMon.setImageResource(R.drawable.mob_0);
                break;
            case 1:
                imgPocketMon.setImageResource(R.drawable.mob_1);
                break;
            case 2:
                imgPocketMon.setImageResource(R.drawable.mob_2);
                break;
            case 3:
                imgPocketMon.setImageResource(R.drawable.mob_3);
                break;
        }
    }

    public void onClick(View v){
        animateNum++;
        buttonDraw.setVisibility(View.INVISIBLE);
    }

    public void onResult(View v){
        mv.setInBattle(false);
        imgBall.setVisibility(View.INVISIBLE);
        finish();
    }

    // Handler 에서 호출하는 함수
    private void handleMessage(Message msg) {
        Log.i("Timer", "IN");

        switch (animateNum){
            case(0):
                break;
            case(1):
                animateNum++;
                imgBall.setVisibility(View.VISIBLE);
                imgBall.setImageResource(R.drawable.drawball_0);
                break;
            case(2):
                animateNum++;
                imgBall.setImageResource(R.drawable.drawball_1);
                break;
            case(3):
                animateNum++;
                imgBall.setImageResource(R.drawable.drawball_2);
                break;
            case(4):
                animateNum++;
                imgBall.setImageResource(R.drawable.drawball_3);
                break;
            case(5):
                animateNum++;
                imgBall.setImageResource(R.drawable.drawball_4);
                break;
            default:
                animateNum = 0;
                int randNum = (int) Math.round(Math.random() * 100);
                if (randNum > 50){
                    buttonResult.setVisibility(View.VISIBLE);
                    buttonResult.setText("Catch!");

                    imgBall.setVisibility(View.VISIBLE);
                    imgPocketMon.setVisibility(View.INVISIBLE);

                    mv.setCatchMob(randomMob);

                    gps = new GPSInfo(BattleActivity.this);

                    if (gps.isGetLocation()){
                        float catchX = (float) gps.getLatitude();
                        float catchY = (float) gps.getLongitude();

                        mv.setCatchLocation(randomMob, catchX, catchY);
                    }
                    else{
                        gps.showSettingsAlert();
                    }
                }
                else{
                    buttonResult.setVisibility(View.VISIBLE);
                    buttonResult.setText("Miss..");

                    imgBall.setVisibility(View.INVISIBLE);
                    imgPocketMon.setVisibility(View.VISIBLE);
                }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        backgroundThread = new BackgroundThread();
        backgroundThread.setRunning(true);
        backgroundThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        boolean retry = true;
        backgroundThread.setRunning(false);

        while (retry) {
            try {
                backgroundThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public class BackgroundThread extends Thread {

        boolean running = false;

        void setRunning(boolean b) {
            running = b;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendMessage(mHandler.obtainMessage());
            }
        }
    }

    // 핸들러 객체 만들기
    private static class MyHandler extends Handler {
        private final WeakReference<BattleActivity> mActivity;
        public MyHandler(BattleActivity activity) {
            mActivity = new WeakReference<BattleActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BattleActivity activity = mActivity.get();
            if (activity != null) {
                activity.handleMessage(msg);
            }
        }
    }
}
