package com.example.cho.pocketmongocho;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent intentManbo;
    BroadcastReceiver receiver;
    String serviceData;

    TextView txtStep;
    TextView txtWeather;

    MainValues mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mv = MainValues.getInstance();

        txtStep = (TextView) findViewById(R.id.txt_Step);
        txtWeather = (TextView) findViewById(R.id.txt_Weather);

        intentManbo = new Intent(this, ManboService.class);
        receiver = new PlayingReceiver();

        IntentFilter mainFilter = new IntentFilter("make.a.yong.manbo");
        registerReceiver(receiver, mainFilter);

        startService(intentManbo);
    }

    class PlayingReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("PlayignReceiver", "IN");
            serviceData = intent.getStringExtra("stepService");
            txtStep.setText(serviceData);

            GetXMLTask task = new GetXMLTask();
            task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
            txtWeather.setText(mv.getWeather());
        }
    }
}
