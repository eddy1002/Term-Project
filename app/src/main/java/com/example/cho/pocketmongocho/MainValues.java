package com.example.cho.pocketmongocho;

public class MainValues {
    private int step = 0;
    private int walk = 0;
    private int speed = 0;
    private String weather = "";
    private boolean inBattle = false;
    private int catchMob[];
    private float catchLocationX[];
    private float catchLocationY[];

    private static MainValues instance;

    private MainValues(){
        step = 10;
        weather = "";
        inBattle = false;
        catchMob = new int[4];
        catchLocationX = new float[4];
        catchLocationY = new float[4];
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

    public boolean getInBattle() { return inBattle; }

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

    public void setInBattle(boolean boolInBattle){ inBattle = boolInBattle; }

    public int getCatchMob(int index){
        return catchMob[index];
    }
    public void setCatchMob(int index){
        catchMob[index] = 1;
    }

    public void setCatchLocation(int index, float x, float y){
        catchLocationX[index] = x;
        catchLocationY[index] = y;
    }
    public float getCacthLocationX(int index){
        return catchLocationX[index];
    }
    public float getCacthLocationY(int index){
        return catchLocationY[index];
    }
}
