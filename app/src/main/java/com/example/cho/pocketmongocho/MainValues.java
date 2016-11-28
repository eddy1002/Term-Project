package com.example.cho.pocketmongocho;

public class MainValues {
    private int step = 0;
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

    public String getWeather(){
        return weather;
    }

    public void setStep(int inStep){
        step = inStep;
    }

    public void setWeather(String inWeather){
        weather = inWeather;
    }
}
