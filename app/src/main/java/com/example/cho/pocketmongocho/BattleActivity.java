package com.example.cho.pocketmongocho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BattleActivity extends AppCompatActivity {
    MainValues mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        mv = MainValues.getInstance();
    }

    public void onClick(View v){
        mv.setInBattle(false);

        finish();
    }
}
