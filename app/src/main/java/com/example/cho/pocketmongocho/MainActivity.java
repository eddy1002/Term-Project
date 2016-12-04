package com.example.cho.pocketmongocho;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent intentManbo;
    BroadcastReceiver receiver;
    String serviceData;

    TextView txtStep;
    TextView txtWeather;

    ImageView imgPlayer;

    MainValues mv;
    Intent battleIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mv = MainValues.getInstance();

        txtStep = (TextView) findViewById(R.id.txt_Step);
        txtWeather = (TextView) findViewById(R.id.txt_Weather);

        imgPlayer = (ImageView) findViewById(R.id.img_Player);

        intentManbo = new Intent(this, ManboService.class);
        receiver = new PlayingReceiver();

        IntentFilter mainFilter = new IntentFilter("make.a.yong.manbo");
        registerReceiver(receiver, mainFilter);

        startService(intentManbo);

        battleIntent = new Intent(MainActivity.this, BattleActivity.class);
    }

    public void viewCatch(View v){
        Intent catchIntent = new Intent(MainActivity.this, CatchActivity.class);
        startActivity(catchIntent);
    }

    class PlayingReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("PlayignReceiver", "IN");
            serviceData = intent.getStringExtra("stepService");

            if (serviceData.equals("0"))
                imgPlayer.setImageResource(R.drawable.player_0);
            else if (serviceData.equals("1"))
                imgPlayer.setImageResource(R.drawable.player_1);
            else if (serviceData.equals("2"))
                imgPlayer.setImageResource(R.drawable.player_2);

            GetXMLTask task = new GetXMLTask();
            task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
            txtWeather.setText(mv.getWeather());
            txtStep.setText(mv.getStep() + "");

            if (mv.getStep() <= 0){
                int randomStep = (int) Math.round(Math.random() * 10) + 10;
                mv.setStep(randomStep);
                mv.setInBattle(true);

                startActivity(battleIntent);
            }
        }
    }
}
