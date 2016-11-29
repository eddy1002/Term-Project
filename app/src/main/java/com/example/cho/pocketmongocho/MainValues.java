package com.example.cho.pocketmongocho;

public class MainValues {
    private int step = 0;
    private int walk = 0;
    private int speed = 0;
    private String weather = "";

    private static MainValues instance;

    private MainValues(){
        step = 0;
        weather = "";
    }

    public static MainValues getInstance(){
        if (instance == null)
            instance = new MainValues();

        return instance;
    }

    public int getStep(){
        return step;
    }

    public int getWalk(){
        return walk;
    }

    public int getSpeed(){
        return speed;
    }

    public String getWeather(){
        return weather;
    }

    public void setStep(int inStep){
        step = inStep;
    }

    public void setWalk(int inWalk){
        walk = inWalk;
    }

    public void subSpeed(){
        speed--;
    }

    public void setSpeed(int inSpeed){
        speed = inSpeed;
    }

    public void setWeather(String inWeather){
        weather = inWeather;
    }
}
